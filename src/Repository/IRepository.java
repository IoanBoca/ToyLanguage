package Repository;

import Model.PrgState;

import java.util.List;

public interface IRepository {

    PrgState getCurrentPrgState() throws RepoException;
    void addPrgState(PrgState newPrgState);
    void clearRepo();
    void logPrgStateExec() throws RepoException, java.io.IOException;
    void setLogFilePath(String logFilePath);

    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> newList);
}
