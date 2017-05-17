package cbstudios.coffeebreak;

import org.junit.*;

/**
 * @author Felix
 * @version 1.0
 *
 * Tests for task classes' hashcode and equals methods.
 */


import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IListTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITaskFactory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.TaskFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TaskEqualsAndHashcodeTest {
    private static ITaskFactory taskFactory = TaskFactory.getInstance();
    private ITask task;
    private ITask task2;
    private ITask task3;
    private IAdvancedTask advancedTask;
    private IAdvancedTask advancedTask2;
    private IAdvancedTask advancedTask3;
    private IListTask listTask;
    private IListTask listTask2;
    private IListTask listTask3;

    @Before
    public void init(){
        task  = taskFactory.createTask("Buy apples");
        task2 = taskFactory.createTask();
        task3 = taskFactory.createTask(task.getName());

        advancedTask = taskFactory.createAdvancedTask(task.getName());
        advancedTask2 = taskFactory.createAdvancedTask();
        advancedTask2.setName(task.getName());
        advancedTask3 = taskFactory.createListTask(task3.getName());

        listTask = taskFactory.createListTask(task.getName());
        listTask2 = taskFactory.createListTask(advancedTask.getName());
        listTask3 = taskFactory.createListTask();

    }
    @Test
    public void testTaskHashCode(){
        assertTrue(task.hashCode() == task3.hashCode());
        assertFalse(task.hashCode() == task2.hashCode());
    }

    @Test
    public void testTaskEquals(){
        task.getCreationCalendar().set(2017,02,03);
        assertFalse(task.equals(task3));
        task = task3;
        assertTrue(task.equals(task3));
        assertFalse(task2.equals(task));
    }

    @Test
    public void testAdvHashCode(){
        assertFalse(advancedTask.hashCode() == advancedTask3.hashCode());
        assertTrue(advancedTask.hashCode() == advancedTask2.hashCode());
        assertFalse(advancedTask3.hashCode() == advancedTask2.hashCode());

        assertFalse(advancedTask.hashCode() == listTask.hashCode());
    }
    @Test
    public void testAdvancedEquals(){
        assertTrue(advancedTask.equals(advancedTask2));
        assertFalse(advancedTask2.equals(advancedTask3));
        assertFalse(advancedTask3.equals(advancedTask));

        assertFalse(advancedTask.equals(listTask));
    }

    @Test
    public void testListHashCode(){
        assertTrue(listTask.hashCode() == listTask2.hashCode());
        assertFalse(listTask2.hashCode() == listTask3.hashCode());
        assertFalse(listTask.hashCode() == listTask3.hashCode());
    }
    @Test
    public void testListEquals(){
        assertTrue(listTask.equals(listTask2));
        assertFalse(listTask2.equals(listTask3));
        assertFalse(listTask3.equals(listTask));
    }
}
