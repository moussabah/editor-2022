package fr.istic.aco.editor.Command;

import fr.istic.aco.editor.Client.Client;
import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.enginecore.EngineImpl;

public class CopySelectedText implements Command{

    // The receiver
    private Engine engine;
    // The Invoker
    private Client client;

    public CopySelectedText(Engine engine, Client client){
        this.engine = engine;
        this.client = client;
    }

    /** The run method of the concrete command CopySelectedText
     * Apply the CopySelectedText method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        engine.getSelection().setBeginIndex(client.getBeginIndex());
        engine.getSelection().setEndIndex(client.getEndIndex());
        engine.copySelectedText();
    }
}
