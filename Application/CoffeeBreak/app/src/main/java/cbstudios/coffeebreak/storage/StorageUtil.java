package cbstudios.coffeebreak.storage;

import android.content.Context;

import android.util.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.AdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ListTask;

public class StorageUtil {

    private static final String TASK_DATA_FILENAME = "data.json";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private StorageUtil() {
    }

    public static void saveTasks(Context context, List<IAdvancedTask> tasks) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

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

            tasksMainObject.add("advancedTasks", advancedTaskArray);
            tasksMainObject.add("listTasks", listTaskArray);

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

    public static List<IAdvancedTask> loadTasks() {
        List<IAdvancedTask> loadedTasks = new ArrayList<>();
        return loadedTasks;
    }

    private static JsonObject listTaskToJsonObject(ListTask task) {
        JsonObject listTaskObject = advancedTaskToJsonObject(task);
        JsonArray subTaskArray = new JsonArray();

        for (ITask subTask : task.getSubtasks()) {
            JsonObject subTaskObject = new JsonObject();
            subTaskObject.addProperty("Name", subTask.getName());
            subTaskObject.addProperty("IsChecked", subTask.isChecked());
            subTaskArray.add(subTaskObject);
        }

        listTaskObject.add("Subtasks", subTaskArray);

        return listTaskObject;
    }

    private static JsonObject advancedTaskToJsonObject(IAdvancedTask task) {
        JsonObject taskObject = new JsonObject();
        JsonArray labelArray = new JsonArray();

        taskObject.addProperty("Name", task.getName());
        taskObject.addProperty("Note", task.getNote());
        taskObject.addProperty("IsChecked", task.isChecked());
        taskObject.addProperty("Priority", task.getPriority().toString());
        taskObject.addProperty("Date", DATE_FORMAT.format(task.getDate().getTime()));

        for (ILabelCategory label : task.getLabels()) {
            labelArray.add(label.getName());
        }

        taskObject.add("Labels", labelArray);

        return taskObject;
    }
}
