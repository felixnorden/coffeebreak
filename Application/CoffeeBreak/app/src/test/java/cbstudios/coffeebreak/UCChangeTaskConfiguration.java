package cbstudios.coffeebreak;

import org.junit.Before;
import org.junit.Test;

import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.TaskFactory;

public class UCChangeTaskConfiguration {

    private Model model;
    private IAdvancedTask task;

    @Before
    public void before() {
        model = new Model();
        task = TaskFactory.getInstance().createAdvancedTask("");
    }
}
