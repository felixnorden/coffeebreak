package cbstudios.coffeebreak.model.tododatamodule.categorylist;//
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.categoryList.ICategoryFactory.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author :

import android.graphics.Color;

import java.util.Calendar;

public interface ICategoryFactory {
    public ILabelCategory createLabelCategory();

    public ILabelCategory createLabelCategory(String name, int color);

    public ITimeCategory createMultipleDayCategory(String name, Calendar date);

    public ITimeCategory createSingleDayCategory(String name, Calendar date);
}
