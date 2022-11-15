package fr.istic.aco.editor.Command;

import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.enginecore.EngineImpl;

public class CutSelectedText implements Command {

    Engine engine;

    public CutSelectedText(Engine engine){
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.cutSelectedText();
    }
}
