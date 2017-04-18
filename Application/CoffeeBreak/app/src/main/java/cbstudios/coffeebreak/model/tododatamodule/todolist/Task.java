package cbstudios.coffeebreak.model.tododatamodule.todolist;

import java.util.Calendar;

//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.toDoList.Task.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 
//
//
public class Task implements ITask {
    private String name;
    private boolean checked;
    private Calendar creationCalendar;

    public Task(){
        name = null;
        checked = false;
        creationCalendar = Calendar.getInstance();
    }

    public Task(String name){
        this.name = name;
        checked = false;
        creationCalendar = Calendar.getInstance();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void toggleChecked() {
        setChecked(!isChecked());
    }

    public void setChecked(boolean value) {
        checked = value;
    }

    public Calendar getCreationCalendar(){
        return creationCalendar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (checked != task.checked) return false;
        return name != null ? name.equals(task.name) : task.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (checked ? 1 : 0);
        return result;
    }
}
