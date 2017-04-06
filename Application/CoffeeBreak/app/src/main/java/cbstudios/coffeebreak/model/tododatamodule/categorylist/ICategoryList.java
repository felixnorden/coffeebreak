package cbstudios.coffeebreak.model.tododatamodule.categorylist;////
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.categoryList.ICategoryList.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 
//
//


import java.util.List;

public interface ICategoryList {

    public List<ILabelCategory> getLabelCategories();

    public void addLabelCategory(String name, int color);

    public void removeLabelCategory(ILabelCategory labelCategory);

    public void removeLabelCategory(String name);

    public List<ITimeCategory> getTimeCategories();

    public void initTimeCategories();

    public void initAllCategories();
}
