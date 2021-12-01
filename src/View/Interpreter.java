package View;

import Controller.Controller;

import Model.Expressions.*;
import Model.Types.BoolType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Repository.RepoException;

import Model.DataStructures.*;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.IntType;
import Model.Values.IntValue;

import Model.Values.StringValue;
import Model.Values.Value;
import Repository.IRepository;
import Repository.Repository;
import View.Commands.ExitCommand;
import View.Commands.RunExampleCommand;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

class Interpreter {

    public static void initializeDataStructures(IStmt example, String logFilePath, TextMenu menu, String RunExampleCmdKey) throws StmtException, ExpException, RepoException, IOException {
        MyIStack<IStmt> exeStack = new MyStack<>();
        MyIDictionary<String, Value> tableOfSymbols = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable = new MyDictionary<>();
        MyIDictionary<Integer,Value> heap = new MyHeap<Integer,Value>();
        PrgState programState = new PrgState(exeStack, tableOfSymbols, output, fileTable, heap, example);

        IRepository repo = new Repository(logFilePath);
        try{
            new FileWriter(logFilePath, false).close();
        } catch (IOException e){
            System.out.println("ERROR when deleting content of file: " + e.getMessage());
        }
        repo.addPrgState(programState);
        Controller ctrl = new Controller(repo);

        menu.addCommand(new RunExampleCommand(RunExampleCmdKey, example.toString(), ctrl));

        // ctrl.allSteps();
        // ctrl.getRepo().clearRepo();
    }

    public static void main(String[] args) {

        TextMenu menu = new TextMenu();

        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new
                        VariableExp("v"))));
        try {
            Interpreter.initializeDataStructures(ex1, "log1.txt", menu, "1");
        } catch (Exception e){
            System.out.println("ERROR " + e.getMessage());
        }

        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithmeticExp(new ValueExp(new IntValue(2)), new
                                ArithmeticExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), '*'),'+')),
                                new CompStmt(new AssignStmt("b", new ArithmeticExp(new VariableExp("a"), new ValueExp(new
                                        IntValue(1)), '+')), new PrintStmt(new VariableExp("b"))))));
        try {
            Interpreter.initializeDataStructures(ex2, "log2.txt", menu, "2");
        } catch (Exception e){
            System.out.println("ERROR " + e.getMessage());
        }

        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VariableExp("a"), new AssignStmt("v", new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VariableExp("v"))))));
        try {
            Interpreter.initializeDataStructures(ex3, "log3.txt", menu, "3");
        } catch (Exception e){
            System.out.println("ERROR " + e.getMessage());
        }

        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new OpenReadFileStmt(new ValueExp(new StringValue("varf"))),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadFileStmt(new ValueExp(new StringValue("varf")),"varc"),
                                                new CompStmt(new PrintStmt(new VariableExp("varc")),
                                                        new CompStmt(new ReadFileStmt(new ValueExp(new StringValue("varf")),"varc"),
                                                                new CompStmt(new PrintStmt(new VariableExp("varc")),
                                                                        new CloseReadFileStmt(new ValueExp(new StringValue("varf")))))))))));
        try {
            Interpreter.initializeDataStructures(ex4, "log4.txt", menu, "4");
        } catch (Exception e){
            System.out.println("ERROR " + e.getMessage());
        }

        // Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new HeapAllocationStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                    new CompStmt(new HeapAllocationStmt("a", new VariableExp("v")),
                    new CompStmt(new PrintStmt(new VariableExp("v")), new PrintStmt(new VariableExp("a")))))));
        try {
            Interpreter.initializeDataStructures(ex5, "log5.txt", menu, "5");
        } catch (Exception e){
            System.out.println("ERROR " + e.getMessage());
        }

        // Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new HeapAllocationStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                    new CompStmt(new HeapAllocationStmt("a", new VariableExp("v")),
                    new CompStmt(new PrintStmt(new HeapReadingExp(new VariableExp("v"))),
                                 new PrintStmt(new ArithmeticExp(new HeapReadingExp(new HeapReadingExp(new VariableExp("a"))), new ValueExp(new IntValue(5)), '+')))))));
        try {
            Interpreter.initializeDataStructures(ex6, "log6.txt", menu, "6");
        } catch (Exception e){
            System.out.println("ERROR " + e.getMessage());
        }

        // Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocationStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new HeapReadingExp(new VariableExp("v"))),
                                new CompStmt(new HeapWritingStmt("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithmeticExp(new HeapReadingExp(new VariableExp("v")), new ValueExp(new IntValue(5)), '+'))))));
        try {
            Interpreter.initializeDataStructures(ex7, "log7.txt", menu, "7");
        } catch (Exception e){
            System.out.println("ERROR " + e.getMessage());
        }

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocationStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocationStmt("a", new VariableExp("v")),
                                        new CompStmt(new HeapAllocationStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VariableExp("a")))))))));
        try {
            Interpreter.initializeDataStructures(ex8, "log8.txt", menu, "8");
        } catch (Exception e){
            System.out.println("ERROR " + e.getMessage());
        }

        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExp(new VariableExp("v"), new ValueExp(new IntValue(0)), ">"),
                                new CompStmt(new PrintStmt( new VariableExp("v")), new AssignStmt("v", new ArithmeticExp( new VariableExp("v"), new ValueExp( new IntValue(1)), '-')))),
                                new PrintStmt(new VariableExp("v")))));
        try {
            Interpreter.initializeDataStructures(ex9, "log9.txt", menu, "9");
        } catch (Exception e){
            System.out.println("ERROR " + e.getMessage());
        }

        menu.addCommand(new ExitCommand("0","Exit"));
        menu.show();
    }
}
