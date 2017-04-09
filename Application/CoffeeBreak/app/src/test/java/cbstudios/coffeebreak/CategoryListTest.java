package cbstudios.coffeebreak;

import android.graphics.Color;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryList;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.LabelCategory;

/**
 * Created by johan on 4/5/2017.
 */

/**
 * Test class for the CategoryList class
 */
public class CategoryListTest {
    CategoryList categoryList = new CategoryList();

    /**
     * Add a first label before the test
     */
    @Before
    public void beforeTest () {
        categoryList.addLabelCategory("Work", Color.BLACK);
    }

    /**
     * Test the add method, remove method, getName method, getLabelCategories method,
     * initTimeCategories method, initLabelCategories method
     */
    @Test
    public void testAddAndRemoveLabelCategories () {
        assertFalse(categoryList.labelCategories.isEmpty());
        assertTrue(categoryList.labelCategories.get(0).getName() =="Work");
        categoryList.addLabelCategory();
        assertTrue(categoryList.labelCategories.get(1).getName() == "");
        categoryList.removeLabelCategory("Work");
        assertTrue(categoryList.labelCategories.get(0).getName() =="");
        categoryList.removeLabelCategory(categoryList.getLabelCategories().get(0));
        assertTrue(categoryList.labelCategories.isEmpty());
        assertTrue(categoryList.timeCategories.isEmpty());
        categoryList.initTimeCategories();
        assertFalse(categoryList.timeCategories.isEmpty());
        categoryList.initLabelCategories();
        assertFalse(categoryList.labelCategories.isEmpty());
    }
    @Test
    public void testLabelEquals(){
        CategoryList mockTrue = new CategoryList();
        mockTrue.addLabelCategory("Work", Color.BLACK);
        CategoryList mockFalseName = new CategoryList();
        mockFalseName.addLabelCategory("Meetings", Color.BLACK);
        CategoryList mockFalseNameNull = new CategoryList();
        mockFalseNameNull.addLabelCategory(null, Color.BLACK);
        CategoryList mockFalseColor = new CategoryList();
        mockFalseColor.addLabelCategory("Work", Color.BLUE);
        assertTrue(categoryList.equals(mockTrue));
        assertTrue(mockTrue.equals(categoryList));
        assertFalse(categoryList.equals(mockFalseName));
        assertFalse(categoryList.equals(mockFalseNameNull));
        assertFalse(categoryList.equals(mockFalseColor));
    }

    /**
     * Test the equals method
     */
    @Test
    public void testTimeEquals(){
        Calendar currentDate2 = Calendar.getInstance();
        Calendar currentDate = Calendar.getInstance();
        assertTrue(categoryList.timeCategories.isEmpty());
        categoryList.timeCategories.add(CategoryFactory.getInstance().createSingleDayCategory("Today", currentDate));
        categoryList.timeCategories.add(CategoryFactory.getInstance().createMultipleDayCategory("MockToday", currentDate));

        CategoryList mockTrue = new CategoryList();
        mockTrue.timeCategories.add(CategoryFactory.getInstance().createSingleDayCategory("Today", currentDate));
        mockTrue.timeCategories.add(CategoryFactory.getInstance().createMultipleDayCategory("MockToday", currentDate));
        assertTrue(mockTrue.timeCategories.equals(categoryList.timeCategories));

        CategoryList mockFalseDay = new CategoryList();
        currentDate2.add(Calendar.DAY_OF_YEAR, 1);

        mockFalseDay.timeCategories.add(CategoryFactory.getInstance().createSingleDayCategory("Today", currentDate2));
        mockFalseDay.timeCategories.add(CategoryFactory.getInstance().createMultipleDayCategory("MockToday", currentDate2));
        assertFalse(mockFalseDay.timeCategories.equals(categoryList.timeCategories));

        CategoryList mockFalseName = new CategoryList();
        mockFalseName.timeCategories.add(CategoryFactory.getInstance().createSingleDayCategory("NOTToday", currentDate));
        mockFalseName.timeCategories.add(CategoryFactory.getInstance().createMultipleDayCategory("MockToday", currentDate));
        assertFalse(mockFalseName.timeCategories.equals(categoryList.timeCategories));

        CategoryList mockFalseNameNull = new CategoryList();
        mockFalseNameNull.timeCategories.add(CategoryFactory.getInstance().createSingleDayCategory(null, currentDate));
        mockFalseNameNull.timeCategories.add(CategoryFactory.getInstance().createMultipleDayCategory("MockToday", currentDate));
        assertFalse(mockFalseNameNull.timeCategories.equals(categoryList.timeCategories));

        CategoryList mockFalseOneCategory = new CategoryList();
        mockFalseOneCategory.timeCategories.add(CategoryFactory.getInstance().createSingleDayCategory("Today", currentDate));
        assertFalse(mockFalseOneCategory.timeCategories.equals(categoryList.timeCategories));

        CategoryList mockFalseThreeCategories = new CategoryList();
        mockFalseThreeCategories.timeCategories.add(CategoryFactory.getInstance().createSingleDayCategory(null, currentDate));
        mockFalseThreeCategories.timeCategories.add(CategoryFactory.getInstance().createMultipleDayCategory("MockToday", currentDate));
        mockFalseThreeCategories.timeCategories.add(CategoryFactory.getInstance().createMultipleDayCategory("MockToday", currentDate));
        assertFalse(mockFalseThreeCategories.timeCategories.equals(categoryList.timeCategories));
    }

    /**
     * test the hashcode method
     */
    @Test
    public void hashcodeTest () {
        Calendar currentDate = Calendar.getInstance();


        int mockHashOrg = categoryList.hashCode();
        CategoryList mock = new CategoryList();
        mock.addLabelCategory("Work", Color.BLACK);
        int mockHashTrue = mock.hashCode();
        assertTrue(mockHashOrg == mockHashTrue);

        categoryList.timeCategories.add(CategoryFactory.getInstance().createSingleDayCategory("Today", currentDate));
        mock.timeCategories.add(CategoryFactory.getInstance().createSingleDayCategory("Today", currentDate));
        mockHashOrg = categoryList.hashCode();
        mockHashTrue = mock.hashCode();
        assertTrue(mockHashOrg == mockHashTrue);

        categoryList.addLabelCategory();
        int mockHashFalse = mock.hashCode();
        mockHashOrg = categoryList.hashCode();
        assertFalse(mockHashOrg == mockHashFalse);

        ILabelCategory labelOrg = new LabelCategory("Work", Color.BLACK);
        ILabelCategory mockLabel = new LabelCategory("Work", Color.BLACK);
        mockHashOrg = labelOrg.hashCode();
        mockHashTrue = mockLabel.hashCode();
        assertTrue(mockHashOrg == mockHashTrue);


        mockLabel = new LabelCategory("False", Color.BLACK);
        mockHashFalse = mockLabel.hashCode();
        assertFalse(mockHashOrg == mockHashFalse);

        mockLabel = new LabelCategory("Work", Color.BLUE);
        mockHashFalse = mockLabel.hashCode();
        assertFalse(mockHashOrg == mockHashFalse);
    }
}
