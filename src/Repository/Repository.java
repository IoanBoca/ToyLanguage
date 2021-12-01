package Repository;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIList;
import Model.DataStructures.MyIStack;
import Model.DataStructures.MyStack;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Repository implements IRepository{

    private List<PrgState> prgStatesList;
    private String logFilePath = "C:\\Users\\Ionut\\IdeaProjects\\ToyLanguage\\src\\firstTry.txt";

    public Repository(){
        prgStatesList = new ArrayList<PrgState>();
    }

    public Repository(String logFilePath){
        prgStatesList = new ArrayList<PrgState>();
        this.logFilePath = logFilePath;
    }

    @Override
    public PrgState getCurrentPrgState() throws RepoException {
        if(prgStatesList.isEmpty())
            throw new RepoException("The program states' list is empty");
        return prgStatesList.get(0);
    }

    @Override
    public void addPrgState(PrgState newPrgState) {
        this.prgStatesList.add(newPrgState);
    }

    @Override
    public void clearRepo() {
        this.prgStatesList.clear();
    }

    @Override
    public void logPrgStateExec() throws RepoException, java.io.IOException {
        PrintWriter logFile = null;
        PrgState currentPrgState = prgStatesList.get(0);

        MyIStack<IStmt> currentExeStack = currentPrgState.getExeStack();
        MyIDictionary<String, Value>  currentSymTable = currentPrgState.getSymTable();

        MyIList<Value> currentOut = currentPrgState.getOut();
        Set<String> currentSymTableKeys = currentSymTable.keys();

        MyIDictionary<StringValue, BufferedReader> currentFileTable = currentPrgState.getFileTable();
        Set<StringValue> currentFileTableKeys = currentFileTable.keys();

        MyIDictionary<Integer,Value> currentHeap = currentPrgState.getHeap();
        Set<Integer> currentHeapKeys = currentHeap.keys();

        List<IStmt> reversedExeStack = currentExeStack.getReversed();

        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));

            logFile.append("ExeStack:\n");

            for(IStmt statement : reversedExeStack){
                logFile.append(statement.toString()).append("\n");
            }

            logFile.append("SymTable:\n");
            for (String key : currentSymTableKeys){
                Value currentVal = currentSymTable.get(key);
                logFile.append(key).append(" --> ").append(currentVal.toString()).append("\n");
            }

            logFile.append("Out:\n");
            for (int i=0; i<currentOut.size(); i++){
                Value aux = currentOut.get(i);
                logFile.append(aux.toString()).append("\n");
            }

            logFile.append("FileTable:\n");
            for (StringValue key : currentFileTableKeys){
                BufferedReader currentBR = currentFileTable.get(key);
                logFile.append(key.toString()).append(" --> ").append(currentBR.toString()).append("\n");
            }

            logFile.append("Heap:\n");
            for (Integer key : currentHeapKeys){
                Value currentValue = currentHeap.get(key);
                logFile.append(key.toString()).append(" --> ").append(currentValue.toString()).append("\n");
            }
            logFile.append("\n");
        } catch (java.io.IOException e) {
            System.err.println("Eroare la citire/scriere" + e);
        } finally {
            if (logFile != null)
                logFile.close();
        }
    }

    @Override
    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public List<PrgState> getPrgList() {
        return prgStatesList;
    }

    @Override
    public void setPrgList(List<PrgState> newList) {
        prgStatesList = newList;
    }
}
