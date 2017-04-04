package cbstudios.coffeebreak.model.tododatamodule.todolist;//
//
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.toDoList.AdvancedTask.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author :

import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class AdvancedTask implements IAdvancedTask {
    protected ITask task;
    private final List<ILabelCategory> labels;
    private Calendar date;
    private String note;
    private Priority priority;

    public AdvancedTask(){
        labels = new ArrayList<>();
        task = new Task();
    }

    public AdvancedTask(String name){
        labels = new ArrayList<>();
        task = new Task(name);
    }

    public ITask getTask() {
        return task;
    }

    public void setTask(ITask task) {
        this.task = task;
    }

    public String getName() {
        return task.getName();
    }

    public void setName(String name) {
        task.setName(name);
    }

    public List<ILabelCategory> getLabels() {
        return labels;
    }

    public void addLabel(ILabelCategory label){
        labels.add(label);
    }

    public void removeLabel(ILabelCategory label){
        if(labels.contains(label)){
            labels.remove(label);
        }
    }
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public boolean hasNote() {
        return !note.isEmpty();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean hasPriority() {
        return false;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isChecked(){
        return task.isChecked();
    }

    public void toggleChecked(){
        task.setChecked(!task.isChecked());
    }

    public void setChecked(boolean value){
        task.setChecked(value);
    }

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
