package cbstudios.coffeebreak.model.tododatamodule.todolist;//
//
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.toDoList.AdvancedTask.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author :

import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

import java.util.Calendar;

public class AdvancedTask implements IAdvancedTask {
    protected ITask task;
    private List<ILabelCategory> labels;
    private Calendar time;
    private String note;
    private Priority priority;

    public ITask getTask() {
        return task;
    }

    public void setTask(ITask task) {
        this.task = task;
    }

    public List<ILabelCategory> getLabels() {
        return labels;
    }

    public void setLabels(List<ILabelCategory> labels) {
        this.labels = labels;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public void toggleChecked();

    public void setChecked(boolean value){

    }
}
