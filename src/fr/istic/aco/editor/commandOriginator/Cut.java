package fr.istic.aco.editor.commandOriginator;

import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.recorder.Recorder;

import java.util.Optional;

public class Cut implements CommandOriginator {

    // The receiver
    private Engine engine;
    private Recorder recorder;

    public Cut(Engine engine, Recorder recorder) {
        this.engine = engine;
        this.recorder = recorder;
    }

    /** The run method of the concrete command Cut
     * Apply the Cut method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        this.engine.cutSelectedText();

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
    public void setMemento(Memento memento) {}
}
