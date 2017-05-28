package cbstudios.coffeebreak;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.ToDoDataModule;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IListTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITaskFactory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.TaskFactory;

import static org.junit.Assert.assertEquals;
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
        // Make sure there are no Label categories
        assertTrue(toDoDataModule.getLabelCategories().isEmpty());

        toDoDataModule.addLabelCategory("Sample 1");
        toDoDataModule.addLabelCategory("Sample 2");

        // Check that categories have been stored and in correct order
        assertEquals(toDoDataModule.getLabelCategories().size(), 2);
        assertEquals(toDoDataModule.getLabelCategories().get(0).getName(), "Sample 1");
        assertEquals(toDoDataModule.getLabelCategories().get(1).getName(), "Sample 2");

        // Check that colors have been set correctly
        assertTrue(!toDoDataModule.getLabelCategory("Sample 1").getColor().equalsIgnoreCase(""));
        assertTrue(!toDoDataModule.getLabelCategory("Sample 1").getColor().equalsIgnoreCase(null));

    }

    @Test
    public void testGetTimeCategories() {
        assertTrue(toDoDataModule.getTimeCategories() != null);
        assertTrue(toDoDataModule.getTimeCategories().size() == 5);
    }

    @Test
    public void testRemoveLabelCategory() {
        toDoDataModule.addLabelCategory("Sample 1");
        toDoDataModule.addLabelCategory("Sample 2");
        toDoDataModule.addLabelCategory("Sample 3");

        assertEquals(toDoDataModule.getLabelCategories().size(), 3);

        //Remove a category and make sure the correct categories remain
        toDoDataModule.removeLabelCategory("Sample 2");

        assertEquals(toDoDataModule.getLabelCategories().size(), 2);
        assertTrue(toDoDataModule.getLabelCategories().get(0).getName().contentEquals("Sample 1"));
        assertTrue(toDoDataModule.getLabelCategories().get(1).getName().contentEquals("Sample 3"));

        toDoDataModule.removeLabelCategory("Sample 1");

        assertEquals(toDoDataModule.getLabelCategories().size(), 1);
        assertTrue(toDoDataModule.getLabelCategories().get(0).getName().contentEquals("Sample 3"));

        toDoDataModule.removeLabelCategory("Sample 3");

        assertTrue(toDoDataModule.getLabelCategories().isEmpty());
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
    @Test
    public void testUpdateToDoList(){
        ITaskFactory factory = TaskFactory.getInstance();
        for(int i = 0; i < 10; i++){
            int a = 65 + i;
            char c = (char) a;
            toDoDataModule.createAdvancedTask();
            toDoDataModule.getTask(i).setName(Character.toString(c));

            // Set every third task to checked
            if(i % 3 == 0)
                toDoDataModule.getTask(i).toggleChecked();
        }

        // Check correct number of tasks before removal
        assertEquals(toDoDataModule.getTasks().size(), 10);

        // Update/remove checked tasks
        for(IAdvancedTask task: toDoDataModule.getTasks()){
            if(task.isChecked())
                toDoDataModule.removeTask(task);
        }

        // Check correct number of tasks after removal
        assertEquals(toDoDataModule.getTasks().size(), 6);

        // Make sure that all tasks removed are ones that
        // had index i % 3 = 0
        assertEquals(toDoDataModule.getTask(0).getName(), "B");
        assertEquals(toDoDataModule.getTask(1).getName(), "C");
        assertEquals(toDoDataModule.getTask(2).getName(), "E");
        assertEquals(toDoDataModule.getTask(3).getName(), "F");
        assertEquals(toDoDataModule.getTask(4).getName(), "H");
        assertEquals(toDoDataModule.getTask(5).getName(), "I");
    }

    @Test
    public void testFilterTasksByCategory(){

        List<IAdvancedTask> filteredList;
        //Add different labels
        toDoDataModule.addLabelCategory("Sample 1");
        toDoDataModule.addLabelCategory("Sample 2");
        toDoDataModule.addLabelCategory("Sample 3");

        //Create tasks and assign them names and labels
        for( int i = 0; i < 10; i++ ){
            toDoDataModule.createAdvancedTask();
            char c = (char) (65 + i);
            IAdvancedTask task = toDoDataModule.getTask(i);

            task.setName(Character.toString(c));

            if(i % 1 == 0)
                task.addLabel(toDoDataModule.getLabelCategory("Sample 1"));
            if(i % 2 == 0)
                task.addLabel(toDoDataModule.getLabelCategory("Sample 2"));
            if(i % 3 == 0)
                task.addLabel(toDoDataModule.getLabelCategory("Sample 3"));

        }

        assertEquals(toDoDataModule.getTasks().size(), 10);
        assertEquals(toDoDataModule.getLabelCategories().size(), 3);

        List<IAdvancedTask> label_1 = toDoDataModule.getLabelCategory("Sample 1").getValidTasks(toDoDataModule.getTasks());
        List<IAdvancedTask> label_2 = toDoDataModule.getLabelCategory("Sample 2").getValidTasks(toDoDataModule.getTasks());
        List<IAdvancedTask> label_3 = toDoDataModule.getLabelCategory("Sample 3").getValidTasks(toDoDataModule.getTasks());

        // Make sure correct amount of tasks are filtered
        assertEquals(label_1.size(), 10);
        assertEquals(label_2.size(), 5);
        assertEquals(label_3.size(), 4);

        // Check the tasks in each list
        for(int i = 0; i < label_1.size(); i++){
            IAdvancedTask task = label_1.get(i);
            char c = (char) (65+i);

            assertEquals(task.getName(), Character.toString(c));
        }
        // Check the tasks in each list
        for(int i = 0; i < label_2.size(); i++){
            IAdvancedTask task = label_2.get(i);
            char c = (char) (65 + 2*i);

            assertEquals(task.getName(), Character.toString(c));
        }
        // Check the tasks in each list
        for(int i = 0; i < label_3.size(); i++){
            IAdvancedTask task = label_3.get(i);
            char c = (char) (65 + 3*i);

            assertEquals(task.getName(), Character.toString(c));
        }

    }
}