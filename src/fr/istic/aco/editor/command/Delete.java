package fr.istic.aco.editor.command;

import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.enginecore.Engine;

public class Delete implements Command {

    // The receiver
    private Engine engine;
    // The Invoker
    private Invoker invoker;

    public Delete(Engine engine) {
        this.engine = engine;
    }

    /** The run method of the concrete command Delete
     * Apply the Delete method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        this.engine.delete();
        System.out.println(this.engine.getBufferContents());
    }
}