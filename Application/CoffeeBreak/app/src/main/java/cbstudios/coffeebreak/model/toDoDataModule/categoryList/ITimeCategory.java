package cbstudios.coffeebreak.model.toDoDataModule.categoryList;//
import java.util.GregorianCalendar;

//  @ Project : CoffeeBreak
//  @ File Name : ITimeCategory.java
//  @ Date : 03/04/2017
//  @ Author : 
//
//
public interface ITimeCategory {
	public void getters();
	public void setters();
	public boolean isInIntervall(GregorianCalendar date);
}
