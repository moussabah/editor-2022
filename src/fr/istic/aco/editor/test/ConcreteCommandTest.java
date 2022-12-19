package fr.istic.aco.editor.test;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;

import fr.istic.aco.editor.command.*;
import fr.istic.aco.editor.enginecore.*;
import fr.istic.aco.editor.invoker.*;

class ConcreteCommandTest {
	private Engine engine;
	private Invoker invoker;

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
		engine = new EngineImpl();
		invoker = new Client();

		/* Concrete commands initialisation */
		invoker.addCommand("Copy", new Copy(engine));
		invoker.addCommand("Cut", new Cut(engine));
		invoker.addCommand("Delete", new Delete(engine));
		invoker.addCommand("Insert", new Insert(engine, invoker));
		invoker.addCommand("Paste", new Paste(engine));
		invoker.addCommand("Select", new MoveSelection(engine, invoker));

		text = "Adventures of Tintin and Milou";
	}

	@Test
	void Initialisation() {
		Selection selection = engine.getSelection();

		/*Initially the cursor of the start is the same to the end and its value is 0*/

		assertEquals(0, selection.getBufferBeginIndex(), "At the start ?");
		assertEquals(0, selection.getBeginIndex(), "At the start");
		assertEquals("", engine.getBufferContents(), "The buffer must be empty at the start");
	}

	/*#****************************************  INSERTION   *************************************/

	// Insert an empty string
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
		setReadStream("0\n10");
		invoker.executeCommand("Select");
		invoker.executeCommand("Cut");
		assertEquals("Adventures", engine.getClipboardContents(),
				"The cut of the selected text have been stored in the clipboard");
		assertEquals(text, engine.getBufferContents(),
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
