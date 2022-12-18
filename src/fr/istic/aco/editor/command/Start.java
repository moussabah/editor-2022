package fr.istic.aco.editor.command;

import fr.istic.aco.editor.command.Command;
import fr.istic.aco.editor.recorder.Recorder;

public class Start implements Command {

    //The receiver
    private Recorder recorder;

    public Start(Recorder recorder ){
        this.recorder = recorder;
    }

    @Override
    public void execute() {
        this.recorder.start();
    }
}
