package cbstudios.coffeebreak.model.toDoDataModule.categoryList;
//

//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.categoryList.CategoryList.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 
//
//
import cbstudios.coffeebreak.model.toDoDataModule.toDoList.TimeCategory;

import java.util.List;

public class CategoryList implements ICategoryList {
	private ICategoryFactory categoryFactory;
	private List<ILabelCategory> labelCategories;
	private List<ITimeCategory> timeCategories;
	public void addLabelCategory(String name){
	    ILabelCategory
    }
	public void removeLabelCategory(ILabelCategory label){

    }
	public void removeLabelCategory(String name){

    }
	public List<ITimeCategory> getTimeCategories(){
		return timeCategories;
	}
	public void initTimeCategories(){

    }
}
