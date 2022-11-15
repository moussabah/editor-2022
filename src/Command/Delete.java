package Command;

import fr.istic.aco.editor.Engine;

public class Delete implements Command {

    Engine engine;

    public Delete(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void execute() {
        engine.delete();
    }
}