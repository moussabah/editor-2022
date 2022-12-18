package fr.istic.aco.editor;

import fr.istic.aco.editor.command.*;
import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.enginecore.EngineImpl;
import fr.istic.aco.editor.invoker.Client;
import fr.istic.aco.editor.invoker.Invoker;

import java.util.logging.Logger;

public class CommandConfigurator {

   private Invoker invoker;
   private Engine receiver;

   public static void main(String lineArgs[]) {

      CommandConfigurator client = new CommandConfigurator();
      client.run();

   }
   
    private void run() {
        Logger.getGlobal().info("Starting...");
        this.invoker = new Client();
        this.receiver = new EngineImpl();
        invoker.setReadStream(System.in);
        configureCommands();
        invoker.executeCommand();
    }
    private void configureCommands() {
        //Instanciate the concret Commands
        invoker.addCommand("Insert", new Insert(receiver,invoker));
        invoker.addCommand("Copy", new Copy(receiver));
        invoker.addCommand("Cut", new Cut(receiver));
        invoker.addCommand("Delete", new Delete(receiver));
        invoker.addCommand("Paste", new Paste(receiver));
        invoker.addCommand("Select", new MoveSelection(receiver,invoker));

        invoker.displayCommands();

        invoker.addCommand("Test", () -> System.err.println("Test : "+this.toString()));
    }

}
