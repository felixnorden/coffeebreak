package cbstudios.coffeebreak;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.TaskFactory;

import static org.junit.Assert.assertTrue;

public class UCChangeTaskConfiguration {

    private Model model;
    private IAdvancedTask task;

    @Before
    public void before() {
        model = new Model();
        task = TaskFactory.getInstance().createAdvancedTask("Test Name");
    }

    @Test
    public void testNameChange() {
        assertTrue(task.getName().equals("Test Name"));
        task.setName("New Test Name");
        assertTrue(task.getName().equals("New Test Name"));
    }

    @Test
    public void testNotificationChange() {
        assertTrue(task.getNotification() == null);

        Calendar cal = Calendar.getInstance();
        task.getNotification(cal);
        assertTrue(task.getNotification().equals(cal));

        task.getNotification(null);
        assertTrue(task.getNotification() == null);
    }

    @Test
    public void testLabelsChange() {
        assertTrue(task.getLabels().isEmpty());

        ILabelCategory labelOne = CategoryFactory.getInstance().createLabelCategory("Category1");
        ILabelCategory labelTwo = CategoryFactory.getInstance().createLabelCategory("Category2");
        ILabelCategory labelThree = CategoryFactory.getInstance().createLabelCategory("Category3");

        task.addLabel(labelOne);

        assertTrue(task.getLabels().size() == 1);
        assertTrue(task.getLabels().get(0).equals(labelOne));

        task.addLabel(labelTwo);

        assertTrue(task.getLabels().size() == 2);
        assertTrue(task.getLabels().get(0).equals(labelOne));
        assertTrue(task.getLabels().get(1).equals(labelTwo));

        task.addLabel(labelThree);

        assertTrue(task.getLabels().size() == 3);
        assertTrue(task.getLabels().get(0).equals(labelOne));
        assertTrue(task.getLabels().get(1).equals(labelTwo));
        assertTrue(task.getLabels().get(2).equals(labelThree));

        task.removeLabel(labelTwo);

        assertTrue(task.getLabels().size() == 2);
        assertTrue(task.getLabels().get(0).equals(labelOne));
        assertTrue(task.getLabels().get(1).equals(labelThree));

        task.removeLabel(labelOne);

        assertTrue(task.getLabels().size() == 1);
        assertTrue(task.getLabels().get(0).equals(labelThree));

        task.removeLabel(labelThree);

        assertTrue(task.getLabels().isEmpty());
    }

    @Test
    public void testPriorityChange() {
        assertTrue(task.getPriority().equals(Priority.NONE));

        task.setPriority(Priority.ONE);
        assertTrue(task.getPriority().equals(Priority.ONE));

        task.setPriority(Priority.TWO);
        assertTrue(task.getPriority().equals(Priority.TWO));

        task.setPriority(Priority.THREE);
        assertTrue(task.getPriority().equals(Priority.THREE));

        task.setPriority(Priority.NONE);
        assertTrue(task.getPriority().equals(Priority.NONE));
    }

    @Test
    public void testNoteChange() {
        String note = "TestNote";
        String anotherNote = "AnotherTestNote";

        assertTrue(task.getNote() == null);

        task.setNote(note);
        assertTrue(task.getNote().equals(note));

        task.setNote(anotherNote);
        assertTrue(task.getNote().equals(anotherNote));

        task.setNote(null);
        assertTrue(task.getNote() == null);
    }
}
