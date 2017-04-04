package cbstudios.coffeebreak;

import cbstudios.coffeebreak.model.tododatamodule.todolist.ITask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITaskFactory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.TaskFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lenovo on 2017-04-04.
 */
public class TaskFactoryTest {
    ITaskFactory factory;
    @BeforeClass
    public void initFactory(){
        factory = TaskFactory.getInstance();
    }
    @Test
    public void TestFactoryBuild_default() throws Exception {
        ITask sampleTask = factory.createTask();

    }

}
