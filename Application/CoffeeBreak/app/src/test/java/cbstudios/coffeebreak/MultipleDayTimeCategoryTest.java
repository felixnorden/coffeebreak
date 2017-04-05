package cbstudios.coffeebreak;

        import org.junit.Before;
        import org.junit.Test;
        import java.util.GregorianCalendar;
        import cbstudios.coffeebreak.model.Model;
        import cbstudios.coffeebreak.model.tododatamodule.categorylist.MultipleDayTimeCategory;
        import cbstudios.coffeebreak.model.tododatamodule.categorylist.SingleDayTimeCategory;
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

}

