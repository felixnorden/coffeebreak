package cbstudios.coffeebreak.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IListTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.ITaskFactory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.TaskFactory;
import cbstudios.coffeebreak.util.IListConverter;

/**
 * @author Zack
 * @version 1.0
 *          Responsibility: Handles converting between tasks and JSON data.
 *          Uses: IListTask, ITask, ITaskFactory, TaskFactory, IAdvancedTask, ILAbelCategory, CategoryFactory
 *          Used by: DelegatingPresenter.
 */
public class TaskConverter implements IListConverter<IAdvancedTask> {

    private final static TaskConverter INSTANCE = new TaskConverter();
    private ITaskFactory factory = TaskFactory.getInstance();

    //List of names for properties in Json objects.
    private static final String TYPE = "Type";
    private static final String NAME = "Name";
    private static final String SUB_TASKS = "Subtasks";
    private static final String IS_CHECKED = "IsChecked";
    private static final String CREATED = "Created";
    private static final String NOTE = "Note";
    private static final String PRIORITY = "Priority";
    private static final String DATE = "Date";
    private static final String CATEGORIES = "Categories";
    private static final String COLOR = "Color";



    /**
     * Fetches the singleton instance of the TaskConverter
     *
     * @return the instance of the TaskConverter singleton
     */
    public static TaskConverter getInstance() {
        return INSTANCE;
    }

    /**
     * Hidden constructor because of singleton.
     */
    private TaskConverter() {
    }

    @Override
    public JsonArray toJson(List<IAdvancedTask> tasks) {
        JsonArray array = new JsonArray();

        for (IAdvancedTask task : tasks) {
            if (task instanceof IListTask) {
                array.add(listTaskToJsonObject((IListTask) task));
            } else if (task != null) {
                array.add(advancedTaskToJsonObject(task));
            }
        }

        return array;
    }

    @Override
    public JsonObject toJson(IAdvancedTask task) {
        if (task instanceof IListTask) {
            return listTaskToJsonObject((IListTask) task);
        } else {
            return advancedTaskToJsonObject(task);
        }
    }

    @Override
    public IAdvancedTask toObject(JsonObject object) {
        if (object.has(TYPE)) {
            String type = object.get(TYPE).getAsString();

            switch (type) {
                case "ListTask":
                    return jsonObjectToListTask(object);
                case "AdvancedTask":
                    return jsonObjectToAdvancedTask(object);
                default:
                    throw new JsonParseException("This type is not supported by this converter.");
            }
        } else {
            throw new JsonParseException("Object is not a IAdvancedTask. Cannot find String-member 'Type' in object");
        }
    }

    @Override
    public List<IAdvancedTask> toObject(JsonArray array) {
        List<IAdvancedTask> list = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();
            String type = object.get(TYPE).getAsString();

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
    private JsonObject listTaskToJsonObject(IListTask listTask) {
        JsonObject listTaskObject = advancedTaskToJsonObject(listTask);
        JsonArray subTaskArray = new JsonArray();

        for (ITask subTask : listTask.getSubtasks()) {
            JsonObject subTaskObject = new JsonObject();
            subTaskObject.addProperty(NAME, subTask.getName());
            subTaskObject.addProperty(IS_CHECKED, subTask.isChecked());
            subTaskObject.addProperty(CREATED, subTask.getCreationCalendar().getTimeInMillis());
            subTaskArray.add(subTaskObject);
        }

        listTaskObject.add(SUB_TASKS, subTaskArray);

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

        if (advancedTask instanceof IListTask) {
            taskObject.addProperty(TYPE, "ListTask");
        } else {
            taskObject.addProperty(TYPE, "AdvancedTask");
        }

        taskObject.addProperty(NAME, advancedTask.getName());
        taskObject.addProperty(NOTE, advancedTask.getNote());
        taskObject.addProperty(IS_CHECKED, advancedTask.isChecked());
        taskObject.addProperty(PRIORITY, advancedTask.getPriority().toString());
        if (advancedTask.getNotification() != null) {
            taskObject.addProperty(DATE, advancedTask.getNotification().getTimeInMillis());
        }
        taskObject.addProperty(CREATED, advancedTask.getCreationCalendar().getTimeInMillis());

        for (ILabelCategory label : advancedTask.getLabels()) {
            JsonObject categoryObject = new JsonObject();
            categoryObject.addProperty(NAME, label.getName());
            categoryObject.addProperty(COLOR, label.getColor());

            labelArray.add(categoryObject);
        }

        taskObject.add(CATEGORIES, labelArray);

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
    private IListTask jsonObjectToListTask(JsonObject object) {
        IListTask task = factory.createListTask();

        task.setName(object.get(NAME).getAsString());
        task.setChecked(object.get(IS_CHECKED).getAsBoolean());

        if (object.has(PRIORITY)) {
            task.setPriority(Priority.valueOf(object.get(PRIORITY).getAsString()));
        }

        if (object.has(NOTE)) {
            task.setNote(object.get(NOTE).getAsString());
        }

        if (object.has(DATE)) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(Long.valueOf(object.get(DATE).getAsString()));
            task.getNotification(cal);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.valueOf(object.get(CREATED).getAsString()));
        task.setCreationCalendar(cal);

        JsonArray categories = object.getAsJsonArray(CATEGORIES);

        for (int i = 0; i < categories.size(); i++) {
            JsonObject jsonCategory = categories.get(i).getAsJsonObject();
            String name = jsonCategory.get(NAME).getAsString();
            String color = jsonCategory.get(COLOR).getAsString();
            ILabelCategory category = CategoryFactory.getInstance().createLabelCategory(name, color);
            task.addLabel(category);
        }

        JsonArray subTasks = object.getAsJsonArray(SUB_TASKS);

        for (int i = 0; i < subTasks.size(); i++) {
            ITask subTask = TaskFactory.getInstance().createTask();
            JsonObject subTaskObject = subTasks.get(i).getAsJsonObject();
            subTask.setName(subTaskObject.get(NAME).getAsString());
            subTask.setChecked(subTaskObject.get(IS_CHECKED).getAsBoolean());

            Calendar subTaskCal = Calendar.getInstance();
            subTaskCal.setTimeInMillis(Long.valueOf(subTaskObject.get(CREATED).getAsString()));
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
    private IAdvancedTask jsonObjectToAdvancedTask(JsonObject object) {
        IAdvancedTask task = factory.createAdvancedTask();
        task.setName(object.get(NAME).getAsString());
        task.setChecked(object.get(IS_CHECKED).getAsBoolean());

        if (object.has(PRIORITY)) {
            task.setPriority(Priority.valueOf(object.get(PRIORITY).getAsString()));
        }

        if (object.has(NOTE)) {
            task.setNote(object.get(NOTE).getAsString());
        }

        if (object.has(DATE)) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(Long.valueOf(object.get(DATE).getAsString()));
            task.getNotification(cal);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.valueOf(object.get(CREATED).getAsString()));
        task.setCreationCalendar(cal);

        JsonArray categories = object.getAsJsonArray(CATEGORIES);

        if (categories != null) {
            for (int i = 0; i < categories.size(); i++) {
                JsonObject jsonCategory = categories.get(i).getAsJsonObject();
                String name = jsonCategory.get(NAME).getAsString();
                String color = jsonCategory.get(COLOR).getAsString();
                ILabelCategory category = CategoryFactory.getInstance().createLabelCategory(name, color);
                task.addLabel(category);
            }
        }
        return task;
    }
}