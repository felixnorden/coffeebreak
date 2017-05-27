package cbstudios.coffeebreak.model.tododatamodule.statistics;

/**
 * Created by johan on 5/27/2017.
 */

 public interface IAchievementStatistics {
    int getCreatedTasks();

    int getCheckOffTasks();

    int getTimesUpdated();

    int getTimesAppStarted();

    int getTimesNavOpen();

    int getTimesTaskDeleted();

    int getTimesCategoryCreated();

    int getTimesSettingsChanged();

    int getTasksAlive();

    int getDaysInARow();


}
