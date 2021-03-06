package cbstudios.coffeebreak.model.tododatamodule.categorylist;////


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * @author Elias
 * @version 1.0
 *          <p>Responsibility: A class whose main usage is checking if a date of a task is
 *          occuring before or during the date of the instance.  Will be used for the instances "Today" and "Next seven days" </br >
 *          Uses: IAdvancedTask </br>
 *          Used by: CategoryFactory
 *          </p>
 */
class MultipleDayTimeCategory implements ITimeCategory {

    private String name;
    private Calendar time;

    /**
     *
     * @param name is the name of the category
     * @param time is the time limit of the category
     */
    MultipleDayTimeCategory(String name, Calendar time) {
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
     * Checks if a tasks date is before or during the date of the instance
     *
     * @param date the date of the task
     * @return True if the tasks date is within the interval
     */
    @Override
    public boolean isInIntervall(Calendar date) {
        return (date.get(Calendar.YEAR) == time.get(Calendar.YEAR) &&
                date.get(Calendar.DAY_OF_YEAR) == time.get(Calendar.DAY_OF_YEAR) ||
                date.before(time));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){ return false;}

        MultipleDayTimeCategory that = (MultipleDayTimeCategory) o;

        if (name != null ? !name.equals(that.getName()) : that.name != null){ return false;}
        return time != null ? time.equals(that.time) : that.time == null;

    }

    @Override
    public int hashCode() {
        int result = 13;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);

        return result;
    }
}
