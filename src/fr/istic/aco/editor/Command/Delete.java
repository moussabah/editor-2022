package fr.istic.aco.editor.Command;

import fr.istic.aco.editor.Client.Client;
import fr.istic.aco.editor.enginecore.Engine;

public class Delete implements Command {

    // The receiver
    private Engine engine;
    // The Invoker
    private Client client;

    public Delete(Engine engine) {
        this.engine = engine;
    }

    /** The run method of the concrete command Delete
     * Apply the Delete method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        engine.getSelection().setBeginIndex(client.getBeginIndex());
        engine.getSelection().setEndIndex(client.getEndIndex());
        engine.delete();
    }
}