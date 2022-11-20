package fr.istic.aco.editor.command;

import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.enginecore.Engine;

public class CutSelectedText implements Command {

    // The receiver
    private Engine engine;
    // The Invoker
    private Invoker invoker;

    public CutSelectedText(Engine engine){
        this.engine = engine;
    }

    /** The run method of the concrete command CutSelectedText
     * Apply the CutSelectedText method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        engine.cutSelectedText();
    }
}
