package service;
import java.util.List;

public interface CRUDService<Obj> {
    void add(Obj t);
    List<Obj> getAll();
    void update(int id, Obj t);
    void delete(int id);
}
