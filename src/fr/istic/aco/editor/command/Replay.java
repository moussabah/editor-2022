package fr.istic.aco.editor.command;

import fr.istic.aco.editor.recorder.Recorder;

/**
 * Provide a re-trigger of the saved Actions
 */

public class Replay implements Command {

    //The receiver of this command
    private Recorder recorder;

    public Replay(Recorder recorder ){
        this.recorder = recorder;
    }

    @Override
    public void execute() {
        this.recorder.replay();
    }
}
