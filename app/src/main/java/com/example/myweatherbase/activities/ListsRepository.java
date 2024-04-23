package com.example.myweatherbase.activities;

import com.example.myweatherbase.activities.model.List;
import com.example.myweatherbase.activities.model.Root;

import java.util.ArrayList;

public class ListsRepository {
    private ArrayList<List> lists;
    public static ListsRepository instance;
    private ListsRepository(){
        lists = Root.lists;
    }
    public static ListsRepository getInstance(){
        if (instance == null)
            instance = new ListsRepository();
        return instance;
    }
    public List get(int index) {
        return lists.get(index);
    }
    public java.util.List<List> getAll(){return lists;}

    public int size() {
        return lists.size();
    }

    public void remove(List list) { lists.remove(list); }

    public void add(List list) { lists.add(list); }

    public void add(int index, List list) { lists.add(index, list); }
}
