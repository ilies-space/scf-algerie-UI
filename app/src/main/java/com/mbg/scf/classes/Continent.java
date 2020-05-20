package com.mbg.scf.classes;

import java.util.ArrayList;

public class Continent {

    private String name;
    private ArrayList<Items> itemsList = new ArrayList<Items>();

    public Continent(String name, ArrayList<Items> itemsList) {
        super();
        this.name = name;
        this.itemsList = itemsList;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Items> getItemsList() {
        return itemsList;
    }
    public void setItemsList(ArrayList<Items> itemsList) {
        this.itemsList = itemsList;
    };


}