package cbstudios.coffeebreak;

import org.junit.Before;
import org.junit.Test;

import cbstudios.coffeebreak.model.Model;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by johan on 5/2/2017.
 */

public class UcCreateAndCheckTaskTest {
    private Model model = new Model();

    @Before
    public void beforeTest(){
        model.getToDoDataModule().createTask();
        model.getToDoDataModule().getTask(0).setName("Test");
        model.getToDoDataModule().getTask(0).toggleChecked();
    }

    @Test
    public void TestUC(){
        assertFalse(model.getToDoDataModule().getTasks().isEmpty());
        assertTrue(model.getToDoDataModule().getTasks().size() == 1);
        assertTrue(model.getToDoDataModule().getTask(0).getName().equals("Test"));
        assertTrue(model.getToDoDataModule().getTask(0).isChecked());

        model.getToDoDataModule().getTask(0).toggleChecked();
        assertFalse(model.getToDoDataModule().getTask(0).isChecked());

        model.getToDoDataModule().getTask(0).setName("NotTest");
        assertFalse(model.getToDoDataModule().getTask(0).getName().equals("Test"));

        assertTrue(model.getToDoDataModule().getTasks().size() == 1);
    }

}
