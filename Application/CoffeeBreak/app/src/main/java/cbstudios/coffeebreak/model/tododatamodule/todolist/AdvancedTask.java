package cbstudios.coffeebreak.model.toDoDataModule.toDoList;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.toDoList.AdvancedTask.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 
//
//


import cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date;
import cbstudios.coffeebreak.model.toDoDataModule.categoryList.ILabelCategory;

public class AdvancedTask implements IAdvancedTask {
	private ITask task;
	private List<ILabelCategory> labels;
	private Date time;
	private String note;
	private Priority priority;
	public void setters();
	public void getters();
	public void isChecked();
	public void toggleChecked();
	public void setChecked(Object boolean);
}