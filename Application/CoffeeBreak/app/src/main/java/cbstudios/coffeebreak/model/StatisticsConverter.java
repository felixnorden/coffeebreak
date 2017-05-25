package cbstudios.coffeebreak.model;

import com.google.gson.JsonObject;

import java.util.Calendar;

import cbstudios.coffeebreak.model.tododatamodule.statistics.Statistics;

/**
 * Created by johan on 5/9/2017.
 */

public class StatisticsConverter {

    private static StatisticsConverter INSTANCE = new StatisticsConverter();

    public static StatisticsConverter getInstance() {
        return INSTANCE;
    }

    StatisticsConverter(){

    }

    public JsonObject toJsonObject(Statistics statistics){
        return statisticsToJsonObject(statistics);
    }

    public Statistics toStatistics(JsonObject object){
        return jsonObjectToStatistics(object);
    }

    private JsonObject statisticsToJsonObject(Statistics statistics){
        JsonObject statisticsObject = new JsonObject();

        statisticsObject.addProperty("CreatedTasks", statistics.getCreatedTasks());
        statisticsObject.addProperty("CheckOffTasks", statistics.getCheckOffTasks());
        statisticsObject.addProperty("TimesUpdated", statistics.getTimesUpdated());
        statisticsObject.addProperty("TimesAppStarted", statistics.getTimesAppStarted());
        statisticsObject.addProperty("TimesNavOpen", statistics.getTimesNavOpen());
        statisticsObject.addProperty("TimesTaskDeleted", statistics.getTimesTaskDeleted());
        statisticsObject.addProperty("TimesCategoryCreated", statistics.getTimesCategoryCreated());
        statisticsObject.addProperty("TimesSettingsChanged", statistics.getTimesSettingsChanged());
        statisticsObject.addProperty("TasksAlive", statistics.getTasksAlive());
        statisticsObject.addProperty("DaysInARow", statistics.getTasksAlive());

        statisticsObject.addProperty("LastDayCheckedTask", statistics.getLastDayCheckedTask().getTimeInMillis());
        return statisticsObject;
    }

    private Statistics jsonObjectToStatistics(JsonObject object){
        Statistics statistics = new Statistics();

        statistics.setCreatedTasks(object.get("CreatedTasks").getAsInt());
        statistics.setCheckOffTasks(object.get("CheckOffTasks").getAsInt());
        statistics.setTimesUpdated(object.get("TimesUpdated").getAsInt());
        statistics.setTimesAppStarted(object.get("TimesAppStarted").getAsInt());
        statistics.setTimesNavOpen(object.get("TimesNavOpen").getAsInt());
        statistics.setTimesTaskDeleted(object.get("TimesTaskDeleted").getAsInt());
        statistics.setTimesCategoryCreated(object.get("TimesCategoryCreated").getAsInt());
        statistics.setTimesSettingsChanged(object.get("TimesSettingsChanged").getAsInt());
        statistics.setTasksAlive(object.get("TasksAlive").getAsInt());

        //if (!(object.get("DaysInARow") == null)) {
            statistics.setDaysInARow(object.get("DaysInARow").getAsInt());
          //  System.out.println("WOHOO");
       // }

       // if(!(object.get("LastDayCheckedTask") == null)){
            Calendar time = Calendar.getInstance();
            time.setTimeInMillis(Long.valueOf(object.get("LastDayCheckedTask").getAsString()));
            statistics.setLastDayCheckedTask(time);
        //    System.out.println("YAAAAS");
       // }

        return statistics;
    }
}
