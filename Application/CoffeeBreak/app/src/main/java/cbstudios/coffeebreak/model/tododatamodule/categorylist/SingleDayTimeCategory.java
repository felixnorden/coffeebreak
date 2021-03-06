package cbstudios.coffeebreak.model.tododatamodule.categorylist;////
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.categoryList.SingleDayTimeCategory.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 
//
//


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;


/**
 * @author Elias
 * @version 1.0
 *          <p>Responsibility: The main usage of this class is to check if a task is due on/before
 *          the date given to the instance.
 *          Used primarily for the "Tomorrow" instance</br >
 *          Uses: IAdvancedTask </br>
 *          Used by: CategoryFactory
 *          </p>
 */
class SingleDayTimeCategory implements ITimeCategory {
    private String name;
    private Calendar time;

    /**
     *
     * @param name is the name of the category
     * @param time is which day the category represents
     */
    SingleDayTimeCategory(String name, Calendar time) {
        this.name = name;
        this.time = time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTaskCount(List<IAdvancedTask> tasks) {
        int num = 0;
        for (IAdvancedTask task : tasks) {
            if (task.getName() == null || task.getNotification() == null || isInIntervall(task.getNotification())) {
                num++;
            }
        }
        return num;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IAdvancedTask> getValidTasks(List<IAdvancedTask> tasks) {
        List<IAdvancedTask> validTasks = new ArrayList<>();
        for (IAdvancedTask task : tasks) {
            if (task.getName() == null || task.getNotification() == null || isInIntervall(task.getNotification())) {
                validTasks.add(task);
            }
        }
        return validTasks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calendar getTime() {
        return time;
    }

    /**
     * Checks if the tasks day, year and month is the same as the date of the category
     *
     * @param date the tasks date
     * @return True if the dates occur on the same day.
     */
    @Override
    public boolean isInIntervall(Calendar date) {
        return (((date.get(Calendar.YEAR) == time.get(Calendar.YEAR)) &&
                (date.get(Calendar.DAY_OF_YEAR) == time.get(Calendar.DAY_OF_YEAR))));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){ return false;}

        SingleDayTimeCategory that = (SingleDayTimeCategory) o;

        if (name != null ? !name.equals(that.getName()) : that.name != null){ return false;}
        return time != null ? time.equals(that.time) : that.time == null;

    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);

        return result;
    }
}
