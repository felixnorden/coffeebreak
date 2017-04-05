package cbstudios.coffeebreak;


import cbstudios.coffeebreak.model.tododatamodule.todolist.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by Lenovo on 2017-04-04.
 */
public class TaskFactoryTest {
    private ITaskFactory factory;
    @Test
    public void TestFactoryBuild_default() throws Exception {
        factory = TaskFactory.getInstance();
        ITask sampleTask = factory.createTask();
        IAdvancedTask advancedTask = factory.createAdvancedTask();
        IListTask listTask = factory.createListTask();

        //Testing base task
        sampleTask.toggleChecked();
        sampleTask.setName("sample");
        assertEquals(sampleTask.isChecked(), true);

        //Testing advanced task with base task
        advancedTask.setTask(sampleTask);
        assertEquals(advancedTask.getName(), "sample");

        //Testing list task with base task
        listTask.add(sampleTask);
        listTask.add(new Task("sample2"));
        listTask.remove(sampleTask);
        assertEquals(listTask.getSubtasks().size(), 1);
        assertEquals(listTask.getSubtasks().get(0).getName(), "sample2");

    }

}
