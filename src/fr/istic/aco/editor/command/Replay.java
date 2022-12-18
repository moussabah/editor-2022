package fr.istic.aco.editor.command;

import fr.istic.aco.editor.command.Command;
import fr.istic.aco.editor.recorder.Recorder;

public class Replay implements Command {

    //The receiver
    private Recorder recorder;

    public Replay(Recorder recorder ){
        this.recorder = recorder;
    }

    @Override
    public void execute() {
        this.recorder.replay();
    }
}
