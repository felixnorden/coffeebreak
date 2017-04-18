package cbstudios.coffeebreak.model.tododatamodule.todolist;

import java.util.Calendar;

//
//  @ Project : Untitled
//  @ File Name : ITask.java
//  @ Date : 03/04/2017
//  @ Author :
//
//
public interface ITask {
    public String getName();

    public void setName(String name);

    public boolean isChecked();

    public void toggleChecked();

    public void setChecked(boolean checked);

    public Calendar getCreationCalendar();

    public boolean equals(Object o);

    public int hashCode();
}
