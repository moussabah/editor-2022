package fr.istic.aco.editor.command;
import fr.istic.aco.editor.enginecore.Engine;

public class Paste implements Command{
    // The receiver
    private Engine engine;

    public Paste(Engine engine) {
        this.engine = engine;
    }

    /** The run method of the concrete command Insert
     *  Apply the Insert method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        this.engine.pasteClipboard();
        System.out.println("Buffer: " + this.engine.getBufferContents());
    }
}
