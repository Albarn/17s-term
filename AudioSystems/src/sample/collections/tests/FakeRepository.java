package sample.collections.tests;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.collections.Repository;

//fake repository implementation
//based on observable list collection(it's the storage of items)
public class FakeRepository<T> implements Repository<T> {

    //is connection to storage should be broken
    //if true, then every method will throw exception
    public boolean broken =false;

    //collection of items
    public ObservableList<T> items;

    //initialize collection with items in parameter
    public FakeRepository(T[] items){
        this.items= FXCollections.observableArrayList(items);
    }

    //crud operations for observable list
    @Override
    public ObservableList<T> getAll() throws Exception {
        if (broken) {
            throw new Exception();
        } else {
            return items;
        }
    }

    @Override
    public void update(T item) throws Exception {
        if(broken){
            throw new Exception();
        }
    }

    @Override
    public int insert(T item) throws Exception {
        if (broken) {
            throw new Exception();
        } else {
            items.add(item);
            return 0;
        }
    }

    @Override
    public void delete(T item) throws Exception {
        if (broken) {
            throw new Exception();
        } else {
            items.remove(item);
        }
    }
}
