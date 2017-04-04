package cbstudios.coffeebreak;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.ToDoDataModule;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.LabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.AdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ListTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.Task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Zack on 2017-04-04.
 */

public class ToDoDataModuleTest {
    private ToDoDataModule toDoDataModule;

    @Before
    public void beforeTest() {
        toDoDataModule = new Model().getToDoDataModule();
        toDoDataModule.initialize();
    }

    @Test
    public void testAddLabelCategory() {
        // TODO: 2017-04-04 Lägg till getLabelCategories så man kan testa detta
    }

    @Test
    public void testGetTimeCategories() {
        assertTrue(toDoDataModule.getTimeCategories() != null);
        assertTrue(toDoDataModule.getTimeCategories().size() == 4);
    }

    @Test
    public void testRemoveLabelCategory() {
        // TODO: 2017-04-04 Metoder finns ej för att testa detta än
    }

    @Test
    public void testAddTask() {
        assertTrue(toDoDataModule.getTasks().size() == 0);
        toDoDataModule.addTask(new AdvancedTask("Test"));
        assertTrue(toDoDataModule.getTasks().size() == 1);
        toDoDataModule.addTask(new ListTask("Test"));
        assertTrue(toDoDataModule.getTasks().size() == 2);
        toDoDataModule.addTask(new AdvancedTask("Test2"));
        assertTrue(toDoDataModule.getTasks().size() == 3);
        toDoDataModule.addTask(new ListTask("Test2"));
        assertTrue(toDoDataModule.getTasks().size() == 4);
    }

    @Test
    public void testIsListTask() {
        assertTrue(toDoDataModule.isListTask(new ListTask("Test")));
        assertFalse(toDoDataModule.isListTask(new AdvancedTask("Test")));
    }

    @Test
    public void testRemoveTask() {
        IAdvancedTask at = new AdvancedTask("Test");
        IAdvancedTask at2 = new ListTask("Test");
        IAdvancedTask at3 = new AdvancedTask("Test");
        IAdvancedTask at4 = new ListTask("Test");
        toDoDataModule.addTask(at);
        toDoDataModule.addTask(at2);
        toDoDataModule.addTask(at3);
        toDoDataModule.addTask(at4);
        toDoDataModule.removeTask(at2);
        toDoDataModule.removeTask(new AdvancedTask("Test"));

        assertTrue(toDoDataModule.getTasks().size() == 3);
        assertTrue(toDoDataModule.getTasks().get(0) instanceof AdvancedTask);
        assertTrue(toDoDataModule.getTasks().get(1) instanceof AdvancedTask);
        assertTrue(toDoDataModule.getTasks().get(2) instanceof ListTask);

        toDoDataModule.removeTask(at);
        toDoDataModule.removeTask(at2);
        toDoDataModule.removeTask(new ListTask("Test"));

        assertTrue(toDoDataModule.getTasks().size() == 2);
        assertTrue(toDoDataModule.getTasks().get(0) instanceof AdvancedTask);
        assertTrue(toDoDataModule.getTasks().get(1) instanceof ListTask);

        toDoDataModule.removeTask(at3);
        toDoDataModule.removeTask(at4);
        assertTrue(toDoDataModule.getTasks().size() == 0);

        assertFalse(toDoDataModule.getTasks().get(0) instanceof AdvancedTask);
    }
}
