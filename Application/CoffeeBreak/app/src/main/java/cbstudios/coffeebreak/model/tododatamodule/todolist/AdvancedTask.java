package cbstudios.coffeebreak.model.tododatamodule.todolist;

import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Represent instances of tasks of type Advanced Task, which are contained in
 *          the list of displayed tasks.</br>
 *          Uses: Task class for delegating the name, checked value and creation time attributes.
 *          Priority enum attribute for defining the specific priority.
 *          Implements the IAdvancedTask interface</br>
 *          Used by: TaskFactory class for creation of AdvancedTasks.
 *          Different controllers and subsequent classes through the IAdvancedTask interface.
 *          </p>
 */
public class AdvancedTask implements IAdvancedTask {
    protected ITask task;
    private final List<ILabelCategory> labels;
    private Calendar date;
    private String note;
    private Priority priority;

    /**
     * Constructs a new Advanced Task with empty attributes
     */
    public AdvancedTask() {
        labels = new ArrayList<>();
        task = new Task();
        priority = Priority.NONE;
    }

    /**
     * Constructs a new Advanced Task with
     * the name of the inserted String
     *
     * @param name The name for the task
     */
    public AdvancedTask(String name) {
        labels = new ArrayList<>();
        task = new Task(name);
        priority = Priority.NONE;
    }


    /**
     * {@inheritDoc}
     */
    public String getName() {
        return task.getName();
    }

    /**
     * {@inheritDoc}
     */
    public void setName(String name) {
        task.setName(name);
    }

    /**
     * {@inheritDoc}
     */
    public List<ILabelCategory> getLabels() {
        return labels;
    }

    /**
     * {@inheritDoc}
     */
    public void addLabel(ILabelCategory label) {
        labels.add(label);
    }

    /**
     * {@inheritDoc}
     */
    public void removeLabel(ILabelCategory label) {
        if (labels.contains(label)) {
            labels.remove(label);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * {@inheritDoc}
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasNote() {
        return !note.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    public String getNote() {
        return note;
    }

    /**
     * {@inheritDoc}
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasPriority() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * {@inheritDoc}
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isChecked() {
        return task.isChecked();
    }

    /**
     * {@inheritDoc}
     */
    public void toggleChecked() {
        task.setChecked(!task.isChecked());
    }

    /**
     * {@inheritDoc}
     */
    public void setChecked(boolean value) {
        task.setChecked(value);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Calendar getCreationCalendar() {
        return task.getCreationCalendar();
    }

    @Override
    public void setCreationCalendar(Calendar calendar) {
        task.setCreationCalendar(calendar);
    }

    /**
     * Compares equality of the inserted Object o with
     * regards of Task name, Labels, Date and Note.
     *
     * @param o Object to be compared
     * @return True if all fields are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdvancedTask that = (AdvancedTask) o;


        if (!task.equals(that.task)) return false;
        if (!labels.equals(that.labels)) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        return priority == that.priority;
    }

    /**
     * Creates a Hash Code based on the Task's Hash Code,
     * the Hash Code of the Labels, Note and Priority fields.
     *
     * @return Hash Code for this object.
     */
    @Override
    public int hashCode() {
        int result = task.hashCode();
        result = 31 * result + labels.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        return result;
    }
}
