package cbstudios.coffeebreak;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.statistics.Statistics;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.AchievementFactory;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.IAchievement;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.NumberAchievement;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * @author Johan
 * @version 1.0
 *          <p>Responsibility: To test the Achievements </br >
 *          Uses: IAcvhievement and Statistis</br>
 *          </p>
 *
 */

public class AchievementTest {
    List<IAchievement> achievements;
    Statistics statistics;

    @Before
    public void beforeTest(){
        achievements = new ArrayList<>();
        statistics = new Statistics();
    }

    /**
     * Test the create task achievement
     */
    @Test
    public void createAchievementTest(){
        achievements.add(AchievementFactory.getInstance().createNumberAchievements("Create", 1, NumberAchievement.TASK_CREATED));
        assertFalse(achievements.get(0).getIfCompleted());
        statistics.onCreateTaskEvent();
        achievements.get(0).checkIfCompleted(statistics.getCreatedTasks());
        assertTrue(achievements.get(0).getIfCompleted());
    }

    /**
     * Test the check off task achievement
     */
    @Test
    public void checkAchievementTest(){
        achievements.add(AchievementFactory.getInstance().createNumberAchievements("Check", 1, NumberAchievement.TASK_CHECKED));
        assertFalse(achievements.get(0).getIfCompleted());
        statistics.onCheckTaskEvent();
        achievements.get(0).checkIfCompleted(statistics.getCheckOffTasks());
        assertTrue(achievements.get(0).getIfCompleted());
    }

    /**
     * Test the times updated achievement
     */
    @Test
    public void timesUpdatedAchievementTest(){
        achievements.add(AchievementFactory.getInstance().createNumberAchievements("TimesUpdated", 1, NumberAchievement.TIMES_UPDATED));
        assertFalse(achievements.get(0).getIfCompleted());
        statistics.onTimesUpdatedEvent();
        achievements.get(0).checkIfCompleted(statistics.getTimesUpdated());
        assertTrue(achievements.get(0).getIfCompleted());
    }

    /**
     * Test the times app started achievement
     */
    @Test
    public void timesAppStartedAchievementTest(){
        achievements.add(AchievementFactory.getInstance().createNumberAchievements("TimesAppStarted", 1, NumberAchievement.TIMES_APP_STARTED));
        assertFalse(achievements.get(0).getIfCompleted());
        statistics.onTimesAppStartedEvent();
        achievements.get(0).checkIfCompleted(statistics.getTimesAppStarted());
        assertTrue(achievements.get(0).getIfCompleted());
    }

    /**
     * Test the times navigationdrawer open achievement
     */
    @Test
    public void timesNavOpenAchievementTest(){
        achievements.add(AchievementFactory.getInstance().createNumberAchievements("TimesNavOpen", 1, NumberAchievement.TIMES_NAV_OPEN));
        assertFalse(achievements.get(0).getIfCompleted());
        statistics.onTimesNavOpenEvent();
        achievements.get(0).checkIfCompleted(statistics.getTimesNavOpen());
        assertTrue(achievements.get(0).getIfCompleted());
    }

    /**
     * Test the times task deleted achievement
     */
    @Test
    public void timesTaskDeletedAchievementTest(){
        achievements.add(AchievementFactory.getInstance().createNumberAchievements("TimesTaskDeleted", 1, NumberAchievement.TIMES_TASK_DELETED));
        assertFalse(achievements.get(0).getIfCompleted());
        statistics.onTimesTaskDeletedEvent();
        achievements.get(0).checkIfCompleted(statistics.getTimesTaskDeleted());
        assertTrue(achievements.get(0).getIfCompleted());
    }

    /**
     * Test the times category created achievement
     */
    @Test
    public void timesCategoryCreatedAchievementTest(){
        achievements.add(AchievementFactory.getInstance().createNumberAchievements("TimesCategoryCreated", 1, NumberAchievement.TIMES_CATEGORY_CREATED));
        assertFalse(achievements.get(0).getIfCompleted());
        statistics.onTimesCategoryCreated();
        achievements.get(0).checkIfCompleted(statistics.getTimesCategoryCreated());
        assertTrue(achievements.get(0).getIfCompleted());
    }

    /**
     * Test the times settings changed achievement
     */
    @Test
    public void timesSettingsChangedAchievementTest(){
        achievements.add(AchievementFactory.getInstance().createNumberAchievements("TimesSettingsChanged", 1, NumberAchievement.TIMES_SETTINGS_CHANGED));
        assertFalse(achievements.get(0).getIfCompleted());
        statistics.onTimesSettingsChangedEvent();
        achievements.get(0).checkIfCompleted(statistics.getTimesSettingsChanged());
        assertTrue(achievements.get(0).getIfCompleted());
    }

    /**
     * Test the tasks alive achievement
     */
    @Test
    public void tasksAliveAchievementTest(){
        achievements.add(AchievementFactory.getInstance().createNumberAchievements("TasksAlive", 1, NumberAchievement.TASKS_ALIVE));
        assertFalse(achievements.get(0).getIfCompleted());
        statistics.onCreateTaskEvent();
        achievements.get(0).checkIfCompleted(statistics.getTasksAlive());
        assertTrue(achievements.get(0).getIfCompleted());
    }

    /**
     * Test the days in a row achievement
     */
    @Test
    public void daysInARowAchievementTest(){
        achievements.add(AchievementFactory.getInstance().createNumberAchievements("DaysInARow", 1, NumberAchievement.DAYS_IN_A_ROW));
        assertFalse(achievements.get(0).getIfCompleted());
        statistics.onCheckTaskEvent();
        achievements.get(0).checkIfCompleted(statistics.getDaysInARow());
        assertTrue(achievements.get(0).getIfCompleted());
    }
}
