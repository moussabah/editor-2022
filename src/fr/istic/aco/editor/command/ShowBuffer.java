package fr.istic.aco.editor.command;

import fr.istic.aco.editor.enginecore.Engine;

public class ShowBuffer implements Command{
    private Engine engine;
    public ShowBuffer(Engine engine){
        this.engine = engine;
    }

    public void execute(){
        System.out.println(this.engine.getBufferContents());
    }

}
