package fr.istic.aco.editor.test;

import fr.istic.aco.editor.command.Replay;
import fr.istic.aco.editor.command.Start;
import fr.istic.aco.editor.command.Stop;
import fr.istic.aco.editor.commandOriginator.*;
import fr.istic.aco.editor.recorder.Recorder;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import fr.istic.aco.editor.enginecore.*;
import fr.istic.aco.editor.invoker.*;

class ConcreteCommandTest {
	private Engine engine;
	private Invoker invoker;
	private Recorder recorder;
	private String text;

	/**	Display a param String like it's a flow of user's entry
	 * @param text a string to convert into an array of bytes
	 */
	public void setReadStream(String text) {
		if (text == null) {
			throw new IllegalArgumentException("null inputStream");
		}
		invoker.setReadStream(new ByteArrayInputStream(text.getBytes()));
	}

	@BeforeEach
	void SetUp() {

		this.engine = new EngineImpl();
		this.invoker = new Client();
		this.recorder = new Recorder();

		/* Concrete originator commands initialisation */
		invoker.addCommand("Copy", new Copy(engine, recorder));
		invoker.addCommand("Cut", new Cut(engine, recorder));
		invoker.addCommand("Delete", new Delete(engine, recorder));
		invoker.addCommand("Insert", new Insert(engine, invoker, recorder));
		invoker.addCommand("Paste", new Paste(engine, recorder));
		invoker.addCommand("Select", new MoveSelection(engine, invoker, recorder));

		/* Concrete commands initialisation */
		invoker.addCommand("Replay", new Replay(recorder));
		invoker.addCommand("Start", new Start(recorder));
		invoker.addCommand("Stop", new Stop(recorder));

		text = "Adventures of Tintin and Milou";
	}

	@Test
	void Initialisation() {
		Selection selection = this.engine.getSelection();

		/*Initially the cursor of the start is the same to the end and its value is 0*/

		assertEquals(0, selection.getBufferBeginIndex(), "At the start ?");
		assertEquals(0, selection.getBeginIndex(), "At the start");
		assertEquals("", this.engine.getBufferContents(), "The buffer must be empty at the start");

		/* At the start of the system, there is no record and the save or the replay are not launched */

		assertEquals(0,this.recorder.getSizeOfStoreCommands(), "No saved commands at the start");
		assertEquals(false, this.recorder.isRecording(), "We haven't started to save yet");
		assertEquals(false, this.recorder.isReplaying(), "We haven't started to replay yet");
	}

	/*#****************************************  SAVE AND STOP  *************************************/
	@Test
	void SaveAndStopTest(){
		invoker.executeCommand("Start");
		assertEquals(true, this.recorder.isRecording(),
				"The save command update the value of the predicate 'isRecording' to true");
		assertEquals(false, this.recorder.isReplaying(), "The 'isReplaying' predicate is remain unchanged");
		invoker.executeCommand("Stop");
		assertEquals(false, this.recorder.isRecording(),
				"The value of the predicate 'isRecording' changed to false");
		assertEquals(false, this.recorder.isReplaying());
	}

	/*#****************************************  SAVE AND STOP  *************************************/
	@Test
	@DisplayName("Replay the inserted text")
	void ReplayInsertTextTest(){
		invoker.executeCommand("Start");
		assertEquals(true, this.recorder.isRecording());

		setReadStream(text);
		invoker.executeCommand("Insert");

		invoker.executeCommand("Stop");
		assertEquals(false, this.recorder.isRecording());
		assertEquals(text, this.engine.getBufferContents(), "The text have been stored in the buffer");

		invoker.executeCommand("Replay");
		assertEquals(text+text, this.engine.getBufferContents());
	}

	@Test
	void ReplayTest(){
		setReadStream(text);
		invoker.executeCommand("Insert");
		assertEquals(text, this.engine.getBufferContents(), text + " have been stored in the buffer");

		invoker.executeCommand("Start");
		assertEquals(true, this.recorder.isRecording());

		setReadStream("0\n14");
		invoker.executeCommand("Select");
		invoker.executeCommand("Delete");
		assertEquals("Tintin and Milou", this.engine.getBufferContents());

		invoker.executeCommand("Stop");
		invoker.executeCommand("Replay");
		assertEquals("ou", this.engine.getBufferContents());
	}

	/*#****************************************  INSERTION   *************************************/

	// Insert an empty string -->  IS NOT WORKING
	@Test
	@DisplayName("Empty String")
	void EmptyInsertTest(){
		setReadStream(" ");
		invoker.executeCommand("Insert");
		assertEquals(" ", engine.getBufferContents());
	}

	@Test
	@DisplayName("Adventures of Tintin and Milou")
	void InsertTest() {
		Selection selection = engine.getSelection();
		setReadStream(text);
		invoker.executeCommand("Insert");
		assertEquals(0, selection.getBufferBeginIndex());
		assertEquals(30, selection.getBeginIndex(), "The cursor must be just after the last element of the insertion");
		assertEquals(text, engine.getBufferContents(), "The text have been stored in the buffer");
	}

	/*#****************************************  COPY   *************************************/

	@Test
	@DisplayName("Copy of 'Adventures'")
	void CopyTextTest() {
		setReadStream(text);
		invoker.executeCommand("Insert");

		//Test the insert command at the same time
		assertEquals(text, engine.getBufferContents());

		setReadStream(text);
		setReadStream("0\n10");
		invoker.executeCommand("Select");
		invoker.executeCommand("Copy");
		assertEquals("Adventures", engine.getClipboardContents(),
				"The copy of the selected text have been stored in the clipboard");
		assertEquals(text, engine.getBufferContents(), "Buffer content has remain the same");
	}

	/*#****************************************  CUT   *************************************/

	@Test
	@DisplayName("Cut 'Adventures' from Adventures of Tintin and Milou ")
	void CutTextTest() {
		setReadStream(text);
		invoker.executeCommand("Insert");
		setReadStream(text);
		setReadStream("0\n11");
		invoker.executeCommand("Select");
		invoker.executeCommand("Cut");
		assertEquals("Adventures ", engine.getClipboardContents(),
				"The cut of the selected text have been stored in the clipboard");
		assertEquals( "of Tintin and Milou", engine.getBufferContents(),
				"Buffer content has remain the same");
	}

	/*#****************************************  DELETE   *************************************/

	@Test
	@DisplayName("Remove ' and Tintin' from Adventures of Tintin and Milou")
	void DeleteTest() {
		setReadStream(text);
		invoker.executeCommand("Insert");
		setReadStream(text);
		setReadStream("20\n30");
		invoker.executeCommand("Select");
		invoker.executeCommand("Delete");
		//assertEquals("", engine.getClipboardContents(), "il n'y a rien dans le presse papier");
		assertEquals("Adventures of Tintin", engine.getBufferContents(),
				"' Milou' have been erased from the buffer");
	}

	/*#****************************************  PASTE   *************************************/

	@Test
	@DisplayName("Reverse 'Tintin' and 'Milou' from Adventures of Tintin and Milou")
	void PasteTest() {
		setReadStream(text);
		invoker.executeCommand("Insert");
		setReadStream("25\n30");
		invoker.executeCommand("Select");
		invoker.executeCommand("Cut");

		setReadStream("20\n25");
		invoker.executeCommand("Select");
		invoker.executeCommand("Delete");

		setReadStream("14\n14");
		invoker.executeCommand("Select");
		invoker.executeCommand("Paste");

		setReadStream("19\n19");
		invoker.executeCommand("Select");
		setReadStream(" and ");
		invoker.executeCommand("Insert");
		assertEquals("Milou", engine.getClipboardContents(), "le texte est bien s�lectionner");
		assertEquals("Adventures of Milou and Tintin", engine.getBufferContents(),
				"The buffer content has been updated : Tintin and Milou have exchanged their places ");

	}

}
