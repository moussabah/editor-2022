package fr.istic.aco.editor.Command;

import fr.istic.aco.editor.Client.Client;
import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.enginecore.EngineImpl;

public class Insert implements Command {

    // The receiver
    private Engine engine;
    // The Invoker
    private Client client;

    public Insert(Engine engine, Client client) {
        this.engine = engine;
        this.client = client;
    }

    /** The run method of the concrete command Insert
     *  Apply the Insert method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        engine.insert(client.getText());
    }
}