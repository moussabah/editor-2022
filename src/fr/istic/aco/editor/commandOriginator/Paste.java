package fr.istic.aco.editor.commandOriginator;

import fr.istic.aco.editor.command.Command;
import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.enginecore.Engine;

public class Paste implements CommandOriginator {
    // The receiver
    private Engine engine;
    // The Invoker
    private Invoker invoker;

    public Paste(Engine engine, Invoker invoker) {
        this.engine = engine;
        this.invoker = invoker;
    }

    /** The run method of the concrete command Insert
     *  Apply the Insert method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        engine.pasteClipboard();
        System.out.println(this.engine.getBufferContents());
    }
}
