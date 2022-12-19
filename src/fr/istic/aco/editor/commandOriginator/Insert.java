package fr.istic.aco.editor.commandOriginator;

import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.memento.InsertMemento;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.originator.Originator;
import fr.istic.aco.editor.recorder.Recorder;

import java.util.Optional;

public class Insert implements CommandOriginator, Originator {

    // The receiver
    private Engine engine;
    // The Invoker
    private Invoker invoker;

    /* MEMENTO ADD */
    private String text="";
    private Recorder recorder;

    public Insert(Engine engine, Invoker invoker, Recorder recorder) {
        this.engine = engine;
        this.invoker = invoker;
        this.recorder = recorder;
    }

    /** The run method of the concrete command Insert
     *  Apply the Insert method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        if (!this.recorder.isReplaying()) {
            System.out.print("Inserer un message: ");
            this.text = invoker.getText();
        }
        engine.insert(this.text);
        this.recorder.save(this);//not condition because we actually verify in the save method if the record is started
        System.out.println(this.engine.getBufferContents());
    }

    @Override
    public Optional<Memento> getMemento() {
        return Optional.of(new InsertMemento(text));
    }

    @Override
    public void setMemento(Memento memento) {       //Only used for the replay
        this.text = ((InsertMemento) memento).getText();    //Change the type of memento for more precise
        invoker.setText(this.text);
    }
}