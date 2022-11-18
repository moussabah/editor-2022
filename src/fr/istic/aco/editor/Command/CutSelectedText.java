package fr.istic.aco.editor.Command;

import fr.istic.aco.editor.Client.Client;
import fr.istic.aco.editor.enginecore.Engine;

public class CutSelectedText implements Command {

    // The receiver
    private Engine engine;
    // The Invoker
    private Client client;

    public CutSelectedText(Engine engine){
        this.engine = engine;
    }

    /** The run method of the concrete command CutSelectedText
     * Apply the CutSelectedText method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        engine.getSelection().setBeginIndex(client.getBeginIndex());
        engine.getSelection().setEndIndex(client.getEndIndex());
        engine.cutSelectedText();
    }
}
