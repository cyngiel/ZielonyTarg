package com.example.zielonytarg.data;

public class Category {
    private int id;
    private String name;

    Category(int id, String name){
        this.id = id;
        this.name = name;
    }

    String getName(){
        return name;
    }

    void setName(String name){
        this.name = name;
    }

}
