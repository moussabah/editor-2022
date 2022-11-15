package fr.istic.aco.editor.Command;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.EngineImpl;

public class CopySelectedText implements Command{

    Engine engine;

    public CopySelectedText(Engine engine){
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.copySelectedText();
    }
}
