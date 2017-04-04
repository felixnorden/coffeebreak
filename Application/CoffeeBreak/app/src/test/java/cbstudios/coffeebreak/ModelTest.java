package cbstudios.coffeebreak;


import org.junit.Before;
import org.junit.Test;

import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.ToDoDataModule;

import static org.junit.Assert.assertTrue;

public class ModelTest {
    private Model model;

    @Before
    public void beforeTest(){
        model = new Model();
    }

    @Test
    public void testGetters(){
        ToDoDataModule toDoDataModule = model.getToDoDataModule();
        assertTrue(toDoDataModule != null);
    }
}
