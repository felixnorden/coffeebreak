package cbstudios.coffeebreak.model.tododatamodule.todolist;

import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

import java.util.Calendar;
import java.util.List;

//
//  @ Project : Untitled
//  @ File Name : IAdvancedTask.java
//  @ Date : 03/04/2017
//  @ Author : 
//
//
public interface IAdvancedTask {
    //Remove??
    public ITask getTask();
    //Remove???
    public void setTask(ITask task);

    public String getName();

    public void setName(String name);

    public Calendar getDate();

    public void setDate(Calendar date);

    public List<ILabelCategory> getLabels();

    public void addLabel(ILabelCategory label);

    public void removeLabel(ILabelCategory label);

    public boolean hasNote();

    public String getNote();

    public void setNote(String note);

    public boolean hasPriority();

    public Priority getPriority();

    public void setPriority(Priority priority);

    public boolean isChecked();

    public void toggleChecked();

    public void setChecked(boolean value);
}
