package cbstudios.coffeebreak.model.tododatamodule.todolist;
//
//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.toDoList.ListTask.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 

import java.util.ArrayList;
import java.util.List;

class ListTask extends AdvancedTask implements IListTask {
    private final List<ITask> subtasks;

    ListTask() {
        subtasks = new ArrayList<>();
    }

    ListTask(String name) {
        super(name);
        subtasks = new ArrayList<>();
    }

    public List<ITask> getSubtasks() {
        return subtasks;
    }

    public void add(ITask subtask) {
        subtasks.add(subtask);
    }

    public void remove(ITask subtask) {
        int index = 0;
        for (int i = 0; i < subtasks.size(); i++) {
            if (subtasks.get(i) == task) {
                index = i;
                break;
            }
        }
        subtasks.remove(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ListTask listTask = (ListTask) o;

        return subtasks.equals(listTask.subtasks);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + subtasks.hashCode();
        return result;
    }
}
