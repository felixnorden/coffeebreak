package cbstudios.coffeebreak.model;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.AdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ListTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.TaskFactory;

/**
 * Class responsible for converting tasks into different datastructures.
 */

public class TaskConverter {

    private static TaskConverter INSTANCE = new TaskConverter();

    /**
     * Fetches the singleton instance of the TaskConverter
     *
     * @return the instance of the TaskConverter singleton
     */
    public static TaskConverter getInstance() {
        return INSTANCE;
    }

    private TaskConverter() {
    }

    /**
     * Converts a list of tasks into a JsonArray.
     *
     * @param tasks The list of tasks to be converted.
     * @return The JsonArray containing the data of the tasks in the list.
     */
    public JsonArray toJsonArray(List<IAdvancedTask> tasks) {
        JsonArray array = new JsonArray();

        for (IAdvancedTask task : tasks) {
            if (task instanceof ListTask) {
                array.add(listTaskToJsonObject((ListTask) task));
            } else if (task instanceof AdvancedTask) {
                array.add(advancedTaskToJsonObject(task));
            }
        }

        return array;
    }

    /**
     * Converts a task into a JsonObject
     *
     * @param task The task to be converted.
     * @return The JsonObject containing the data of the task.
     */
    public JsonObject toJsonObject(IAdvancedTask task) {
        if (task instanceof ListTask) {
            return listTaskToJsonObject((ListTask) task);
        } else {
            return advancedTaskToJsonObject(task);
        }
    }

    /**
     * Converts a JsonArray into a List<IAdvancedTask>
     *
     * @param array The JsonArray to be converted.
     * @return The List of IAdvancedTasks.
     */
    public List<IAdvancedTask> toList(JsonArray array) {
        List<IAdvancedTask> list = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();
            String type = object.get("Type").getAsString();

            switch (type) {
                case "ListTask":
                    list.add(jsonObjectToListTask(object));
                    break;
                case "AdvancedTask":
                    list.add(jsonObjectToAdvancedTask(object));
                    break;
            }
        }

        return list;
    }

    /**
     * Converts a ListTask into a JsonObject.
     *
     * @param listTask The task that is to be converted into a JsonObject.
     * @return The JsonObject generated from the task.
     */
    private JsonObject listTaskToJsonObject(ListTask listTask) {
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
    private JsonObject advancedTaskToJsonObject(IAdvancedTask advancedTask) {
        JsonObject taskObject = new JsonObject();
        JsonArray labelArray = new JsonArray();

        if (advancedTask instanceof ListTask) {
            taskObject.addProperty("Type", "ListTask");
        } else {
            taskObject.addProperty("Type", "AdvancedTask");
        }

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
     * Creates an ListTask from a JsonObject. The created task should yield {@<code>true</code>} when equals()
     * is performed predecessor to this task (The task that it was saved from)
     * This method assumes the given JsonObject originated from a ListTask.
     *
     * @param object The JsonObject to be converted.
     * @return The task created.
     */
    private ListTask jsonObjectToListTask(JsonObject object) {
        ListTask task = new ListTask();

        task.setName(object.get("Name").getAsString());
        task.setChecked(object.get("IsChecked").getAsBoolean());

        if (object.has("Priority")) {
            task.setPriority(Priority.valueOf(object.get("Priority").getAsString()));
        }

        if (object.has("Note")) {
            task.setNote(object.get("Note").getAsString());
        }

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
    private AdvancedTask jsonObjectToAdvancedTask(JsonObject object) {
        AdvancedTask task = new AdvancedTask();
        task.setName(object.get("Name").getAsString());
        task.setChecked(object.get("IsChecked").getAsBoolean());

        if (object.has("Priority")) {
            task.setPriority(Priority.valueOf(object.get("Priority").getAsString()));
        }

        if (object.has("Note")) {
            task.setNote(object.get("Note").getAsString());
        }

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
}
