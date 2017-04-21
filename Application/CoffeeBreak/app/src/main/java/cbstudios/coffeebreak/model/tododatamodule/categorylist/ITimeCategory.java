package cbstudios.coffeebreak.model.tododatamodule.categorylist;//
import java.util.Calendar;
import java.util.GregorianCalendar;

//  @ Project : CoffeeBreak
//  @ File Name : ITimeCategory.java
//  @ Date : 03/04/2017
//  @ Author : 
//
//

/**
 * A interface for the two timeCategories
 */
public interface ITimeCategory {

    public String getName();

    /**
     * Sets a new name to a timeCategory
     * @param name is the new name to a timeCategory
     */
    public void setName(String name);

    /**
     * A method that checks if a specific date is within the intervall of the timeCategory
     * @param date is the date that is checked if it is within the intervall
     * @return true if it is within the intervall, false if it is not
     */
    public boolean isInIntervall(Calendar date);

    /**
     *
     * @return the date that a timeCategory holds
     */
    public Calendar getTime();

    /**
     * Will never be used?
     * @param time
     */
    public void setTime(Calendar time);

    /**
     * An equals method that compares a timeCategory with another object to check if they are
     * the same
     * @param o is the object the timeCategory will compare with
     * @return true if equal, false if not equal
     */
    public boolean equals(Object o);

    /**
     *
     * @return an int that is unique from different objects
     */
    public int hashCode();
}
