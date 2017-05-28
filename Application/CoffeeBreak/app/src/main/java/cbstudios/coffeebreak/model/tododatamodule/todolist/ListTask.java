package cbstudios.coffeebreak.model.tododatamodule.todolist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Represent instances of tasks of type List Task, which are contained in
 *          the list of displayed tasks.</br>
 *          Uses: Task class for delegating the name, checked value and creation time attributes.
 *          AdvancedTask for all features that class has.
 *          Priority enum attribute for defining the specific priority.
 *          Implements the IListTask interface</br>
 *          Used by: TaskFactory class for creation of ListTasks.
 *          Different controllers and subsequent classes through the IListTask as well as the
 *          IAdvancedTask interface.
 *          </p>
 */
class ListTask extends AdvancedTask implements IListTask {
    private final List<ITask> subtasks;

    ListTask() {
        subtasks = new ArrayList<>();
    }

    ListTask(String name) {
        super(name);
        subtasks = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ITask> getSubtasks() {
        return subtasks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(ITask subtask) {
        subtasks.add(subtask);
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){ return false;}
        if (!super.equals(o)){ return false;}

        ListTask listTask = (ListTask) o;

        return subtasks.equals(listTask.subtasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + subtasks.hashCode();
        return result;
    }
}
