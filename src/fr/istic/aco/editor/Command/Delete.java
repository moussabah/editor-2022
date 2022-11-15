package fr.istic.aco.editor.Command;

import fr.istic.aco.editor.enginecore.Engine;

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