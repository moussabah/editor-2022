package fr.istic.aco.editor.recorder;

import fr.istic.aco.editor.command.Command;

import java.util.List;

public class Recorder {

    private boolean stateRecorder;
    private List<Command> commands;

    public void save(Command command){
        if (this.stateRecorder == true){
            commands.add(command);
        }
    }

    public void start(){
        this.stateRecorder = true;
    }

    public void stop(){
        this.stateRecorder = false;
    }

    public void replay(){
        this.stop();
        for (Command command:this.commands) {
            command.execute();
        }
    }
}
