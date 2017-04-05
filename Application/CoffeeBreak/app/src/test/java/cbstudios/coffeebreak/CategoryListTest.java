package cbstudios.coffeebreak;

import android.graphics.Color;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryList;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.LabelCategory;

/**
 * Created by johan on 4/5/2017.
 */

public class CategoryListTest {
    CategoryList categoryList;
    ILabelCategory labelCategory;
    CategoryFactory categoryFactory;

    @Before
    public void beforeTest () {
        categoryFactory = new CategoryFactory();
        //categoryList = new CategoryList();
        //categoryList.addLabelCategory("Work", Color.BLACK );
        labelCategory = categoryFactory.createLabelCategory("Work", Color.BLACK);
    }

    @Test
    public void testAddLabelCategories () {
        assertTrue(labelCategory.getName() == "Work");
        //assertTrue(categoryList.getLabelCategories().get(0).getName().equals("Work"));
    }
}
