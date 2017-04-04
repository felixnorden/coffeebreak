package cbstudios.coffeebreak.model;

import cbstudios.coffeebreak.model.tododatamodule.ToDoDataModule;

public class Model {

    private ToDoDataModule toDoDataModule;

    public Model() {
        toDoDataModule = new ToDoDataModule();
    }

    /**
     * Initializes the model with saved data.
     */
    public void initialize() {
        toDoDataModule.initialize();
    }

    /**
     * @return Returns the ToDoDataModule.
     */
    public ToDoDataModule getToDoDataModule() {
        return this.toDoDataModule;
    }
}
