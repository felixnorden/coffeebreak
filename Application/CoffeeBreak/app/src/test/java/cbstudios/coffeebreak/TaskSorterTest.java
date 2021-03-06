package cbstudios.coffeebreak;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.TaskSorter;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITaskFactory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.TaskFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by Felix on 2017-04-18.
 * <p>
 * Testing for sorting utility class with regard to priority, alphabetical and chronological order.
 */

public class TaskSorterTest {
    private static ITaskFactory factory = TaskFactory.getInstance();
    private static final List<IAdvancedTask> tasks = new ArrayList<>();
    private static final TaskSorter sorter = TaskSorter.getInstance();
    private static List<IAdvancedTask> listCopy;

    @BeforeClass
    public static void init() {
        for (int i = 0; i < 5; i++){
            // Set name from A through E backwards
            int a = 69 - i;
            char c = (char) a;
            IAdvancedTask task = factory.createAdvancedTask(Character.toString(c));
            tasks.add(task);
        }

        tasks.get(0).getCreationCalendar().set(2016,7,5);
        tasks.get(1).getCreationCalendar().set(2016,5,10);
        tasks.get(3).getCreationCalendar().set(2017,2,15);
        tasks.get(4).getCreationCalendar().set(2022,3,16);
        //Set priorities for a few tasks
        tasks.get(0).setPriority(Priority.ONE);
        tasks.get(2).setPriority(Priority.TWO);
        tasks.get(4).setPriority(Priority.THREE);
    }

    @Before
    public void initTasksCopy() {
        listCopy = new ArrayList<>(tasks);
    }

    @Test
    public void testAlphabeticallySort() {
        sorter.sortAlphabetically(listCopy);
        
        //Make sure that order is reversed, due to names being E-A in original list.
        for (int i = 0; i < tasks.size(); i++) {
            assertTrue(listCopy.get(i).equals(tasks.get(tasks.size() - 1 - i)));
        }
        assertFalse(listCopy.get(0).getName().equals(tasks.get(0).getName()));
    }

    @Test
    public void testChronologicallySort() {
        sorter.sortChronologically(listCopy);
        assertEquals(listCopy.get(0), tasks.get(1));
        assertEquals(listCopy.get(1), tasks.get(0));
        assertEquals(listCopy.get(2), tasks.get(3));
        assertEquals(listCopy.get(3), tasks.get(2));
        assertEquals(listCopy.get(4), tasks.get(4));

        assertFalse(listCopy.get(4).equals(tasks.get(0)));
        assertFalse(listCopy.get(3).equals(tasks.get(3)));
        assertFalse(listCopy.get(4).equals(tasks.get(2)));
        assertFalse(listCopy.get(1).equals(tasks.get(1)));
    }

    @Test
    public void testPrioritySort(){
        sorter.sortPriorities(listCopy);

        assertEquals(listCopy.get(0), tasks.get(0));
        assertEquals(listCopy.get(1), tasks.get(2));
        assertEquals(listCopy.get(2), tasks.get(4));
        assertEquals(listCopy.get(3), tasks.get(3));
        assertEquals(listCopy.get(4), tasks.get(1));

        //Control Check that priorities are firstly sorted
        //then also alphabetically in each priority
        assertEquals(listCopy.get(0).getPriority(), Priority.ONE);
        assertEquals(listCopy.get(2).getPriority(), Priority.THREE);
        assertEquals(listCopy.get(4).getPriority(), Priority.NONE);
        assertEquals(listCopy.get(4).getName(), "D");
    }
}
