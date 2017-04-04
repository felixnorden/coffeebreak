package cbstudios.coffeebreak;

import org.junit.Before;
import org.junit.Test;
import java.util.GregorianCalendar;
import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.SingleDayTimeCategory;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by elias on 04/04/2017.
 */

public class SingleDayTimeCategoryTest {
    SingleDayTimeCategory singleDayTimeCategory;

    @Before
    public void beforeTest(){
        singleDayTimeCategory = new SingleDayTimeCategory("mock", new GregorianCalendar(2016,4,5));
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

}