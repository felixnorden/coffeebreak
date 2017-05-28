package cbstudios.coffeebreak.model.tododatamodule.todolist;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

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
class AdvancedTask implements IAdvancedTask {
    protected final ITask task;
    private final List<ILabelCategory> labels;
    private Calendar date;
    private String note;
    private Priority priority;

    /**
     * Constructs a new Advanced Task with empty attributes
     */
    AdvancedTask() {
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
    AdvancedTask(String name) {
        labels = new ArrayList<>();
        task = new Task(name);
        priority = Priority.NONE;
    }


    @Override
    public String getName() {
        return task.getName();
    }

    @Override
    public void setName(String name) {
        task.setName(name);
    }

    @Override
    public List<ILabelCategory> getLabels() {
        return labels;
    }

    @Override
    public void addLabel(ILabelCategory label) {
        labels.add(label);
    }

    @Override
    public void removeLabel(ILabelCategory label) {
        if (labels.contains(label)) {
            labels.remove(label);
        }
    }

    @Override
    public Calendar getNotification() {
        return date;
    }

    @Override
    public void getNotification(Calendar date) {
        this.date = date;
    }

    @Override
    public boolean hasNote() {
        return note != null;
    }

    @Override
    public String getNote() {
        return note;
    }

    @Override
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public boolean isChecked() {
        return task.isChecked();
    }

    @Override
    public void toggleChecked() {
        task.setChecked(!task.isChecked());
    }

    @Override
    public void setChecked(boolean value) {
        task.setChecked(value);
    }


    @Override
    public Calendar getCreationCalendar() {
        return task.getCreationCalendar();
    }

    @Override
    public void setCreationCalendar(Calendar calendar) {
        task.setCreationCalendar(calendar);
    }

    @Override
    public boolean hasILabelCategory(String name) {
        for (ILabelCategory label : labels) {
            if (label.getName().equals(name)) {
                return true;
            }
        }
        return false;
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdvancedTask that = (AdvancedTask) o;


        if (!task.equals(that.task)) {
            return false;
        }
        if (!labels.equals(that.labels)) {
            return false;
        }
        return date != null ? date.equals(that.date) : that.date == null && (note != null ? note.equals(that.note) : that.note == null && priority == that.priority);
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
