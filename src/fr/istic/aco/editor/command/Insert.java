package fr.istic.aco.editor.command;

import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.enginecore.Engine;

public class Insert implements Command {

    // The receiver
    private Engine engine;
    // The Invoker
    private Invoker invoker;

    public Insert(Engine engine, Invoker invoker) {
        this.engine = engine;
        this.invoker = invoker;
    }

    /** The run method of the concrete command Insert
     *  Apply the Insert method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        System.out.print("Insert some text : ");
        engine.insert(invoker.getText());
        System.out.println("Buffer: " + this.engine.getBufferContents());
    }
}