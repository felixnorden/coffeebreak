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

import static org.junit.Assert.assertTrue;

public class DataUtilTest {

    @Test
    public void testTaskSaveAndLoad() {
        List<IAdvancedTask> saveTasks = new ArrayList<>();

        Calendar currentCal = Calendar.getInstance();
        Calendar tomorrovCal = Calendar.getInstance();
        tomorrovCal.add(Calendar.DAY_OF_MONTH, 30);

        IAdvancedTask at1 = new AdvancedTask("AdvancedTask1");
        List<ILabelCategory> labelCategories1 = new ArrayList<>();
        labelCategories1.add(CategoryFactory.getInstance().createLabelCategory("Label", Color.BLUE));

        at1.setDate(currentCal);
        at1.addLabel(labelCategories1.get(0));
        at1.setChecked(true);
        at1.setNote("Exempel");
        at1.setPriority(Priority.THREE);


        IAdvancedTask at2 = new AdvancedTask("AdvancedTask2");
        List<ILabelCategory> labelCategories2 = new ArrayList<>();
        labelCategories2.add(CategoryFactory.getInstance().createLabelCategory("Label2", Color.RED));

        at2.setDate(tomorrovCal);
        at2.addLabel(labelCategories2.get(0));
        at2.setChecked(false);
        at2.setNote("");
        at2.setPriority(Priority.TWO);

        saveTasks.add(at1);
        saveTasks.add(at2);


        // TODO: Because of JVM (no emulator) testing, no context is available (context = null) .
        // TODO: Should be changed when algorithm is done and android implementation begins
        DataUtil.saveTasksToFile(null, saveTasks);
        List<IAdvancedTask> loadedTasks = DataUtil.loadTasks();

        assertTrue(loadedTasks.size() == 2);

        IAdvancedTask loadedAt1 = loadedTasks.get(0);
        IAdvancedTask loadedAt2 = loadedTasks.get(1);

        assertTrue(loadedAt1.getName().equals("AdvancedTask1"));
        assertTrue(loadedAt1.hasNote());
        assertTrue(loadedAt1.getNote().equals("Exempel"));
        assertTrue(loadedAt1.isChecked());
        assertTrue(loadedAt1.hasPriority());
        assertTrue(loadedAt1.getPriority().equals(Priority.THREE));
        assertTrue(loadedAt1.getDate().compareTo(currentCal) == 0);
        assertTrue(loadedAt1.getLabels().equals(labelCategories2));

        assertTrue(loadedAt2.getName().equals("AdvancedTask2"));
        assertTrue(!loadedAt2.hasNote());
        assertTrue(loadedAt2.getNote().equals(""));
        assertTrue(!loadedAt2.isChecked());
        assertTrue(loadedAt2.hasPriority());
        assertTrue(loadedAt2.getPriority().equals(Priority.TWO));
        assertTrue(loadedAt2.getDate().compareTo(tomorrovCal) == 0);
        assertTrue(loadedAt2.getLabels().equals(labelCategories2));
    }
}
