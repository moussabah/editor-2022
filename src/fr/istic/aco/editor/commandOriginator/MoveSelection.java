package fr.istic.aco.editor.commandOriginator;

import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.memento.SelectionMemento;
import fr.istic.aco.editor.originator.Originator;
import fr.istic.aco.editor.recorder.Recorder;

import java.util.Optional;

/**
 * Provide an executable command named MoveSelection
 */
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

    /** Command to Set the cursor's Selection
     *
     * Execute this command
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

    /** Provide a memento with the information of this command
     *
     * @return the memento with the information
     */
    @Override
    public Optional<Memento> getMemento() {
        return Optional.of(new SelectionMemento(this.beginIndex, this.endIndex));
    }

    /** Given a memento, set the surrounding elements with the information contained inside the memento.
     *
     * @param memento the memento with the information.
     */
    @Override
    public void setMemento(Memento memento) {

        //We will set the current selection by the new ones given by the memento

        this.beginIndex = ((SelectionMemento) memento).getBeginIndex();
        this.endIndex = ((SelectionMemento) memento).getEndIndex();
    }
}
