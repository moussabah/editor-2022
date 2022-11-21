package fr.istic.aco.editor.command;

import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.enginecore.Engine;

public class Copy implements Command{

    // The receiver
    private Engine engine;
    // The Invoker
    private Invoker invoker;

    public Copy(Engine engine, Invoker invoker){
        this.engine = engine;
        this.invoker = invoker;
    }

    /** The run method of the concrete command Copy
     * Apply the Copy method to the user's(invoker) selection
     */
    @Override
    public void execute() {

        engine.copySelectedText();
    }
}
