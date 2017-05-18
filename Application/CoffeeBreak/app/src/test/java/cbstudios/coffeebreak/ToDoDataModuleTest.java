package cbstudios.coffeebreak;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.ToDoDataModule;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IListTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.TaskFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ToDoDataModuleTest {
    private ToDoDataModule toDoDataModule;

    @Before
    public void beforeTest() {
        toDoDataModule = new Model().getToDoDataModule();
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
        toDoDataModule.addTask(TaskFactory.getInstance().createAdvancedTask("Test"));
        assertTrue(toDoDataModule.getTasks().size() == 1);
        toDoDataModule.addTask(TaskFactory.getInstance().createListTask("Test"));
        assertTrue(toDoDataModule.getTasks().size() == 2);
        toDoDataModule.addTask(TaskFactory.getInstance().createAdvancedTask("Test2"));
        assertTrue(toDoDataModule.getTasks().size() == 3);
        toDoDataModule.addTask(TaskFactory.getInstance().createListTask("Test2"));
        assertTrue(toDoDataModule.getTasks().size() == 4);
    }

    @Test
    public void testIsListTask() {
        assertTrue(toDoDataModule.isListTask(TaskFactory.getInstance().createListTask("Test")));
        assertFalse(toDoDataModule.isListTask(TaskFactory.getInstance().createAdvancedTask("Test")));
    }

    @Test
    public void testRemoveTask() {
        IAdvancedTask at = TaskFactory.getInstance().createAdvancedTask("Test");
        IAdvancedTask at2 = TaskFactory.getInstance().createListTask("Test");
        IAdvancedTask at3 = TaskFactory.getInstance().createAdvancedTask("Test");
        IAdvancedTask at4 = TaskFactory.getInstance().createListTask("Test");
        toDoDataModule.addTask(at);
        toDoDataModule.addTask(at2);
        toDoDataModule.addTask(at3);
        toDoDataModule.addTask(at4);
        toDoDataModule.removeTask(at2);
        toDoDataModule.removeTask(TaskFactory.getInstance().createListTask("Test"));

        assertTrue(toDoDataModule.getTasks().size() == 3);
        assertTrue(toDoDataModule.getTasks().get(0) instanceof IAdvancedTask);
        assertTrue(toDoDataModule.getTasks().get(1) instanceof IAdvancedTask);
        assertTrue(toDoDataModule.getTasks().get(2) instanceof IListTask);

        toDoDataModule.removeTask(at);
        toDoDataModule.removeTask(at2);
        toDoDataModule.removeTask(TaskFactory.getInstance().createListTask("Test"));

        assertTrue(toDoDataModule.getTasks().size() == 2);
        assertTrue(toDoDataModule.getTasks().get(0) instanceof IAdvancedTask);
        assertTrue(toDoDataModule.getTasks().get(1) instanceof IListTask);

        toDoDataModule.removeTask(at3);
        toDoDataModule.removeTask(at4);
        assertTrue(toDoDataModule.getTasks().size() == 0);

        assertTrue(toDoDataModule.getTasks().isEmpty());
    }
}