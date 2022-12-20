package fr.istic.aco.editor.command;
import fr.istic.aco.editor.recorder.Recorder;

/**
 * End the Record
 */

public class Stop implements Command {

    //The receiver
    private Recorder recorder;

    public Stop(Recorder recorder ){
        this.recorder = recorder;
    }

    @Override
    public void execute() {
        this.recorder.stop();
        System.out.println("...Finish to save");
    }
}