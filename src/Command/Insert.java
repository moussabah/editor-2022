package Command;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.EngineImpl;

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