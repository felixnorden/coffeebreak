package cbstudios.coffeebreak;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.TaskConverter;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.AdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ListTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.TaskFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TaskConverterTest {

    private TaskConverter converter = TaskConverter.getInstance();
    private List<IAdvancedTask> list;
    private JsonArray array;

    private Calendar currentCal;
    private Calendar threeMonthCal;
    private List<ITask> subtasks;


    @Before
    public void initList() {
        list = new ArrayList<>();
        subtasks = new ArrayList<>();

        currentCal = Calendar.getInstance();
        threeMonthCal = Calendar.getInstance();
        threeMonthCal.add(Calendar.MONTH, 3);

        List<ILabelCategory> labelCategories = new ArrayList<>();
        labelCategories.add(CategoryFactory.getInstance().createLabelCategory("Label"));
        labelCategories.add(CategoryFactory.getInstance().createLabelCategory("Label2"));

        ITask subTask1 = TaskFactory.getInstance().createTask("Subtask1");
        ITask subTask2 = TaskFactory.getInstance().createTask("Subtask2");

        subTask1.setChecked(false);
        subTask2.setChecked(true);
        subtasks.add(subTask1);
        subtasks.add(subTask2);

        AdvancedTask at1 = new AdvancedTask("AdvancedTask1");
        at1.setDate(currentCal);
        at1.addLabel(labelCategories.get(0));
        at1.setChecked(true);
        at1.setNote("Exempel");
        at1.setPriority(Priority.THREE);

        AdvancedTask at2 = new AdvancedTask("AdvancedTask2");
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

        list.add(at1);
        list.add(at2);
        list.add(lt1);
    }

    @Test
    public void testSingleton() {
        assertTrue(converter != null);
    }

    @Test
    public void testListToJsonArray() {
        JsonArray array = converter.toJsonArray(list);
        assertTrue(array.size() == 3);

        //Extract JsonObjects from array
        JsonObject one = array.get(0).getAsJsonObject();
        JsonObject two = array.get(1).getAsJsonObject();
        JsonObject three = array.get(2).getAsJsonObject();

        //Type
        assertTrue(one.get("Type").getAsString().equals("AdvancedTask"));
        assertTrue(two.get("Type").getAsString().equals("AdvancedTask"));
        assertTrue(three.get("Type").getAsString().equals("ListTask"));

        //Name
        assertTrue(one.get("Name").getAsString().equals("AdvancedTask1"));
        assertTrue(two.get("Name").getAsString().equals("AdvancedTask2"));
        assertTrue(three.get("Name").getAsString().equals("ListTask1"));

        //Note
        assertTrue(one.get("Note").getAsString().equals("Exempel"));
        assertTrue(two.get("Note").getAsString().equals(""));
        assertTrue(three.get("Note").getAsString().equals("Lorem ipsum"));

        //Is checked
        assertTrue(one.get("IsChecked").getAsBoolean());
        assertFalse(two.get("IsChecked").getAsBoolean());
        assertTrue(three.get("IsChecked").getAsBoolean());

        //Priorities
        assertTrue(one.get("Priority").getAsString().equals("THREE"));
        assertTrue(two.get("Priority").getAsString().equals("TWO"));
        assertTrue(three.get("Priority").getAsString().equals("ONE"));

        //Dates
        assertTrue(one.has("Date"));
        assertTrue(one.get("Date").getAsString().equals(String.valueOf(currentCal.getTimeInMillis())));
        assertFalse(two.has("Date"));
        assertTrue(three.has("Date"));
        assertTrue(three.get("Date").getAsString().equals(String.valueOf(threeMonthCal.getTimeInMillis())));

        //Created-Date
        assertTrue(one.get("Created").getAsString().equals(String.valueOf(list.get(0).getCreationCalendar().getTimeInMillis())));
        assertTrue(two.get("Created").getAsString().equals(String.valueOf(list.get(1).getCreationCalendar().getTimeInMillis())));
        assertTrue(three.get("Created").getAsString().equals(String.valueOf(list.get(2).getCreationCalendar().getTimeInMillis())));

        //Labels
        assertTrue(one.get("Labels").getAsJsonArray().size() == 1);
        assertTrue(one.get("Labels").getAsJsonArray().get(0).getAsString().equals("Label"));
        assertTrue(two.get("Labels").getAsJsonArray().size() == 2);
        assertTrue(two.get("Labels").getAsJsonArray().get(0).getAsString().equals("Label"));
        assertTrue(two.get("Labels").getAsJsonArray().get(1).getAsString().equals("Label2"));
        assertTrue(three.get("Labels").getAsJsonArray().size() == 1);
        assertTrue(three.get("Labels").getAsJsonArray().get(0).getAsString().equals("Label2"));

        //SubTasks
        assertFalse(one.has("Subtasks"));
        assertFalse(two.has("Subtasks"));
        assertTrue(three.has("Subtasks"));
        assertTrue(three.getAsJsonArray("Subtasks").size() == 2);

        JsonArray subtasksArray = three.getAsJsonArray("Subtasks");

        JsonObject subTask1 = subtasksArray.get(0).getAsJsonObject();
        assertTrue(subTask1.get("Name").getAsString().equals("Subtask1"));
        assertFalse(subTask1.get("IsChecked").getAsBoolean());
        assertTrue(subTask1.get("Created").getAsString().equals(String.valueOf(subtasks.get(0).getCreationCalendar().getTimeInMillis())));

        JsonObject subTask2 = subtasksArray.get(1).getAsJsonObject();
        assertTrue(subTask2.get("Name").getAsString().equals("Subtask2"));
        assertTrue(subTask2.get("IsChecked").getAsBoolean());
        assertTrue(subTask2.get("Created").getAsString().equals(String.valueOf(subtasks.get(1).getCreationCalendar().getTimeInMillis())));

    }

    @Test
    public void testTaskToJsonObject() {
        JsonObject listTaskObj = converter.toJsonObject(list.get(2));
        JsonObject advancedTaskObj = converter.toJsonObject(list.get(0));

        assertNotNull(listTaskObj);
        assertNotNull(advancedTaskObj);

        //Type
        assertTrue(advancedTaskObj.get("Type").getAsString().equals("AdvancedTask"));
        assertTrue(listTaskObj.get("Type").getAsString().equals("ListTask"));

        //Name
        assertTrue(advancedTaskObj.get("Name").getAsString().equals("AdvancedTask1"));
        assertTrue(listTaskObj.get("Name").getAsString().equals("ListTask1"));

        //Note
        assertTrue(advancedTaskObj.get("Note").getAsString().equals("Exempel"));
        assertTrue(listTaskObj.get("Note").getAsString().equals("Lorem ipsum"));

        //Is checked
        assertTrue(advancedTaskObj.get("IsChecked").getAsBoolean());
        assertTrue(listTaskObj.get("IsChecked").getAsBoolean());

        //Priorities
        assertTrue(advancedTaskObj.get("Priority").getAsString().equals("THREE"));
        assertTrue(listTaskObj.get("Priority").getAsString().equals("ONE"));

        //Dates
        assertTrue(advancedTaskObj.has("Date"));
        assertTrue(advancedTaskObj.get("Date").getAsString().equals(String.valueOf(currentCal.getTimeInMillis())));
        assertTrue(listTaskObj.has("Date"));
        assertTrue(listTaskObj.get("Date").getAsString().equals(String.valueOf(threeMonthCal.getTimeInMillis())));

        //Created-Date
        assertTrue(advancedTaskObj.get("Created").getAsString().equals(String.valueOf(list.get(0).getCreationCalendar().getTimeInMillis())));
        assertTrue(listTaskObj.get("Created").getAsString().equals(String.valueOf(list.get(2).getCreationCalendar().getTimeInMillis())));

        //Labels
        assertTrue(advancedTaskObj.get("Labels").getAsJsonArray().size() == 1);
        assertTrue(advancedTaskObj.get("Labels").getAsJsonArray().get(0).getAsString().equals("Label"));
        assertTrue(listTaskObj.get("Labels").getAsJsonArray().size() == 1);
        assertTrue(listTaskObj.get("Labels").getAsJsonArray().get(0).getAsString().equals("Label2"));

        //SubTasks
        assertFalse(advancedTaskObj.has("Subtasks"));
        assertTrue(listTaskObj.has("Subtasks"));
        assertTrue(listTaskObj.getAsJsonArray("Subtasks").size() == 2);

        JsonArray subtasksArray = listTaskObj.getAsJsonArray("Subtasks");

        JsonObject subTask1 = subtasksArray.get(0).getAsJsonObject();
        assertTrue(subTask1.get("Name").getAsString().equals("Subtask1"));
        assertFalse(subTask1.get("IsChecked").getAsBoolean());
        assertTrue(subTask1.get("Created").getAsString().equals(String.valueOf(subtasks.get(0).getCreationCalendar().getTimeInMillis())));

        JsonObject subTask2 = subtasksArray.get(1).getAsJsonObject();
        assertTrue(subTask2.get("Name").getAsString().equals("Subtask2"));
        assertTrue(subTask2.get("IsChecked").getAsBoolean());
        assertTrue(subTask2.get("Created").getAsString().equals(String.valueOf(subtasks.get(1).getCreationCalendar().getTimeInMillis())));
    }
}
