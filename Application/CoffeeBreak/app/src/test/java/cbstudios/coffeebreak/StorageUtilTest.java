package cbstudios.coffeebreak;

import android.content.Context;
import android.graphics.Color;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cbstudios.coffeebreak.storage.StorageUtil;
import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.AdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ListTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.Task;

import static org.junit.Assert.assertTrue;

public class StorageUtilTest {
    @Mock
    Context cont;
    @Test
    public void testTaskSaveAndLoad() {
        List<IAdvancedTask> saveTasks = new ArrayList<>();

        Calendar currentCal = Calendar.getInstance();
        Calendar nextMonthCal = Calendar.getInstance();
        Calendar threeMonthCal = Calendar.getInstance();
        nextMonthCal.add(Calendar.DAY_OF_MONTH, 30);
        threeMonthCal.add(Calendar.MONTH, 2);

        List<ILabelCategory> labelCategories = new ArrayList<>();
        labelCategories.add(CategoryFactory.getInstance().createLabelCategory("Label"));
        labelCategories.add(CategoryFactory.getInstance().createLabelCategory("Label2"));

        ITask subTask1 = new Task("Subtask1");
        ITask subTask2 = new Task("Subtask2");
        subTask1.setChecked(false);
        subTask2.setChecked(true);

        AdvancedTask at1 = new AdvancedTask("AdvancedTask1");
        at1.setDate(currentCal);
        at1.addLabel(labelCategories.get(0));
        at1.setChecked(true);
        at1.setNote("Exempel");
        at1.setPriority(Priority.THREE);

        AdvancedTask at2 = new AdvancedTask("AdvancedTask2");
        at2.setDate(nextMonthCal);
        at2.addLabel(labelCategories.get(0));
        at2.addLabel(labelCategories.get(1));
        at2.setChecked(false);
        at2.setNote("");
        at2.setPriority(Priority.THREE);

        ListTask lt1 = new ListTask("ListTask1");
        lt1.setDate(threeMonthCal);
        lt1.addLabel(labelCategories.get(1));
        lt1.setChecked(true);
        lt1.setNote("Lorem ipsum");
        lt1.setPriority(Priority.ONE);
        lt1.add(subTask1);
        lt1.add(subTask2);

        saveTasks.add(at1);
        saveTasks.add(at2);
        saveTasks.add(lt1);

        StorageUtil.saveTasks(cont, saveTasks);
        List<IAdvancedTask> loadedTasks = StorageUtil.loadTasks(cont);

        assertTrue(saveTasks.equals(loadedTasks));
    }

    @Test
    public void testCategorySaveAndLoad() {
        List<ILabelCategory> save = new ArrayList<>();
        ILabelCategory c1 = CategoryFactory.getInstance().createLabelCategory("Name1");
        ILabelCategory c2 = CategoryFactory.getInstance().createLabelCategory("Name2");
        ILabelCategory c3 = CategoryFactory.getInstance().createLabelCategory("Name3");
        c1.setColor(Color.RED);
        c2.setColor(Color.BLACK);
        c3.setColor(Color.CYAN);
        StorageUtil.saveCategories(null, save);

        List<ILabelCategory> load = StorageUtil.loadCategories(null);

        assertTrue(load.equals(save));
    }
}
