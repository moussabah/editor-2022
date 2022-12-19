package fr.istic.aco.editor.commandOriginator;

import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.memento.SelectionMemento;
import fr.istic.aco.editor.originator.Originator;
import fr.istic.aco.editor.recorder.Recorder;

import java.util.Optional;

public class MoveSelection implements CommandOriginator, Originator {
    // The receiver
    private Engine engine;
    // The Invoker
    private Invoker invoker;
    private Recorder recorder;
    //private SelectionMemento selectionMemento;
    private int beginIndex, endIndex;

    public MoveSelection(Engine engine, Invoker invoker, Recorder recorder) {
        this.engine = engine;
        this.invoker = invoker;
        this.recorder = recorder;
        this.beginIndex = this.endIndex = 0;
    }

    /** The run method of the concrete command Insert
     *  Apply the Insert method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        if (!this.recorder.isReplaying()){
            this.beginIndex = invoker.getBeginIndex();
            this.endIndex = invoker.getEndIndex();
        }
        this.engine.getSelection().setBeginIndex(this.beginIndex);
        this.engine.getSelection().setEndIndex(this.endIndex);
        this.recorder.save(this);
    }

    /* Memento part */
    @Override
    public Optional<Memento> getMemento() {
        return Optional.of(new SelectionMemento(this.beginIndex, this.endIndex));
    }

    @Override
    public void setMemento(Memento memento) {
        //Dans le memento on va changer la selection du memento par les entrees du user
        this.beginIndex = ((SelectionMemento) memento).getBeginIndex();
        this.endIndex = ((SelectionMemento) memento).getEndIndex();
    }
}
