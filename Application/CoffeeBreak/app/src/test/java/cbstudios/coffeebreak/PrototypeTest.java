package cbstudios.coffeebreak;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.TaskFactory;

/**
 * Created by elias on 06/04/2017.
 */

public class PrototypeTest {
    public Model model;

    @Before
    public void beforeTest(){
        model = new Model();
    }

    @Test
    public void initializeTest(){
        assertTrue(model.getToDoDataModule().getLabelCategories().size() == 3 );
        assertTrue(model.getToDoDataModule().getTimeCategories().size() == 4);
    }

    @Test
    public void addAndNameNewTask(){
        model.getToDoDataModule().addTask(TaskFactory.getInstance().createAdvancedTask());
        assertTrue(model.getToDoDataModule().getTasks().size() == 1);

        //Check assigning a name to the created task
        IAdvancedTask task = model.getToDoDataModule().getTask(0);
        task.setName("mock");
        assertTrue(model.getToDoDataModule().getTask(0).getName().equals("mock"));

        //Test that task is not checked
        assertFalse(task.isChecked());

        //Test if we can check/uncheck a task
        task.toggleChecked();
        assertTrue(task.isChecked());
        task.setChecked(false);
        assertFalse(task.isChecked());

        //Test if checked tasks is removed when we change view
        task.setChecked(true);
        model.getToDoDataModule().removeChecked();
        assertTrue(model.getToDoDataModule().getTasks().isEmpty());
    }

    
}