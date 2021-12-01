package View.Commands;

import Controller.Controller;
import Controller.CtrlException;
import Model.Expressions.ExpException;
import Model.Statements.StmtException;
import Repository.RepoException;

import java.io.IOException;

public class RunExampleCommand extends Command {
    private Controller ctr;

    public RunExampleCommand(String key, String desc, Controller ctr) {
        super(key, desc);
        this.ctr = ctr;
    }

    @Override
    public void execute() {
        try {
            ctr.allSteps();
            // ctr.getRepo().clearRepo();
        } catch (ExpException | StmtException | RepoException | CtrlException | IOException exception) {
            System.out.println("THERE IS AN ERROR: ");
            System.out.println(exception.getMessage());
            System.out.println("\nGive command: ");
        }
    }
}
