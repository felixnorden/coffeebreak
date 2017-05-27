package cbstudios.coffeebreak;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryList;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//import cbstudios.coffeebreak.model.tododatamodule.categorylist.LabelCategory;

/**
 * Created by johan on 4/5/2017.
 */

/**
 * Test class for the CategoryList class
 */

public class CategoryListTest {
    CategoryList categoryList = new CategoryList();
    ICategoryFactory factory = CategoryFactory.getInstance();

    /**
     * Add a first label before the test
     */
    @Before
    public void beforeTest () {
        categoryList.addLabelCategory("Work");
    }

    /**
     * Test the add method, remove method, getName method, getLabelCategories method,
     * initTimeCategories method, initLabelCategories method
     */

    @Test
    public void testAddAndRemoveLabelCategories () {
        assertFalse(categoryList.getLabelCategories().isEmpty());
        assertTrue(categoryList.getLabelCategories().get(0).getName() =="Work");
        categoryList.addLabelCategory();
        assertTrue(categoryList.getLabelCategories().get(1).getName() == "");
        categoryList.removeLabelCategory("Work");
        assertTrue(categoryList.getLabelCategories().get(0).getName() =="");
        categoryList.removeLabelCategory(categoryList.getLabelCategories().get(0));
        assertTrue(categoryList.getLabelCategories().isEmpty());
        assertFalse(categoryList.getTimeCategories().isEmpty());
    }
    @Test
    public void testLabelEquals(){
        CategoryList mockTrue = new CategoryList();
        mockTrue.addLabelCategory("Work");
        CategoryList mockFalseName = new CategoryList();
        mockFalseName.addLabelCategory("Meetings");
        CategoryList mockFalseNameNull = new CategoryList();
        mockFalseNameNull.addLabelCategory();
        assertFalse(categoryList.equals(mockFalseName));
        assertFalse(categoryList.equals(mockFalseNameNull));
    }

    /**
     * Test the equals method
     */

    @Test
    public void testTimeEquals(){
        Calendar currentDate2 = Calendar.getInstance();
        Calendar currentDate = Calendar.getInstance();
//        assertTrue(categoryList.getTimeCategories().isEmpty());
        categoryList.getTimeCategories().add(CategoryFactory.getInstance().createSingleDayCategory("Today", currentDate));
        categoryList.getTimeCategories().add(CategoryFactory.getInstance().createMultipleDayCategory("MockToday", currentDate));

        CategoryList mockTrue = new CategoryList();
        mockTrue.getTimeCategories().add(CategoryFactory.getInstance().createSingleDayCategory("Today", currentDate));
        mockTrue.getTimeCategories().add(CategoryFactory.getInstance().createMultipleDayCategory("MockToday", currentDate));
//        assertTrue(mockTrue.getTimeCategories().equals(categoryList.getTimeCategories()));

        CategoryList mockFalseDay = new CategoryList();
        currentDate2.add(Calendar.DAY_OF_YEAR, 1);

        mockFalseDay.getTimeCategories().add(CategoryFactory.getInstance().createSingleDayCategory("Today", currentDate2));
        mockFalseDay.getTimeCategories().add(CategoryFactory.getInstance().createMultipleDayCategory("MockToday", currentDate2));
        assertFalse(mockFalseDay.getTimeCategories().equals(categoryList.getTimeCategories()));

        CategoryList mockFalseName = new CategoryList();
        mockFalseName.getTimeCategories().add(CategoryFactory.getInstance().createSingleDayCategory("NOTToday", currentDate));
        mockFalseName.getTimeCategories().add(CategoryFactory.getInstance().createMultipleDayCategory("MockToday", currentDate));
        assertFalse(mockFalseName.getTimeCategories().equals(categoryList.getTimeCategories()));

        CategoryList mockFalseNameNull = new CategoryList();
        mockFalseNameNull.getTimeCategories().add(CategoryFactory.getInstance().createSingleDayCategory(null, currentDate));
        mockFalseNameNull.getTimeCategories().add(CategoryFactory.getInstance().createMultipleDayCategory("MockToday", currentDate));
        assertFalse(mockFalseNameNull.getTimeCategories().equals(categoryList.getTimeCategories()));

        CategoryList mockFalseOneCategory = new CategoryList();
        mockFalseOneCategory.getTimeCategories().add(CategoryFactory.getInstance().createSingleDayCategory("Today", currentDate));
        assertFalse(mockFalseOneCategory.getTimeCategories().equals(categoryList.getTimeCategories()));

        CategoryList mockFalseThreeCategories = new CategoryList();
        mockFalseThreeCategories.getTimeCategories().add(CategoryFactory.getInstance().createSingleDayCategory(null, currentDate));
        mockFalseThreeCategories.getTimeCategories().add(CategoryFactory.getInstance().createMultipleDayCategory("MockToday", currentDate));
        mockFalseThreeCategories.getTimeCategories().add(CategoryFactory.getInstance().createMultipleDayCategory("MockToday", currentDate));
        assertFalse(mockFalseThreeCategories.getTimeCategories().equals(categoryList.getTimeCategories()));
    }

    /**
     * test the hashcode method
     */

    @Test
    public void hashcodeTest () {
        Calendar currentDate = Calendar.getInstance();


        int mockHashOrg = categoryList.hashCode();
        CategoryList mock = new CategoryList();
        mock.addLabelCategory("Work");
        int mockHashTrue = mock.hashCode();
//        assertTrue(mockHashOrg == mockHashTrue);

        categoryList.getTimeCategories().add(CategoryFactory.getInstance().createSingleDayCategory("Today", currentDate));
        mock.getTimeCategories().add(CategoryFactory.getInstance().createSingleDayCategory("Today", currentDate));
        mockHashOrg = categoryList.hashCode();
        mockHashTrue = mock.hashCode();
//        assertTrue(mockHashOrg == mockHashTrue);

        categoryList.addLabelCategory();
        int mockHashFalse = mock.hashCode();
        mockHashOrg = categoryList.hashCode();
        assertFalse(mockHashOrg == mockHashFalse);

        ILabelCategory labelOrg = CategoryFactory.getInstance().createLabelCategory("Work");
        ILabelCategory mockLabel = CategoryFactory.getInstance().createLabelCategory("Work");
        mockHashOrg = labelOrg.hashCode();
        mockHashTrue = mockLabel.hashCode();
        assertTrue(mockHashOrg == mockHashTrue);


        mockLabel = CategoryFactory.getInstance().createLabelCategory("False");
        mockHashFalse = mockLabel.hashCode();
        assertFalse(mockHashOrg == mockHashFalse);
    }
}
