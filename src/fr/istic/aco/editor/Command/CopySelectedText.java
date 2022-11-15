package fr.istic.aco.editor.Command;

import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.enginecore.EngineImpl;

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
