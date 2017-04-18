package cbstudios.coffeebreak;

import org.junit.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;

import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.TaskSorter;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITaskFactory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.TaskFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Felix on 2017-04-18.
 * <p>
 * Testing for sorting utility class with regard to priority, alphabetical and chronological order.
 */

public class TaskSorterTest {
    private static ITaskFactory factory = TaskFactory.getInstance();
    private static final List<IAdvancedTask> tasks = new ArrayList<>();
    private static final TaskSorter sorter = new TaskSorter();
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

        //TODO mockito implementation.
        tasks.get(0).getCreationCalendar().set(2016,7,5);
        tasks.get(1).getCreationCalendar().set(2016,5,10);
        tasks.get(3).getCreationCalendar().set(2017,2,15);
        tasks.get(4).getCreationCalendar().set(2022,3,16);
    //Set priorities for a few tasks
        /*tasks.get(0).setPriority(Priority.ONE);
        tasks.get(2).setPriority(Priority.TWO);
        tasks.get(4).setPriority(Priority.THREE);*/
    }

    @Before
    public void initTasksCopy() {
        listCopy = new ArrayList<>(tasks);
    }

    @Test
    public void testAlphabeticallySort() {
        sorter.sortAlphabetically(listCopy);

        for (int i = 0; i < tasks.size(); i++) {
            assertTrue(listCopy.get(i).equals(tasks.get(tasks.size() - 1 - i)));
        }
        assertFalse(listCopy.get(0).getName().equals(tasks.get(0).getName()));
    }

    @Test
    public void testChronologicallySort() {
        //swap(listCopy);
        sorter.sortChronologically(listCopy);
        assertEquals(listCopy.get(0), tasks.get(1));
        assertEquals(listCopy.get(1), tasks.get(0));
        assertEquals(listCopy.get(2), tasks.get(3));
        assertEquals(listCopy.get(3), tasks.get(2));
        assertEquals(listCopy.get(4), tasks.get(4));
    }


}
