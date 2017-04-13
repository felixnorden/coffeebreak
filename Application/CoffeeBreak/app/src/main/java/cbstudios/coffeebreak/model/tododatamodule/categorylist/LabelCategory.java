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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * A class that is responsibly for creating and changing the LabelCategories
 */
public class LabelCategory implements ILabelCategory {


    private String name;
    private int color;

    /**
     * Creating a labelCategory with no name
     */
    LabelCategory() {
        this.name = "";
        this.color = getRandomColor();
    }

    /**
     * Creating a labelCateogory with a name and a color
     * @param name of the labelCategory
     */
    LabelCategory(String name) {
        this.name = name;
        this.color = getRandomColor();
    }

    /**
     *
     * @return a random Color
     */
    public int getRandomColor(){
        Random rnd = new Random();
        return Color.BLUE;
        //return (Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        //return Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    /**
     *
     * @return the name of the labelCategory
     */
    public String getName() {
        return name;
    }


    @Override
    public List<IAdvancedTask> getValidTasks(List<IAdvancedTask> tasks) {
        List<IAdvancedTask> validTasks = new ArrayList<>();
        for(IAdvancedTask task: tasks){
            if (task.getName() == null){task.addLabel(this);}
            for(ILabelCategory labelCategory: task.getLabels()){
                if(labelCategory.getName().equals(this.getName())) {
                    validTasks.add(task);
                }
            }

        }
        return validTasks;
    }

    /**
     * Sets a new name for a labelCategory
     * @param name is the new name of the labelCategory
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the color of the labelCategory
     */
    public int getColor() {
        return color;
    }

    /**
     * sets a new color for the labelCategory
     * @param color is the new color for the labelCategory
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * An equals method for the class
     * @param o is the object that the method will compare to
     * @return True if equal, false if not equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabelCategory that = (LabelCategory) o;

        if (name != null ? !name.equals(that.getName()): that.name != null ) return false;
        return color == that.color;
    }

    /**
     *
     * @return an int that is unique from different objects
     */
    @Override
    public int hashCode() {
        int result = 13;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + color;

        return result;
    }

    //Testa saker
}
