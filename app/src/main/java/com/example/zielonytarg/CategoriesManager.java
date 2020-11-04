package com.example.zielonytarg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class CategoriesManager {
    static LinkedList<Category> categories;

    CategoriesManager(){
        categories = new LinkedList<>();
        initCategories();
    }

    private static void initCategories(){
        File myObj = new File("categories.txt");
        Scanner myReader = null;
        int id = 0;
        try {
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String categoryName = myReader.nextLine();
                Category category = new Category(id, categoryName);
                categories.add(category);
                System.out.println(categoryName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
