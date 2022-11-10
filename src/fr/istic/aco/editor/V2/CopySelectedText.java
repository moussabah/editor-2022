package fr.istic.aco.editor.V2;
import fr.istic.aco.editor.V1.*;
public class CopySelectedText implements Command {

    private Engine engine;
    public CopySelectedText(Engine engineCopy)
    {
        engine = engineCopy;

    }

    @Override
    public void execute() {
        this.engine.copySelectedText();

    }


}
