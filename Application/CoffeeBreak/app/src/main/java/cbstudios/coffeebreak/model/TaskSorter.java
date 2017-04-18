package cbstudios.coffeebreak.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * Created by Felix on 2017-04-18.
 */

public class TaskSorter{
    public void sortAlphabetically(List<IAdvancedTask> list){
        Collections.sort(list, new AlphabeticalComparator());
    }

    public void sortChronologically(List<IAdvancedTask> list){
        Collections.sort(list, new ChronologicalComparator());
    }

    public void sortPriorities(List<IAdvancedTask> list){
        Collections.sort(list, new PriorityComparator());
    }

    private class AlphabeticalComparator implements Comparator<IAdvancedTask>{
        @Override
        public int compare(IAdvancedTask t1, IAdvancedTask t2) {
            return t1.getName().compareTo(t2.getName());
        }
    }
    private class ChronologicalComparator implements Comparator<IAdvancedTask>{
        @Override
        public int compare(IAdvancedTask t1, IAdvancedTask t2) {
            if(t1.getCreationCalendar().compareTo(t2.getCreationCalendar()) == 0)
                return t1.getName().compareTo(t2.getName());
            return t1.getCreationCalendar().getTime().compareTo(t2.getCreationCalendar().getTime());
        }
    }

    private class PriorityComparator implements Comparator<IAdvancedTask>{
        @Override
        public int compare(IAdvancedTask t1, IAdvancedTask t2) {
            if(t1.getPriority().compareTo(t2.getPriority()) == 0)
                return t1.getName().compareTo(t2.getName());
            return t1.getPriority().compareTo(t2.getPriority());
        }
    }
}
