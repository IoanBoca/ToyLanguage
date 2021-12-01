import Controller.Controller;
import Controller.IController;
import Model.PrgState;
import Repository.IRepository;
import Repository.Repository;
import View.UI;

public class Main {
    public static void main(String[] v)
    {
        IRepository repository = new Repository();
        Controller controller=new Controller(repository);
        UI view=new UI(controller);
        view.start();
    }
}