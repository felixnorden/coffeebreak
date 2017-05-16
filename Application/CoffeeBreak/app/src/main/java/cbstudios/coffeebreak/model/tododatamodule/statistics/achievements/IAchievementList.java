package cbstudios.coffeebreak.model.tododatamodule.statistics.achievements;

/**
 * Created by johan on 5/6/2017.
 */

import java.util.List;

/**
 * Interface for Achievement list
 */
public interface IAchievementList {
    public void InitAchievement();

    public List<IAchievement> getCreateTaskAchievementsList();

    public void setCreateTaskAchievementsList(List<IAchievement> createTaskAchievementsList);

    public List<IAchievement> getCheckTaskAchievementsList();

    public void setCheckTaskAchievementsList(List<IAchievement> checkTaskAchievementsList);

    public List<IAchievement> getTimesAppOpenAchievementList();

    public void setTimesAppOpenAchievementList(List<IAchievement> timesAppOpenAchievementList);

    public List<IAchievement> getTimesUpdatedAchievementList();

    public void setTimesUpdatedAchievementList(List<IAchievement> timesUpdatedAchievementList);

    public List<IAchievement> getTimesNavOpenAchievementList();

    public void setTimesNavOpenAchievementList(List<IAchievement> timesNavOpenAchievementList);

    public List<IAchievement> getTimesTaskDeletedAchievementList();

    public void setTimesTaskDeletedAchievementList(List<IAchievement> timesTaskDeletedAchievementList);

    public List<IAchievement> getTimesCategoryCreatedAchievementList();

    public void setTimesCategoryCreatedAchievementList(List<IAchievement> timesCategoryCreatedAchievementList);

    public List<IAchievement> getTaskAliveAchievementList();

    public void setTaskAliveAchievementList(List<IAchievement> taskAliveAchievementList);
}
