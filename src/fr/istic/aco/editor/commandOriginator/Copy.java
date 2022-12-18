package fr.istic.aco.editor.commandOriginator;

import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.recorder.Recorder;

import java.util.Optional;

public class Copy implements CommandOriginator {

    // The receiver
    private Engine engine;
    private Recorder recorder;

    public Copy(Engine engine, Recorder recorder){
        this.engine = engine;
        this.recorder = recorder;
    }

    /** The run method of the concrete command Copy
     * Apply the Copy method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        if (!this.recorder.isReplaying()){
            this.engine.copySelectedText();
        }
        this.recorder.save(this);
        System.out.println(this.engine.getBufferContents());
    }

    /* MEMENTO PART */
    /**
     * @return null if there's no memento used
     */
    @Override
    public Optional<Memento> getMemento() {
        return null;
    }
}
