package cbstudios.coffeebreak.storage;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.TaskSorter;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.AdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ListTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.Task;

/**
 * A utility class used to save and load data between application executions.
 */
public class StorageUtil {
    private static final TaskSorter sorter = TaskSorter.getInstance();
    private static final String TASK_DATA_FILENAME = "tasks.json";
    private static final String CATEGORIES_DATA_FILENAME = "categories.json";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
    private static final SimpleDateFormat CREATED_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    private StorageUtil() {
    }

    public static void main(String[] args) {
        List<ILabelCategory> categories = new ArrayList<>();
        ICategoryFactory factory = CategoryFactory.getInstance();
        categories.add(factory.createLabelCategory("1"));
        categories.add(factory.createLabelCategory("5"));
        categories.add(factory.createLabelCategory("ASDASD"));

        List<IAdvancedTask> saveTasks = new ArrayList<>();

        Calendar currentCal = Calendar.getInstance();
        Calendar nextMonthCal = Calendar.getInstance();
        Calendar twoMonthCal = Calendar.getInstance();
        nextMonthCal.add(Calendar.DAY_OF_MONTH, 30);
        twoMonthCal.add(Calendar.MONTH, 2);

        List<ITask> subTasks = new ArrayList<>();
        ITask subTask1 = new Task("Subtask1");
        ITask subTask2 = new Task("Subtask2");
        subTask1.setChecked(false);
        subTask2.setChecked(true);
        subTasks.add(subTask1);
        subTasks.add(subTask2);

        AdvancedTask at1 = new AdvancedTask("AdvancedTask1");
        at1.setDate(nextMonthCal);
        at1.setCreationCalendar(nextMonthCal);
        at1.addLabel(categories.get(0));
        at1.setChecked(true);
        at1.setNote("Exempel");
        at1.setPriority(Priority.THREE);

        ListTask lt1 = new ListTask("ListTask1");
        lt1.setDate(twoMonthCal);
        lt1.setCreationCalendar(twoMonthCal);
        lt1.addLabel(categories.get(1));
        lt1.setChecked(true);
        lt1.setNote("Lorem ipsum");
        lt1.setPriority(Priority.ONE);
        lt1.add(subTask1);
        lt1.add(subTask2);

        AdvancedTask at2 = new AdvancedTask("AdvancedTask2");
        at2.setDate(currentCal);
        at2.setCreationCalendar(currentCal);
        at2.addLabel(categories.get(0));
        at2.addLabel(categories.get(1));
        at2.setChecked(false);
        at2.setNote("");
        at2.setPriority(Priority.THREE);

        saveTasks.add(at1);
        saveTasks.add(lt1);
        saveTasks.add(at2);
        sorter.sortChronologically(saveTasks);


        save(null, saveTasks, categories);

        List<IAdvancedTask> loaded = loadTasks();

        for (IAdvancedTask task : saveTasks) {
            System.out.println(task.getName());
            System.out.println(task.getCreationCalendar().getTimeInMillis());
        }

        for (IAdvancedTask task : loaded) {
            System.out.println(task.getName());
            System.out.println(task.getCreationCalendar().getTimeInMillis());
        }

        System.out.println(saveTasks.equals(loaded));
    }

    public static void save(Context context, List<IAdvancedTask> tasks,
                            List<ILabelCategory> categories) {
        saveTasks(context, tasks);
        saveCategories(context, categories);
    }

    public static void saveTasks(Context context, List<IAdvancedTask> tasks) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // TODO: 2017-04-21 Fix for android filesystem
        /*
        File path = context.getFilesDir();
        File file = new File(path, TASK_DATA_FILENAME);
        */
        File file = new File(System.getProperty("user.home") + "\\Desktop\\" + TASK_DATA_FILENAME);

        try {
            file.createNewFile();
            JsonObject tasksMainObject = new JsonObject();

            JsonArray advancedTaskArray = new JsonArray();
            JsonArray listTaskArray = new JsonArray();


            for (IAdvancedTask task : tasks) {
                //Required to check if instance of ListTask first because ListTask extends AdvancedTask
                if (task instanceof ListTask) {
                    listTaskArray.add(listTaskToJsonObject((ListTask) task));
                } else if (task instanceof AdvancedTask) {
                    advancedTaskArray.add(advancedTaskToJsonObject(task));
                }
            }

            tasksMainObject.add("AdvancedTasks", advancedTaskArray);
            tasksMainObject.add("ListTasks", listTaskArray);

            // TODO: 2017-04-21 Fix for android filesystem.
            /*
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(TASK_DATA_FILENAME, Context.MODE_PRIVATE));
            writer.write(gson.toJson(tasksMainObject));
            writer.close();
            */

            FileWriter writer = new FileWriter(file);
            writer.write(gson.toJson(tasksMainObject));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save the given categories to filesystem.
     *
     * @param context    The Android-context where the file should be saved. Decides <i>where</i> the
     *                   file should be saved on the android filesystem.
     * @param categories The {@link List} of categories to be saved.
     */
    public static void saveCategories(Context context, List<ILabelCategory> categories) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        /*
        File path = context.getFilesDir();
        File file = new File(path, TASK_DATA_FILENAME);
        */
        File file = new File(System.getProperty("user.home") + "\\Desktop\\" + CATEGORIES_DATA_FILENAME);

        try {
            file.createNewFile();

            JsonArray categoriesMainArray = new JsonArray();


            for (ILabelCategory category : categories) {
                categoriesMainArray.add(categoryToJsonObject(category));
            }

            // TODO: 2017-04-18 Fix for android filesystem
            /*
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(TASK_DATA_FILENAME, Context.MODE_PRIVATE));
            writer.write(gson.toJson(tasksMainObject));
            writer.close();
            */

            FileWriter writer = new FileWriter(file);
            writer.write(gson.toJson(categoriesMainArray));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from save, adds them to a list, sorts that list and then returns them.
     *
     * @return {@link List} containing the tasks saved from last run.
     */
    public static List<IAdvancedTask> loadTasks() {
        List<IAdvancedTask> loadedTasks = new ArrayList<>();
        // TODO: 2017-04-21 Remove after testing
        Gson gson = new Gson();
        // TODO: 2017-04-21 Remove and replace with Android equivalent
        File file = new File(System.getProperty("user.home") + "\\Desktop\\" + TASK_DATA_FILENAME);

        try {
            //Read string from file and parse it to JsonObject
            FileReader reader = new FileReader(file);
            JsonParser parser = new JsonParser();
            JsonObject mainObject = parser.parse(reader).getAsJsonObject();

            //Extract the different arrays of tasks.
            JsonArray advancedTasks = mainObject.getAsJsonArray("AdvancedTasks");
            JsonArray listTasks = mainObject.getAsJsonArray("ListTasks");

            for (int i = 0; i < advancedTasks.size(); i++) {
                loadedTasks.add(jsonObjectToAdvancedTask(advancedTasks.get(i).getAsJsonObject()));
            }

            for (int i = 0; i < listTasks.size(); i++) {
                loadedTasks.add(jsonoObjectToListTask(listTasks.get(i).getAsJsonObject()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.err.println("Tasks file doesnt seem to be proper Json - format. Cannot load");
        }
        sorter.sortChronologically(loadedTasks);
        return loadedTasks;
    }

    /**
     * Creates an ListTask from a JsonObject. The created task should yield {@<code>true</code>} when equals()
     * is performed predecessor to this task (The task that it was saved from)
     * This method assumes the given JsonObject originated from a ListTask.
     *
     * @param object The JsonObject to be converted.
     * @return The task created.
     */
    private static ListTask jsonoObjectToListTask(JsonObject object) {
        ListTask task = new ListTask();
        // TODO: 2017-04-22 This is code duplication (see jsonObjectToAdvancedTask below). Could probably be solved in a prettier way.
        try {
            task.setName(object.get("Name").getAsString());
            task.setNote(object.get("Note").getAsString());
            task.setChecked(object.get("IsChecked").getAsBoolean());
            task.setPriority(Priority.valueOf(object.get("Priority").getAsString()));

            if (object.has("Date")) {
                Calendar cal = Calendar.getInstance();
                Date date = DATE_FORMAT.parse(object.get("Date").getAsString());
                cal.setTime(date);
                task.setDate(cal);
            }

            Calendar cal = Calendar.getInstance();
            Date date = DATE_FORMAT.parse(object.get("Created").getAsString());
            cal.setTime(date);
            task.setCreationCalendar(cal);

            JsonArray labels = object.getAsJsonArray("Labels");

            for (int i = 0; i < labels.size(); i++) {
                // TODO: 2017-04-22 Incorrect implementation. References will go ham if done this way.
                ILabelCategory category = CategoryFactory.getInstance().createLabelCategory(labels.get(i).getAsString());
                task.addLabel(category);
            }

            JsonArray subTasks = object.getAsJsonArray("Subtasks");

            for (int i = 0; i < subTasks.size(); i++) {
                Task subTask = new Task();
                JsonObject subTaskObject = subTasks.get(i).getAsJsonObject();
                subTask.setName(subTaskObject.get("Name").getAsString());
                subTask.setChecked(subTaskObject.get("IsChecked").getAsBoolean());

                Calendar subTaskCal = Calendar.getInstance();
                Date subTaskDate = DATE_FORMAT.parse(subTaskObject.get("Created").getAsString());
                subTaskCal.setTime(subTaskDate);
                subTask.setCreationCalendar(subTaskCal);
            }

        } catch (ParseException e) {
            // TODO: 2017-04-22 Real error handling
            System.err.println("Error parsing task from file");
        }
        return task;
    }

    /**
     * Creates an AdvancedTask from a JsonObject. The created task should yield {@<code>true</code>} when equals()
     * is performed predecessor to this task (The task that it was saved from).
     * This method assumes the given JsonObject originated from a AdvancedTask.
     *
     * @param object The JsonObject to be converted.
     * @return The task created.
     */
    private static AdvancedTask jsonObjectToAdvancedTask(JsonObject object) {
        AdvancedTask task = new AdvancedTask();
        try {
            task.setName(object.get("Name").getAsString());
            task.setNote(object.get("Note").getAsString());
            task.setChecked(object.get("IsChecked").getAsBoolean());
            task.setPriority(Priority.valueOf(object.get("Priority").getAsString()));

            if (object.has("Date")) {
                Calendar cal = Calendar.getInstance();
                Date date = DATE_FORMAT.parse(object.get("Date").getAsString());
                cal.setTime(date);
                task.setDate(cal);
            }

            Calendar cal = Calendar.getInstance();
            Date date = DATE_FORMAT.parse(object.get("Created").getAsString());
            cal.setTime(date);
            task.setCreationCalendar(cal);

            JsonArray labels = object.getAsJsonArray("Labels");

            for (int i = 0; i < labels.size(); i++) {
                // TODO: 2017-04-22 Incorret implementation. References will go ham if done this way.
                ILabelCategory category = CategoryFactory.getInstance().createLabelCategory(labels.get(i).getAsString());
                task.addLabel(category);
            }

        } catch (ParseException e) {
            // TODO: 2017-04-22 Real error handling
            System.err.println("Error parsing task from file");
        }
        return task;
    }

    /**
     * Converts a ListTask into a JsonObject.
     *
     * @param listTask The task that is to be converted into a JsonObject.
     * @return The JsonObject generated from the task.
     */
    private static JsonObject listTaskToJsonObject(ListTask listTask) {
        JsonObject listTaskObject = advancedTaskToJsonObject(listTask);
        JsonArray subTaskArray = new JsonArray();

        for (ITask subTask : listTask.getSubtasks()) {
            JsonObject subTaskObject = new JsonObject();
            subTaskObject.addProperty("Name", subTask.getName());
            subTaskObject.addProperty("IsChecked", subTask.isChecked());
            subTaskObject.addProperty("Created", CREATED_DATE_FORMAT.format(subTask.getCreationCalendar().getTime()));
            subTaskArray.add(subTaskObject);
        }

        listTaskObject.add("Subtasks", subTaskArray);

        return listTaskObject;
    }

    /**
     * Converts a AdvancedTask into a JsonObject.
     *
     * @param advancedTask The task that is to be converted into a JsonObject.
     * @return The JsonObject generated from the task.
     */
    private static JsonObject advancedTaskToJsonObject(IAdvancedTask advancedTask) {
        JsonObject taskObject = new JsonObject();
        JsonArray labelArray = new JsonArray();

        taskObject.addProperty("Name", advancedTask.getName());
        taskObject.addProperty("Note", advancedTask.getNote());
        taskObject.addProperty("IsChecked", advancedTask.isChecked());
        taskObject.addProperty("Priority", advancedTask.getPriority().toString());
        if (advancedTask.getDate() != null) {
            taskObject.addProperty("Date", DATE_FORMAT.format(advancedTask.getDate().getTime()));
        }
        taskObject.addProperty("Created",
                CREATED_DATE_FORMAT.format(advancedTask.getCreationCalendar().getTime()));

        for (ILabelCategory label : advancedTask.getLabels()) {
            labelArray.add(label.getName());
        }

        taskObject.add("Labels", labelArray);

        return taskObject;
    }

    /**
     * Converts a ILabelCategory into a JsonObject.
     *
     * @param category The category that is to be converted into a JsonObject.
     * @return The JsonObject generated from the category.
     */
    private static JsonObject categoryToJsonObject(ILabelCategory category) {
        JsonObject object = new JsonObject();
        object.addProperty("Name", category.getName());
        object.addProperty("Color", category.getColor());
        return object;
    }
}