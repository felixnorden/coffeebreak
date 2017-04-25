package cbstudios.coffeebreak.storage;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cbstudios.coffeebreak.model.Priority;
import cbstudios.coffeebreak.model.TaskSorter;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.AdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ListTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.TaskFactory;

/**
 * A utility class used to save and load data between application executions.
 */
public class StorageUtil {
    private static final TaskSorter sorter = TaskSorter.getInstance();
    private static final String TASK_DATA_FILENAME = "tasks.json";
    private static final String CATEGORIES_DATA_FILENAME = "categories.json";

    private StorageUtil() {
    }

    public static void saveTasks(Context context, List<IAdvancedTask> tasks) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
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

            //Save data to file
            FileOutputStream output = context.openFileOutput(TASK_DATA_FILENAME, Context.MODE_PRIVATE);
            output.write(gson.toJson(tasksMainObject).getBytes());
            output.close();
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
        try {
            JsonArray categoriesMainArray = new JsonArray();

            for (ILabelCategory category : categories) {
                categoriesMainArray.add(labelCategoryToJsonObject(category));
            }

            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(CATEGORIES_DATA_FILENAME, Context.MODE_PRIVATE));
            writer.write(gson.toJson(categoriesMainArray));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from save, adds them to a list and returns that list.
     * Always returns a chronologically sorted list of tasks sorted using {@link TaskSorter}.
     *
     * @return {@link List} containing the tasks saved from last run.
     */
    public static List<IAdvancedTask> loadTasks(Context context) {
        List<IAdvancedTask> loadedTasks = new ArrayList<>();
        File file = new File(context.getFilesDir(), TASK_DATA_FILENAME);

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
                loadedTasks.add(jsonObjectToListTask(listTasks.get(i).getAsJsonObject()));
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
     * Loads categories from save, adds them to a list and returns that list.
     *
     * @return {@link List} containing categories saved from last run.
     */
    public static List<ILabelCategory> loadCategories(Context context) {
        List<ILabelCategory> loadedCategories = new ArrayList<>();
        File file = new File(context.getFilesDir(), CATEGORIES_DATA_FILENAME);

        try {
            //Read string from file and parse it to JsonObject
            FileReader reader = new FileReader(file);
            JsonParser parser = new JsonParser();
            JsonArray mainArray = parser.parse(reader).getAsJsonArray();

            for (int i = 0; i < mainArray.size(); i++) {
                JsonObject categoryObject = mainArray.get(i).getAsJsonObject();
                ILabelCategory category = CategoryFactory.getInstance().createLabelCategory();
                category.setName(categoryObject.get("Name").getAsString());
                category.setColor(categoryObject.get("Color").getAsInt());
                loadedCategories.add(category);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.err.println("Tasks file doesn't seem to be proper Json - format. Can't load");
        }
        return loadedCategories;
    }

    /**
     * Creates an ListTask from a JsonObject. The created task should yield {@<code>true</code>} when equals()
     * is performed predecessor to this task (The task that it was saved from)
     * This method assumes the given JsonObject originated from a ListTask.
     *
     * @param object The JsonObject to be converted.
     * @return The task created.
     */
    private static ListTask jsonObjectToListTask(JsonObject object) {
        ListTask task = new ListTask();
        task.setName(object.get("Name").getAsString());
        task.setNote(object.get("Note").getAsString());
        task.setChecked(object.get("IsChecked").getAsBoolean());
        task.setPriority(Priority.valueOf(object.get("Priority").getAsString()));

        if (object.has("Date")) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(Long.valueOf(object.get("Date").getAsString()));
            task.setDate(cal);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.valueOf(object.get("Created").getAsString()));
        task.setCreationCalendar(cal);

        JsonArray labels = object.getAsJsonArray("Labels");

        for (int i = 0; i < labels.size(); i++) {
            ILabelCategory category = CategoryFactory.getInstance().createLabelCategory(labels.get(i).getAsString());
            task.addLabel(category);
        }

        JsonArray subTasks = object.getAsJsonArray("Subtasks");

        for (int i = 0; i < subTasks.size(); i++) {
            ITask subTask = TaskFactory.getInstance().createTask();
            JsonObject subTaskObject = subTasks.get(i).getAsJsonObject();
            subTask.setName(subTaskObject.get("Name").getAsString());
            subTask.setChecked(subTaskObject.get("IsChecked").getAsBoolean());

            Calendar subTaskCal = Calendar.getInstance();
            subTaskCal.setTimeInMillis(Long.valueOf(subTaskObject.get("Created").getAsString()));
            subTask.setCreationCalendar(subTaskCal);

            task.add(subTask);
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
        task.setName(object.get("Name").getAsString());
        task.setNote(object.get("Note").getAsString());
        task.setChecked(object.get("IsChecked").getAsBoolean());
        task.setPriority(Priority.valueOf(object.get("Priority").getAsString()));

        if (object.has("Date")) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(Long.valueOf(object.get("Date").getAsString()));
            task.setDate(cal);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.valueOf(object.get("Created").getAsString()));
        task.setCreationCalendar(cal);

        JsonArray labels = object.getAsJsonArray("Labels");

        for (int i = 0; i < labels.size(); i++) {
            ILabelCategory category = CategoryFactory.getInstance().createLabelCategory(labels.get(i).getAsString());
            task.addLabel(category);
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
            subTaskObject.addProperty("Created", subTask.getCreationCalendar().getTimeInMillis());
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
            taskObject.addProperty("Date", advancedTask.getDate().getTimeInMillis());
        }
        taskObject.addProperty("Created", advancedTask.getCreationCalendar().getTimeInMillis());

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
    private static JsonObject labelCategoryToJsonObject(ILabelCategory category) {
        JsonObject object = new JsonObject();
        object.addProperty("Name", category.getName());
        object.addProperty("Color", category.getColor());
        return object;
    }
}