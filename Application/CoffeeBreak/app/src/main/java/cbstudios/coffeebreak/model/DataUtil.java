package cbstudios.coffeebreak.model;

import android.content.Context;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

import cbstudios.coffeebreak.model.tododatamodule.todolist.AdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ListTask;

public class DataUtil {
    private static final String TASK_DATA_FILENAME = "tasks.json";
    private static final Format DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

    private DataUtil() {
    }

    public static void main(String[] args) {
        List<IAdvancedTask> tasks = new ArrayList<>();
        saveTasksToFile(null, tasks);


        IAdvancedTask task = new AdvancedTask("Hello");
        task.setDate(Calendar.getInstance());
        tasks.add(task);
        System.out.println(DATE_FORMAT.format(task.getDate().getTime()));
    }


    /*
    Date to String to Date
    http://stackoverflow.com/questions/27950581/how-can-i-store-a-date-as-a-string
    http://stackoverflow.com/questions/9115897/how-do-i-convert-a-java-sql-date-object-into-a-gregoriancalendar
     */

    public static void saveTasksToFile(Context context, List<IAdvancedTask> tasks) {
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
                    advancedTaskArray.add(advancedTaskToJsonObject((AdvancedTask) task));
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
        return null;
    }

    private static JsonObject advancedTaskToJsonObject(AdvancedTask task) {
        JsonObject taskObject = new JsonObject();
        JsonArray labelArray = new JsonArray();

        taskObject.addProperty("Name", task.getName());
        taskObject.addProperty("Note", task.getNote());
        taskObject.addProperty("IsChecked", task.isChecked());
        //Gets the calender from the task -> extracts date from calender -> extracts long from date
        //-> wraps long in timestamp -> gets new long from timestamp -> adds long to json data.
        taskObject.addProperty("TimeStamp", new Timestamp(task.getDate().getTime().getTime()).getTime());
        taskObject.addProperty("Priority", task.getPriority().toString());

        for (ILabelCategory label : task.getLabels()) {
            labelArray.add(label.getName());
        }

        taskObject.add("Labels", labelArray);

        return taskObject;
    }

    private static JsonObject listTaskToJsonObject(ListTask task) {
        return null;
    }
}
