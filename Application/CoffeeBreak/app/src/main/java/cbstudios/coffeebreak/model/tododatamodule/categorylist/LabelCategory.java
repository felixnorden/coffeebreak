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
    private int color;

    LabelCategory() {
        this.name = "";
    }

    LabelCategory(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabelCategory that = (LabelCategory) o;

        if (name != null ? !name.equals(that.getName()): that.name != null ) return false;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        int result = 13;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + color;

        return result;
    }

}
