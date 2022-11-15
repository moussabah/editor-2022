package Command;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.EngineImpl;

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
