package cbstudios.coffeebreak.model.tododatamodule.todolist;

import java.util.Calendar;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Represent instances of tasks of the type Task. Holds the name, checked value
 *          and time of creation attributes.</br>
 *          Used by: Classes AdvancedTask and ListTask for holding the Task attributes through the abstraction interface ITask.
 *          Contained in ListTask's list of subtasks using the abstraction interface ITask.
 *          </p>
 */
class Task implements ITask {
    private String name;
    private boolean checked;
    private Calendar creationCalendar;

    /**
     * Constructor for an empty Task.
     */
    public Task() {
        name = null;
        checked = false;
        creationCalendar = Calendar.getInstance();
    }

    /**
     * Constructor for a task with the given name
     *
     * @param name the name for the created task
     */
    public Task(String name) {
        this.name = name;
        checked = false;
        creationCalendar = Calendar.getInstance();
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
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isChecked() {
        return checked;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleChecked() {
        setChecked(!isChecked());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setChecked(boolean value) {
        checked = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calendar getCreationCalendar() {
        return creationCalendar;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCreationCalendar(Calendar calendar) {
        creationCalendar = calendar;
    }

    /**
     * Compares equality of the inserted Object o with
     * regards of Task name, checked value and creation time.
     *
     * @param o Object to be compared
     * @return True if the compared object is the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){return false;}

        Task task = (Task) o;

        return checked == task.checked && creationCalendar.equals(task.creationCalendar) && (name != null ? name.equals(task.name) : task.name == null);
    }

    /**
     * Hash code generation based on the task's name, creation time and checked value
     *
     * @return the generated Hash Code for the task
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (creationCalendar.hashCode());
        result = 31 * result + (checked ? 1 : 0);
        return result;
    }
}
