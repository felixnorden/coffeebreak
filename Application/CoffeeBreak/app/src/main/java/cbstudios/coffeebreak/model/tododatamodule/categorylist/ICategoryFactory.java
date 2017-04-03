package cbstudios.coffeebreak.model.toDoDataModule.categoryList;//

//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.categoryList.ICategoryFactory.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author :

import java.util.Calendar;
public interface ICategoryFactory {
	public ILabelCategory createLabelCategory();
	public ILabelCategory createLabelCategory(String name);
	public ITimeCategory createMultipleDayCategory(Calendar date);
	public ITimeCategory createSingleDayCategory(Calendar date);
}
