package fr.istic.aco.editor.commandOriginator;

import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.recorder.Recorder;

import java.util.Optional;

public class Paste implements CommandOriginator {
    // The receiver
    private Engine engine;
    private Recorder recorder;

    public Paste(Engine engine, Recorder recorder) {
        this.engine = engine;
        this.recorder = recorder;
    }

    /** The run method of the concrete command Insert
     *  Apply the Insert method to the user's(invoker) selection
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
    /**BAD SMEll but no find other way */
    public void setMemento(Memento memento) {}

}
