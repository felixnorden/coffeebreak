package cbstudios.coffeebreak.model.tododatamodule.categorylist;////
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : ILabelCategory.java
//  @ Date : 03/04/2017
//  @ Author : 
//
//


import android.graphics.Color;

/**
 * An interface for the labelCategory
 */
public interface ILabelCategory {

    /**
     *
     * @return the name of the labelCategory
     */
    public String getName();

    /**
     * Set a new name to a labelCategory
     * @param name is the new name of a labelCategory
     */
    public void setName(String name);

    /**
     *
     * @return the color of the labelCategory
     */
    public int getColor();

    /**
     * Sets a new Color to a labelCategory
     * @param color is the new color of a labelCategory
     */
    public void setColor(int color);
}