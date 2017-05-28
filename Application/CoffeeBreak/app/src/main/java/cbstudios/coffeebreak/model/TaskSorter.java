package cbstudios.coffeebreak.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * @author Felix
 * @version 1.0
 *          <p>Responsibility: Handle sorting of tasks in a given list, depending of the wanted sorting order.</br>
 *          Uses: Comparison by implementing the Comparator library, and sorts using the Collections library</br>
 *          Used by: ToDoDatamodule which is in return used by controllers outside the Model.
 *          </p>
 */

public class TaskSorter {

    private final static TaskSorter INSTANCE = new TaskSorter();

    /**
     * Fetches the singleton instance of the TaskSorter
     *
     * @return the instance of the TaskSorter singleton
     */
    public static TaskSorter getInstance() {
        return INSTANCE;
    }

    /**
     * Sorts the given list in a alphabetical order based on the tasks'
     * names. The order given is from A-Z
     *
     * @param list the list of tasks to be sorted
     */
    public void sortAlphabetically(List<IAdvancedTask> list) {
        Collections.sort(list, new AlphabeticalComparator());
    }

    /**
     * Sorts the given list in a chronological order based on the time
     * that the tasks were created. The order is from older to newer.
     * Subsequent order is based on alphabetical order from A-Z.
     *
     * @param list the list of tasks to be sorted
     */
    public void sortChronologically(List<IAdvancedTask> list) {
        Collections.sort(list, new ChronologicalComparator());
    }

    /**
     * Sorts the given list in a priority based order. The resulting
     * order is from highest to no priority of the list's tasks.
     * Subsequent order is based on alphabetical order from A-Z.
     *
     * @param list the list of tasks to be sorted
     */
    public void sortPriorities(List<IAdvancedTask> list) {
        Collections.sort(list, new PriorityComparator());
    }

    /**
     * Private constructor because singleton.
     */
    private TaskSorter() {
    }

    /**
     * A comparator which compares tasks to each other in regards their names.
     */
    private class AlphabeticalComparator implements Comparator<IAdvancedTask> {
        @Override
        public int compare(IAdvancedTask t1, IAdvancedTask t2) {
            return t1.getName().compareTo(t2.getName());
        }
    }

    /**
     * A comparator which compares tasks to each other in regards to when they were created.
     */
    private class ChronologicalComparator implements Comparator<IAdvancedTask> {
        @Override
        public int compare(IAdvancedTask t1, IAdvancedTask t2) {
            if (t1.getCreationCalendar().compareTo(t2.getCreationCalendar()) == 0) {
                return t1.getName().compareTo(t2.getName());
            }
            return t1.getCreationCalendar().getTime().compareTo(t2.getCreationCalendar().getTime());
        }
    }

    /**
     * A comparator which compares tasks to each other in regards to their priorities.
     */
    private class PriorityComparator implements Comparator<IAdvancedTask> {
        @Override
        public int compare(IAdvancedTask t1, IAdvancedTask t2) {
            if (t1.getPriority().compareTo(t2.getPriority()) == 0) {
                return t1.getName().compareTo(t2.getName());
            }
            return t1.getPriority().compareTo(t2.getPriority());
        }
    }
}
