package fr.istic.aco.editor.commandOriginator;

import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.recorder.Recorder;

import java.util.Optional;

/**
 * Provide an executable Command named Cut
 */

public class Cut implements CommandOriginator {

    // The receiver
    private Engine engine;
    private Recorder recorder;

    /** To Cut the selection into the clipboard
     *
     * @param engine the new engine
     * @param recorder the new recorder
     */
    public Cut(Engine engine, Recorder recorder) {
        this.engine = engine;
        this.recorder = recorder;
    }

    /** Store the selection into
     * the clipboard and remove it from the buffer
     *
     * Execute this command
     */
    @Override
    public void execute() {
        this.engine.cutSelectedText();
        this.recorder.save(this);
    }

    /* MEMENTO PART */
    /**
     * @return Empty optional if there's no memento used
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
