package fr.istic.aco.editor.commandOriginator;

import fr.istic.aco.editor.command.Command;
import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.enginecore.Engine;

public class Cut implements CommandOriginator {

    // The receiver
    private Engine engine;
    // The Invoker
    private Invoker invoker;

    public Cut(Engine engine){
        this.engine = engine;
    }

    /** The run method of the concrete command Cut
     * Apply the Cut method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        engine.cutSelectedText();
        System.out.println(this.engine.getBufferContents());
    }
}
