package cbstudios.coffeebreak;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import cbstudios.coffeebreak.model.tododatamodule.statistics.Statistics;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * @author Johan
 * @version 1.0
 *          <p>Responsibility: To test the statistic class </br >
 *          Uses: Statistics </br>
 *          </p>
 *
 */

public class StatisticTest {
    Statistics statistics;

    @Before
    public void beforeTest(){
        statistics = new Statistics();
    }

    /**
     * Test the CreatedTask stats
     */
    @Test
    public void testCreateTaskStatistic(){
        assertTrue(statistics.getCreatedTasks() == 0);
        statistics.onCreateTaskEvent();
        assertTrue(statistics.getCreatedTasks() == 1);
        assertFalse(statistics.getCreatedTasks() == 0);
    }

    /**
     * Test the CheckTask stats
     */
    @Test
    public void testCheckTaskStatistic(){
        assertTrue(statistics.getCheckOffTasks() == 0);
        statistics.onCheckTaskEvent();
        assertTrue(statistics.getCheckOffTasks() == 1);
        assertFalse(statistics.getCheckOffTasks() == 0);
    }

    /**
     * Test the timesAppStarted stats
     */
    @Test
    public void testTimesAppStartedStatistic(){
        assertTrue(statistics.getTimesAppStarted() == 0);
        statistics.onTimesAppStartedEvent();
        assertTrue(statistics.getTimesAppStarted() == 1);
        assertFalse(statistics.getTimesAppStarted() == 0);
    }

    /**
     * Test the timesCategoryCreated stats
     */
    @Test
    public void testTimesCategoryCreatedStatistic(){
        assertTrue(statistics.getTimesCategoryCreated() == 0);
        statistics.onTimesCategoryCreated();
        assertTrue(statistics.getTimesCategoryCreated() == 1);
        assertFalse(statistics.getTimesCategoryCreated() == 0);
    }

    /**
     * Test the timesNavOpen stats
     */
    @Test
    public void testTimesNavOpenStatistic(){
        assertTrue(statistics.getTimesNavOpen() == 0);
        statistics.onTimesNavOpenEvent();
        assertTrue(statistics.getTimesNavOpen() == 1);
        assertFalse(statistics.getTimesNavOpen() == 0);
    }

    /**
     * Test the timesSettingsChangedStatistic
     */
    @Test
    public void testTimesSettingsChangedStatistic(){
        assertTrue(statistics.getTimesSettingsChanged() == 0);
        statistics.onTimesSettingsChangedEvent();
        assertTrue(statistics.getTimesSettingsChanged() == 1);
        assertFalse(statistics.getTimesSettingsChanged() == 0);
    }

    /**
     * Test the timesTaskDeleted stats
     */
    @Test
    public void testTimesTaskDeletedStatistic(){
        assertTrue(statistics.getTimesTaskDeleted() == 0);
        statistics.onTimesTaskDeletedEvent();
        assertTrue(statistics.getTimesTaskDeleted() == 1);
        assertFalse(statistics.getTimesTaskDeleted() == 0);
    }

    /**
     * Test the timesUpdated stats
     */
    @Test
    public void testTimesUpdatedStatistic(){
        assertTrue(statistics.getTimesUpdated() == 0);
        statistics.onTimesUpdatedEvent();
        assertTrue(statistics.getTimesUpdated() == 1);
        assertFalse(statistics.getTimesUpdated() == 0);
    }

    /**
     * Test the tasksAlive stats
     */
    @Test
    public void testTasksAliveStatistic(){
        assertTrue(statistics.getTasksAlive() == 0);
        statistics.onCreateTaskEvent();
        statistics.onCreateTaskEvent();
        assertTrue(statistics.getTasksAlive() == 2);
        assertFalse(statistics.getTasksAlive() == 0);
        statistics.onCheckTaskEvent();
        assertTrue(statistics.getTasksAlive() == 1);
        statistics.onTimesTaskDeletedEvent();
        assertTrue(statistics.getTasksAlive() == 0);
    }

    /**
     * Test the daysInARow stats
     */
    @Test
    public void testDaysInARowStatistic(){
        statistics.setLastDayCheckedTask(null);
        assertTrue(statistics.getDaysInARow() == 0);
        statistics.onCheckTaskEvent();
        assertTrue(statistics.getDaysInARow() == 1);
        assertFalse(statistics.getDaysInARow() == 0);
        statistics.onCheckTaskEvent();
        assertTrue(statistics.getDaysInARow() == 1);
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.DAY_OF_YEAR, -1);
        statistics.setLastDayCheckedTask(calendar);
        statistics.onCheckTaskEvent();
        assertTrue(statistics.getDaysInARow() == 2);
        statistics.setLastDayCheckedTask(calendar);
        statistics.onCheckTaskEvent();
        assertTrue(statistics.getDaysInARow() == 3);
        statistics.onCheckTaskEvent();
        assertTrue(statistics.getDaysInARow() == 3);
        calendar.add(calendar.DAY_OF_YEAR, -1);
        statistics.setLastDayCheckedTask(calendar);
        statistics.onCheckTaskEvent();
        assertTrue(statistics.getDaysInARow() == 0);

    }
}
