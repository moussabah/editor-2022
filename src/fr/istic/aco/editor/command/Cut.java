package fr.istic.aco.editor.command;
import fr.istic.aco.editor.enginecore.Engine;

public class Cut implements Command {

    // The receiver
    private Engine engine;

    public Cut(Engine engine){
        this.engine = engine;
    }

    /** The run method of the concrete command Cut
     * Apply the Cut method to the user's(invoker) selection
     */
    @Override
    public void execute() {
        engine.cutSelectedText();
        System.out.println("Clipboard: " + this.engine.getClipboardContents());
        System.out.println("Buffer: " + this.engine.getBufferContents());
    }
}
