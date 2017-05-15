package cbstudios.coffeebreak;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.AdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.TaskFactory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ToDoList;
import cbstudios.coffeebreak.storage.StorageUtil;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by johan on 4/20/2017.
 */

public class SaveAndLoadTest {

    ToDoList toDoList = new ToDoList();
    List<IAdvancedTask> loadedList = new ArrayList<>();
    List<IAdvancedTask> orgList = new ArrayList<>();
    List<ILabelCategory> labelCategories = new ArrayList<>();

    @Before
    public void beforeTest() {
        labelCategories.add(0, CategoryFactory.getInstance().createLabelCategory("Work"));
        labelCategories.add(1, CategoryFactory.getInstance().createLabelCategory("Home"));
    }

    @Test
    public void testSaveAndLoad(){
        Calendar today = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);


        AdvancedTask mockTask1 = (AdvancedTask) TaskFactory.getInstance().createAdvancedTask();
        mockTask1.setName("Task1");
        mockTask1.addLabel(labelCategories.get(0));
        mockTask1.setDate(today);
        mockTask1.setNote("Note1");
        mockTask1.setPriority(Priority.ONE);
        mockTask1.setChecked(false);

        AdvancedTask mockTask2 = (AdvancedTask) TaskFactory.getInstance().createAdvancedTask();
        mockTask2.setName("Task2");
        mockTask2.addLabel(labelCategories.get(0));
        mockTask2.addLabel(labelCategories.get(1));
        mockTask2.setDate(tomorrow);
        mockTask2.setNote("Note2");
        mockTask2.setPriority(Priority.THREE);
        mockTask2.setChecked(true);

        orgList.add(mockTask1);
        orgList.add(mockTask2);

        assertTrue(loadedList.size() == orgList.size());

        IAdvancedTask loadedMockTask1 = loadedList.get(0);
        assertTrue(loadedMockTask1.getName().equals(mockTask1.getName()));
        assertTrue(loadedMockTask1.getLabels().equals(mockTask1.getLabels()));
        assertTrue(loadedMockTask1.getDate().equals(mockTask1.getDate()));
        assertTrue(loadedMockTask1.getNote().equals(mockTask1.getNote()));
        assertTrue(loadedMockTask1.getPriority().equals(mockTask1.getPriority()));
        assertFalse(loadedMockTask1.isChecked());

        IAdvancedTask loadedMockTask2 = loadedList.get(1);
        assertTrue(loadedMockTask2.getName().equals(mockTask2.getName()));
        assertTrue(loadedMockTask2.getLabels().equals(mockTask2.getLabels()));
        assertTrue(loadedMockTask2.getDate().equals(mockTask2.getDate()));
        assertTrue(loadedMockTask2.getNote().equals(mockTask2.getNote()));
        assertTrue(loadedMockTask2.getPriority().equals(mockTask2.getPriority()));
        assertTrue(loadedMockTask2.isChecked());

        //Some assertFalse to check that not everything is true
        assertFalse(loadedMockTask1.getName().equals(mockTask2));
        assertFalse(loadedMockTask2.getPriority().equals(mockTask1.getPriority()));
        assertFalse(loadedMockTask2.getLabels().equals(mockTask1.getLabels()));

    }

}
