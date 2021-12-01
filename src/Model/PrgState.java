package Model;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIStack;
import Model.DataStructures.MyIList;
import Model.Values.StringValue;
import Model.Values.Value;
import Model.Statements.IStmt;
import Model.DataStructures.MyHeap;

import javax.imageio.spi.ImageInputStreamSpi;
import javax.management.StringValueExp;
import java.io.BufferedReader;

public class PrgState {

    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String,Value> symTable;
    private MyIList<Value> output;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private MyIDictionary<Integer, Value> heap;

    // IStmt originalProgram

    public PrgState(MyIStack<IStmt> stack,
                    MyIDictionary<String,Value> symtable,
                    MyIList<Value> ot,
                    MyIDictionary<StringValue, BufferedReader> ft,
                    MyIDictionary<Integer, Value> heap,
                    IStmt prg){
        exeStack = stack;
        symTable = symtable;
        output = ot;
        fileTable = ft;
        this.heap = heap;
        // originalProgram=deepCopy(prg); //TODO recreate the entire original prg
        stack.push(prg);
    }

    public MyIStack<IStmt> getExeStack() {  return exeStack;    }

    public void setExeStack(MyIStack<IStmt> exeStack) { this.exeStack = exeStack;   }

    public MyIDictionary<String, Value> getSymTable() { return symTable;    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {    this.symTable = symTable;   }

    public MyIList<Value> getOut() {    return output; }

    public void setOut(MyIList<Value> ot) {    this.output = ot; }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {  return fileTable;   }

    public void setFileTable(MyIDictionary<StringValue, BufferedReader> ft) {   this.fileTable = ft;    }

    public MyIDictionary<Integer, Value> getHeap(){    return this.heap;  }

    public void printPrgState() {
        System.out.println("Execution Stack: " + exeStack.toString());
        System.out.println("Table of Symbols: " + symTable.toString());
        System.out.println("Output: " + output.toString());
        System.out.println("FileTable: " + fileTable.toString());
        System.out.println("Heap: " + heap.toString());
        System.out.println("\n");
    }

}
