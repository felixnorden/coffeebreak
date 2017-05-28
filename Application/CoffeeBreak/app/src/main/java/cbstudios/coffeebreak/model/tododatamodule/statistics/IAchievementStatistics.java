package cbstudios.coffeebreak.model.tododatamodule.statistics;

/**
 * @author Johan
 * @version 1.1
 *          <p>Responsibility: Abstraction for the Statistic class </br >
 *          Used by: Statistics, IAchievement, NumberAchievement
 *          </p>
 *
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
