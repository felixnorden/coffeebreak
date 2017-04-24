package cbstudios.coffeebreak;

import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by elias on 04/04/2017.
 */

public class SingleDayTimeCategoryTest {
    ITimeCategory singleDayTimeCategory;

    @Before
    public void beforeTest(){
        singleDayTimeCategory = CategoryFactory.getInstance().createSingleDayCategory("mock", new GregorianCalendar(2016,4,5));
    }

    @Test
    public void testIsInInterval(){
        assertTrue(singleDayTimeCategory.isInIntervall(new GregorianCalendar(2016,4,5)));
        assertFalse(singleDayTimeCategory.isInIntervall(new GregorianCalendar(2016,3,4)));
        assertFalse(singleDayTimeCategory.isInIntervall(new GregorianCalendar(2016,4,4)));
        assertFalse(singleDayTimeCategory.isInIntervall(new GregorianCalendar(1916,4,4)));
        assertTrue(singleDayTimeCategory.isInIntervall(new GregorianCalendar(2016,4,5,8,2,1)));
        singleDayTimeCategory.setTime(new GregorianCalendar(2016,2,2,2,2,2));
        assertTrue(singleDayTimeCategory.isInIntervall(new GregorianCalendar(2016,2,2)));
    }

    @Test
    public void testEquals(){
        ITimeCategory mockTrue = CategoryFactory.getInstance().createSingleDayCategory("mock", new GregorianCalendar(2016,4,5));
        ITimeCategory mockFalseDay = CategoryFactory.getInstance().createSingleDayCategory("mock", new GregorianCalendar(2016,4,5,2,2));
        ITimeCategory mockFalseNameNull = CategoryFactory.getInstance().createSingleDayCategory(null, new GregorianCalendar(2016,4,5));
        ITimeCategory mockFalseName = CategoryFactory.getInstance().createSingleDayCategory("mock2", new GregorianCalendar(2016,4,5));
        ITimeCategory mockFalseDayNull = CategoryFactory.getInstance().createSingleDayCategory("mock", null);
        ITimeCategory mockFalseNull = CategoryFactory.getInstance().createSingleDayCategory(null, null);
        assertTrue(singleDayTimeCategory.equals(mockTrue));
        assertFalse(singleDayTimeCategory.equals(mockFalseDay));
        assertFalse(singleDayTimeCategory.equals(mockFalseDayNull));
        assertFalse(singleDayTimeCategory.equals(mockFalseName));
        assertFalse(singleDayTimeCategory.equals(mockFalseNameNull));
        assertFalse(singleDayTimeCategory.equals(mockFalseNull));
    }
}