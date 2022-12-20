package fr.istic.aco.editor.commandOriginator;

import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.memento.InsertMemento;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.originator.Originator;
import fr.istic.aco.editor.recorder.Recorder;

import java.util.Optional;

/**
 * Provide an executable command named Insert
 */
public class Insert implements CommandOriginator, Originator {

    // The receiver
    private Engine engine;
    // The Invoker
    private Invoker invoker;

    /* MEMENTO ADD */
    private String text="";
    private Recorder recorder;

    /** Command to insert a String to the buffer at the selection poistion
     *
     * @param engine the engine
     * @param invoker the invoker
     * @param recorder the recorder
     */
    public Insert(Engine engine, Invoker invoker, Recorder recorder) {
        this.engine = engine;
        this.invoker = invoker;
        this.recorder = recorder;
    }

    /** Insert the given the input stream into the buffer
     *
     * Execute this command
     */
    @Override
    public void execute() {
        if (!this.recorder.isReplaying()) {
            System.out.print("Insert some text: ");
            this.text = invoker.getText();
        }
        engine.insert(this.text);
        this.recorder.save(this);

    }

    /** Provide a memento with the information of this command
     *
     * @return the memento with the information
     */
    @Override
    public Optional<Memento> getMemento() {
        return Optional.of(new InsertMemento(text));
    }

    /** Given a memento, set the surrounding elements with the information contained inside the memento.
     *
     * @param memento the memento with the information.
     */
    @Override
    public void setMemento(Memento memento) {       //Only used for the replay
        this.text = ((InsertMemento) memento).getText();    //Change the type of memento for more precise
        invoker.setText(this.text);
    }
}