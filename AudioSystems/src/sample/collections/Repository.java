package sample.collections;

import javafx.collections.ObservableList;

//generic repository
public interface Repository<T> {

    //define crud operations
    ObservableList<T> getAll() throws Exception;
    void update(T item) throws Exception;
    int insert(T item) throws Exception;
    void delete(T item) throws Exception;
}
