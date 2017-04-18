package cbstudios.coffeebreak;

import org.junit.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.TaskSorter;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITaskFactory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.TaskFactory;

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
        swap(listCopy);
        for (int i = 0; i < tasks.size() - 1; i++) {
            System.out.println(listCopy.get(i).getCreationCalendar().toString());
        }
        sorter.sortChronologically(listCopy);
    }

    private void swap(List<IAdvancedTask> list) {
        IAdvancedTask t1, t2, t3, t4;
        t1 = list.get(0);
        t2 = list.get(2);
        t3 = list.get(3);
        t4 = list.get(4);

        list.remove(t1);
        list.remove(t3);
        list.remove(t4);
        list.add(t4);
        list.add(t2);
        list.add(t3);
    }

}
