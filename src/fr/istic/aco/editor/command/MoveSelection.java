package fr.istic.aco.editor.command;

import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.enginecore.Engine;

public class MoveSelection implements Command {
    // The receiver
    private Engine engine;
    // The Invoker
    private Invoker invoker;

    public MoveSelection(Engine engine, Invoker invoker) {
        this.engine = engine;
        this.invoker = invoker;
    }

    /** The run method of the concrete command Insert
     *  Apply the Insert method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        engine.getSelection().setBeginIndex(invoker.getBeginIndex());
        engine.getSelection().setEndIndex(invoker.getEndIndex());
    }
}
