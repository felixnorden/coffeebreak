package cbstudios.coffeebreak;

        import org.junit.Before;
        import org.junit.Test;
        import java.util.GregorianCalendar;
        import cbstudios.coffeebreak.model.Model;
        import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryFactory;
        import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
        import cbstudios.coffeebreak.model.tododatamodule.categorylist.MultipleDayTimeCategory;
        import cbstudios.coffeebreak.model.tododatamodule.categorylist.MultipleDayTimeCategory;
        import static org.junit.Assert.assertFalse;
        import static org.junit.Assert.assertTrue;

/**
 * Created by elias on 04/04/2017.
 */

public class MultipleDayTimeCategoryTest {
    ITimeCategory multipleDayTimeCategory;
    @Before
    public void beforeTest(){
        multipleDayTimeCategory = CategoryFactory.getInstance().createMultipleDayCategory("mock", new GregorianCalendar(2016,4,5));
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
        ITimeCategory mockTrue = CategoryFactory.getInstance().createMultipleDayCategory("mock", new GregorianCalendar(2016,4,5));
        ITimeCategory mockFalseDay = CategoryFactory.getInstance().createMultipleDayCategory("mock", new GregorianCalendar(2016,4,5,2,2));
        ITimeCategory mockFalseNameNull = CategoryFactory.getInstance().createMultipleDayCategory(null, new GregorianCalendar(2016,4,5));
        ITimeCategory mockFalseName = CategoryFactory.getInstance().createMultipleDayCategory("mock2", new GregorianCalendar(2016,4,5));
        ITimeCategory mockFalseDayNull = CategoryFactory.getInstance().createMultipleDayCategory("mock", null);
        ITimeCategory mockFalseNull = CategoryFactory.getInstance().createMultipleDayCategory(null, null);
        assertTrue(multipleDayTimeCategory.equals(mockTrue));
        assertFalse(multipleDayTimeCategory.equals(mockFalseDay));
        assertFalse(multipleDayTimeCategory.equals(mockFalseDayNull));
        assertFalse(multipleDayTimeCategory.equals(mockFalseName));
        assertFalse(multipleDayTimeCategory.equals(mockFalseNameNull));
        assertFalse(multipleDayTimeCategory.equals(mockFalseNull));
    }
}