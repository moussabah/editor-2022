package fr.istic.aco.editor.V2;

import fr.istic.aco.editor.V1.Engine;

public class CutSelectedText implements Command {

    private Engine engine;
    public CutSelectedText(Engine engineCut)
    {
        engine = engineCut;

    }

    @Override
    public void execute() {
        this.engine.cutSelectedText();

    }
}
