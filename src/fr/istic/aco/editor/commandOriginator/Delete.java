package fr.istic.aco.editor.commandOriginator;

import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.recorder.Recorder;

import java.util.Optional;

/**
 * Provide an executable Command named Delete
 */

public class Delete implements CommandOriginator {

    // The receiver
    private Engine engine;
    private Recorder recorder;

    /** To suppress the selection from the buffer
     *
     * @param engine the new engine
     * @param recorder the new recorder
     */
    public Delete(Engine engine, Recorder recorder) {
        this.engine = engine;
        this.recorder = recorder;
    }

    /** Remove the Selection from the Buffer
     *
     * Execute this command
     */
    @Override
    public void execute() {
        this.engine.delete();
        this.recorder.save(this);
    }

    /* MEMENTO PART */
    /**
     * @return null if there's no memento used
     */
    @Override
    public Optional<Memento> getMemento() {
        return Optional.empty();
    }
    @Override
    public void setMemento(Memento memento) {
        //Its empty because this command has use of the memento so no parameter to set.

        //It's a bad smell but no find an alternative
    }
}