package cbstudios.coffeebreak.model.tododatamodule.statistics.achievements;

/**
 * Created by johan on 5/6/2017.
 */

import java.util.List;

/**
 * Interface for Achievement list
 */
public interface IAchievementList {
    public List<IAchievement> getCreateTaskAchievementsList();

    public void setCreateTaskAchievementsList(List<IAchievement> createTaskAchievementsList);

    public List<IAchievement> getCheckTaskAchievementsList();

    public void setCheckTaskAchievementsList(List<IAchievement> checkTaskAchievementsList);

    public List<IAchievement> getTimesAppOpenAchievementList();

    public void setTimesAppOpenAchievementList(List<IAchievement> timesAppOpenAchievementList);
}
