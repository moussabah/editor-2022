package fr.istic.aco.editor.command;
import fr.istic.aco.editor.enginecore.Engine;

public class Copy implements Command{

    // The receiver
    private Engine engine;

    public Copy(Engine engine){
        this.engine = engine;
    }

    /** The run method of the concrete command Copy
     * Apply the Copy method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        engine.copySelectedText();
        System.out.println("Clipboard: " + this.engine.getClipboardContents());
        System.out.println("Buffer: " + this.engine.getBufferContents());
    }
}
