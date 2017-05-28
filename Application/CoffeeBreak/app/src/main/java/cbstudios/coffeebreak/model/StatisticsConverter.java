package cbstudios.coffeebreak.model;

import com.google.gson.JsonObject;

import java.util.Calendar;

import cbstudios.coffeebreak.model.tododatamodule.statistics.Statistics;
import cbstudios.coffeebreak.util.IConverter;

/**
 * Created by johan on 5/9/2017.
 */

public class StatisticsConverter implements IConverter<Statistics> {

    private final static StatisticsConverter INSTANCE = new StatisticsConverter();

    //List of names for properties in Json objects.
    private static final String CREATED_TASKS = "CreatedTasks";
    private static final String CHECK_OFF_TASKS = "CheckOffTasks";
    private static final String TIMES_UPDATED = "TimesUpdated";
    private static final String TIMES_APP_STARTED = "TimesAppStarted";
    private static final String TIMES_NAV_OPEN = "TimesNavOpen";
    private static final String TIMES_TASK_DELETED = "TimesTaskDeleted";
    private static final String TIMES_CATEGORY_CREATED = "TimesCategoryCreated";
    private static final String TIMES_SETTINGS_CHANGED = "TimesSettingsChanged";
    private static final String TASKS_ALIVE = "TasksAlive";
    private static final String DAYS_IN_A_ROW = "DaysInARow";
    private static final String LAST_DAY_CHECKED_TASK = "LastDayCheckedTask";

    public static StatisticsConverter getInstance() {
        return INSTANCE;
    }

    private StatisticsConverter() {

    }

    @Override
    public JsonObject toJson(Statistics statistics) {
        return statisticsToJsonObject(statistics);
    }

    @Override
    public Statistics toObject(JsonObject object) {
        return jsonObjectToStatistics(object);
    }

    private JsonObject statisticsToJsonObject(Statistics statistics) {
        JsonObject statisticsObject = new JsonObject();

        statisticsObject.addProperty(CREATED_TASKS, statistics.getCreatedTasks());
        statisticsObject.addProperty(CHECK_OFF_TASKS, statistics.getCheckOffTasks());
        statisticsObject.addProperty(TIMES_UPDATED, statistics.getTimesUpdated());
        statisticsObject.addProperty(TIMES_APP_STARTED, statistics.getTimesAppStarted());
        statisticsObject.addProperty(TIMES_NAV_OPEN, statistics.getTimesNavOpen());
        statisticsObject.addProperty(TIMES_TASK_DELETED, statistics.getTimesTaskDeleted());
        statisticsObject.addProperty(TIMES_CATEGORY_CREATED, statistics.getTimesCategoryCreated());
        statisticsObject.addProperty(TIMES_SETTINGS_CHANGED, statistics.getTimesSettingsChanged());
        statisticsObject.addProperty(TASKS_ALIVE, statistics.getTasksAlive());
        statisticsObject.addProperty(DAYS_IN_A_ROW, statistics.getTasksAlive());

        statisticsObject.addProperty(LAST_DAY_CHECKED_TASK, statistics.getLastDayCheckedTask().getTimeInMillis());
        return statisticsObject;
    }

    private Statistics jsonObjectToStatistics(JsonObject object) {
        Statistics statistics = new Statistics();

        statistics.setCreatedTasks(object.get(CREATED_TASKS).getAsInt());
        statistics.setCheckOffTasks(object.get(CHECK_OFF_TASKS).getAsInt());
        statistics.setTimesUpdated(object.get(TIMES_UPDATED).getAsInt());
        statistics.setTimesAppStarted(object.get(TIMES_APP_STARTED).getAsInt());
        statistics.setTimesNavOpen(object.get(TIMES_NAV_OPEN).getAsInt());
        statistics.setTimesTaskDeleted(object.get(TIMES_TASK_DELETED).getAsInt());
        statistics.setTimesCategoryCreated(object.get(TIMES_CATEGORY_CREATED).getAsInt());
        statistics.setTimesSettingsChanged(object.get(TIMES_SETTINGS_CHANGED).getAsInt());
        statistics.setTasksAlive(object.get(TASKS_ALIVE).getAsInt());

        if (!(object.get(DAYS_IN_A_ROW) == null)) {
            statistics.setDaysInARow(object.get(DAYS_IN_A_ROW).getAsInt());
        }

        if (!(object.get(LAST_DAY_CHECKED_TASK) == null)) {
            Calendar time = Calendar.getInstance();
            time.setTimeInMillis(Long.valueOf(object.get(LAST_DAY_CHECKED_TASK).getAsString()));
            statistics.setLastDayCheckedTask(time);
        }

        return statistics;
    }
}
