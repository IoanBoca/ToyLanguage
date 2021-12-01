package View;

import Controller.*;
import Model.DataStructures.*;
import Model.Expressions.*;
import Model.Statements.*;
import Model.PrgState;
import Model.Types.*;
import Model.Values.*;
import Repository.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class UI {

    private final Controller controller;

    public UI(Controller controller) {
        this.controller = controller;
    }

    public void example1() throws CtrlException, StmtException, ExpException, RepoException, IOException {
        IStmt example1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new
                        VariableExp("v"))));

        MyIStack<IStmt> stack = new MyStack<>();
        initializeDataStructures(example1, stack);
    }

    public void example2() throws CtrlException, StmtException, RepoException, ExpException, IOException {
        IStmt example2 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithmeticExp(new ValueExp(new IntValue(2)), new
                                ArithmeticExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), '*'),'+')),
                                new CompStmt(new AssignStmt("b", new ArithmeticExp(new VariableExp("a"), new ValueExp(new
                                        IntValue(1)), '+')), new PrintStmt(new VariableExp("b"))))));

        MyIStack<IStmt> stack = new MyStack<>();
        initializeDataStructures(example2, stack);
    }

    public void example3() throws CtrlException, StmtException, RepoException, ExpException, IOException {
        IStmt example3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VariableExp("a"), new AssignStmt("v", new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VariableExp("v"))))));
        MyIStack<IStmt> stack = new MyStack<>();
        initializeDataStructures(example3, stack);
    }

    public void example4() throws CtrlException, StmtException, RepoException, ExpException, IOException {
        IStmt example4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new OpenReadFileStmt(new ValueExp(new StringValue("varf"))),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadFileStmt(new ValueExp(new StringValue("varf")),"varc"),
                                                new CompStmt(new PrintStmt(new VariableExp("varc")),
                                                        new CompStmt(new ReadFileStmt(new ValueExp(new StringValue("varf")),"varc"),
                                                                new CompStmt(new PrintStmt(new VariableExp("varc")),
                                                                        new CloseReadFileStmt(new ValueExp(new StringValue("varf")))))))))));
        MyIStack<IStmt> stack = new MyStack<>();
        initializeDataStructures(example4, stack);
    }

    private void initializeDataStructures(IStmt example, MyIStack<IStmt> stack) throws CtrlException, StmtException, ExpException, RepoException, IOException {
        MyIDictionary<String, Value> tableOfSymbols = new MyDictionary<String, Value>();
        MyIList<Value> output = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable = new MyDictionary<StringValue, BufferedReader>();
        MyIDictionary<Integer,Value> heap = new MyHeap<Integer,Value>();
        PrgState programState = new PrgState(stack, tableOfSymbols, output, fileTable, heap, example);
        System.out.println("Set logFilePath to: ");
        String logFilePath = new Scanner(System.in).nextLine();
        this.controller.getRepo().setLogFilePath(logFilePath);
        this.controller.getRepo().addPrgState(programState);
        this.controller.allSteps();
        this.controller.getRepo().clearRepo();
    }


    public void start() {
        System.out.println("""

                Menu:
                1. int v;
                   v=2;
                   print(v);
                   
                2. int a;
                   int b;
                   a=2+3*5;
                   b=a+1;
                   print(b);
                   
                3. bool a;
                   int v;
                   a=true;
                   (if a Then v=2 Else v=3);
                   print(v);
                   
                4. string varf;
                   varf="test.in";
                   openRFile(varf);
                   int varc;
                   readFile(varf,varc);print(varc);
                   readFile(varf,varc);print(varc);
                   closeRFile(varf);
                   
                0. Exit.
                """);


        System.out.println("\nGive command: ");
        int command;
        while (true) {
            try {
                Scanner s = new Scanner(System.in);
                command = s.nextInt();
                switch (command) {
                    case 1:
                        this.example1();
                        System.out.println("\nGive command: ");
                        break;
                    case 2:
                        ;
                        this.example2();
                        System.out.println("\nGive command: ");
                        break;
                    case 3:
                        this.example3();
                        System.out.println("\nGive command: ");
                        break;
                    case 4:
                        this.example4();
                        System.out.println("\nGive command: ");
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("\nBad command: ");
                        System.out.println("\nGive command: ");
                }
            } catch (ExpException | StmtException | RepoException | CtrlException | IOException exception) {
                System.out.println("THERE IS AN ERROR: ");
                System.out.println(exception.getMessage());
                System.out.println("\nGive command: ");
            }
        }
    }
}







