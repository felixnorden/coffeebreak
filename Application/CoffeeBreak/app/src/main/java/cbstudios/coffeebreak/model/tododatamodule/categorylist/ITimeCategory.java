package cbstudios.coffeebreak.model.tododatamodule.categorylist;//
import java.util.Calendar;
import java.util.GregorianCalendar;

//  @ Project : CoffeeBreak
//  @ File Name : ITimeCategory.java
//  @ Date : 03/04/2017
//  @ Author : 
//
//
public interface ITimeCategory {
   //getters & setters()

    public void setName(String name);
    public boolean isInIntervall(Calendar date);
    public Calendar getTime();
    public void setTime(Calendar time);
}
