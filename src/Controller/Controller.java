package Controller;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIList;
import Model.DataStructures.MyIStack;
import Model.Expressions.ExpException;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Statements.StmtException;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.IRepository;
import Repository.RepoException;
import Repository.Repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller implements IController{

    private IRepository repo;

    public Controller(IRepository repo){
        this.repo = repo;
    }

    public Map<Integer, Value> safeGarbageCollector(List<Integer> symbolTableAddress, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e -> symbolTableAddress.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Integer> getAddressFromSymbolTable(Collection<Value> symbolTableValues, Collection<Value> heap) {
        return Stream.concat(
                        heap.stream()
                                .filter(v -> v instanceof RefValue)
                                .map(v -> {RefValue v1 = (RefValue) v; return v1.getHeapAddress();}),
                        symbolTableValues.stream()
                                .filter(v -> v instanceof RefValue)
                                .map(v -> {RefValue v1 = (RefValue) v; return v1.getHeapAddress();})
                )
                .collect(Collectors.toList());
    }

    @Override
    public IRepository getRepo() {
        return this.repo;
    }

    @Override
    public PrgState oneStep(PrgState state) throws CtrlException, StmtException, ExpException, IOException {
        MyIStack<IStmt> stk = state.getExeStack();
        if(stk.isEmpty())
            throw new CtrlException("The execution stack of the current program state is empty");
        IStmt currentState = stk.pop();
        return currentState.execute(state);
    }

    @Override
    public void allSteps() throws CtrlException, StmtException, ExpException, RepoException, IOException {
        PrgState currentPrgState = this.repo.getCurrentPrgState();
        currentPrgState.printPrgState();
        repo.logPrgStateExec();
        while(!currentPrgState.getExeStack().isEmpty()){
            oneStep(currentPrgState);
            currentPrgState.printPrgState();

            currentPrgState.getHeap().setContent((HashMap<Integer, Value>) safeGarbageCollector(
                    getAddressFromSymbolTable(currentPrgState.getSymTable().getContent().values(), currentPrgState.getHeap().getContent().values()),
                    currentPrgState.getHeap().getContent()));

            repo.logPrgStateExec();
        }
    }

}
