package cbstudios.coffeebreak.model.tododatamodule.categorylist;////
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.categoryList.LabelCategory.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 
//
//


import android.graphics.Color;

public class LabelCategory implements ILabelCategory {

    private String name;
    private Color color;

    LabelCategory() {
        name = null;
        color = null;
    }

    LabelCategory(String name) {
        this.name = name;
        color = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


}
