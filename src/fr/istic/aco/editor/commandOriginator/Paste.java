package fr.istic.aco.editor.commandOriginator;

import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.recorder.Recorder;

import java.util.Optional;

/**
 * Provide an executable command named Paste
 */
public class Paste implements CommandOriginator {
    // The receiver
    private Engine engine;
    private Recorder recorder;

    public Paste(Engine engine, Recorder recorder) {
        this.engine = engine;
        this.recorder = recorder;
    }

    /** Insert the content of the clipboard
     * into the actual cursor selection of the buffer
     *
     * Execute this command
     */
    @Override
    public void execute() {
        this.engine.pasteClipboard();

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
