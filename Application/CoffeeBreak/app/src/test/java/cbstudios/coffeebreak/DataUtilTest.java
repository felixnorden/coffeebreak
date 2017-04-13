package cbstudios.coffeebreak;

import android.graphics.Color;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cbstudios.coffeebreak.model.DataUtil;
import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.AdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ListTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.Task;

import static org.junit.Assert.assertTrue;

public class DataUtilTest {

    @Test
    public void testTaskSaveAndLoad() {
        List<IAdvancedTask> saveTasks = new ArrayList<>();

        Calendar currentCal = Calendar.getInstance();
        Calendar nextMonthCal = Calendar.getInstance();
        Calendar threeMonthCal = Calendar.getInstance();
        nextMonthCal.add(Calendar.DAY_OF_MONTH, 30);
        threeMonthCal.add(Calendar.MONTH, 2);

        List<ILabelCategory> labelCategories = new ArrayList<>();
        labelCategories.add(CategoryFactory.getInstance().createLabelCategory("Label", Color.BLUE));
        labelCategories.add(CategoryFactory.getInstance().createLabelCategory("Label2", Color.RED));

        List<ITask> subTasks = new ArrayList<>();
        ITask subTask1 = new Task("Subtask1");
        ITask subTask2 = new Task("Subtask2");
        subTask1.setChecked(false);
        subTask2.setChecked(true);
        subTasks.add(subTask1);
        subTasks.add(subTask2);

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
        at2.setPriority(Priority.TWO);

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


        //Because of JVM (no emulator) testing, no context is available (context = null) .
        DataUtil.saveTasksToFile(null, saveTasks);
        List<IAdvancedTask> loadedTasks = DataUtil.loadTasks();

        assertTrue(loadedTasks.size() == 3);

        IAdvancedTask loaded1 = loadedTasks.get(0);
        IAdvancedTask loaded2 = loadedTasks.get(1);
        ListTask loaded3 = (ListTask) loadedTasks.get(2);

        assertTrue(loaded1.getName().equals("AdvancedTask1"));
        assertTrue(loaded1.hasNote());
        assertTrue(loaded1.getNote().equals("Exempel"));
        assertTrue(loaded1.isChecked());
        assertTrue(loaded1.hasPriority());
        assertTrue(loaded1.getPriority().equals(Priority.THREE));
        assertTrue(loaded1.getDate().compareTo(currentCal) == 0);
        assertTrue(loaded1.getLabels().get(0).equals(labelCategories.get(0)));

        assertTrue(loaded2.getName().equals("AdvancedTask2"));
        assertTrue(!loaded2.hasNote());
        assertTrue(loaded2.getNote().equals(""));
        assertTrue(!loaded2.isChecked());
        assertTrue(loaded2.hasPriority());
        assertTrue(loaded2.getPriority().equals(Priority.TWO));
        assertTrue(loaded2.getDate().compareTo(nextMonthCal) == 0);
        assertTrue(loaded2.getLabels().equals(labelCategories));

        assertTrue(loaded3.getName().equals("ListTask1"));
        assertTrue(loaded3.hasNote());
        assertTrue(loaded3.getNote().equals("Lorem ipsum"));
        assertTrue(loaded3.isChecked());
        assertTrue(loaded3.hasPriority());
        assertTrue(loaded3.getPriority().equals(Priority.ONE));
        assertTrue(loaded3.getDate().compareTo(threeMonthCal) == 0);
        assertTrue(loaded3.getLabels().get(0).equals(labelCategories.get(1)));
        assertTrue(loaded3.getSubtasks().equals(subTasks));
    }
}
