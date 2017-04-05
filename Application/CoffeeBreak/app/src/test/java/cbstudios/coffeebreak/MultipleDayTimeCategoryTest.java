package cbstudios.coffeebreak;

        import org.junit.Before;
        import org.junit.Test;
        import java.util.GregorianCalendar;
        import cbstudios.coffeebreak.model.Model;
        import cbstudios.coffeebreak.model.tododatamodule.categorylist.MultipleDayTimeCategory;
        import cbstudios.coffeebreak.model.tododatamodule.categorylist.MultipleDayTimeCategory;
        import static org.junit.Assert.assertFalse;
        import static org.junit.Assert.assertTrue;

/**
 * Created by elias on 04/04/2017.
 */

public class MultipleDayTimeCategoryTest {
    MultipleDayTimeCategory multipleDayTimeCategory;
    @Before
    public void beforeTest(){
        multipleDayTimeCategory = new MultipleDayTimeCategory("mock", new GregorianCalendar(2016,4,5));
    }

    @Test
    public void testIsInInterval(){
        assertTrue(multipleDayTimeCategory.isInIntervall(new GregorianCalendar(2016,4,5)));
        assertTrue(multipleDayTimeCategory.isInIntervall(new GregorianCalendar(2016,3,4)));
        assertTrue(multipleDayTimeCategory.isInIntervall(new GregorianCalendar(2016,4,4)));
        assertFalse(multipleDayTimeCategory.isInIntervall(new GregorianCalendar(2016,4,6)));
        assertFalse(multipleDayTimeCategory.isInIntervall(new GregorianCalendar(2016,6,5)));
        assertTrue(multipleDayTimeCategory.isInIntervall(new GregorianCalendar(1916,4,6)));
        assertTrue(multipleDayTimeCategory.isInIntervall(new GregorianCalendar(2016,4,5,8,2,1)));
        multipleDayTimeCategory.setTime(new GregorianCalendar(2016,2,2));
        assertTrue(multipleDayTimeCategory.isInIntervall(new GregorianCalendar(16,2,2)));
    }

    @Test
    public void testEquals(){
        MultipleDayTimeCategory mockTrue = new MultipleDayTimeCategory("mock", new GregorianCalendar(2016,4,5));
        MultipleDayTimeCategory mockFalseDay = new MultipleDayTimeCategory("mock", new GregorianCalendar(2016,4,5,2,2));
        MultipleDayTimeCategory mockFalseNameNull = new MultipleDayTimeCategory(null, new GregorianCalendar(2016,4,5));
        MultipleDayTimeCategory mockFalseName = new MultipleDayTimeCategory("mock2", new GregorianCalendar(2016,4,5));
        MultipleDayTimeCategory mockFalseDayNull = new MultipleDayTimeCategory("mock", null);
        MultipleDayTimeCategory mockFalseNull = new MultipleDayTimeCategory(null, null);
        assertTrue(multipleDayTimeCategory.equals(mockTrue));
        assertFalse(multipleDayTimeCategory.equals(mockFalseDay));
        assertFalse(multipleDayTimeCategory.equals(mockFalseDayNull));
        assertFalse(multipleDayTimeCategory.equals(mockFalseName));
        assertFalse(multipleDayTimeCategory.equals(mockFalseNameNull));
        assertFalse(multipleDayTimeCategory.equals(mockFalseNull));
    }
}