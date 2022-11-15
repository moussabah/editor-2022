package fr.istic.aco.editor.Command;

import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.enginecore.EngineImpl;

public class Insert implements Command {

    Engine engine;

    public Insert(Engine engine) {
        this.engine = engine;
    }

    //Get the text writ by the client
    String text = engine.getClipboardContents();
    @Override
    public void execute() {
        engine.insert(text);
    }
}