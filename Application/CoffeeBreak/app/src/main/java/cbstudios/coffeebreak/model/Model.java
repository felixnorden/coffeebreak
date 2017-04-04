package cbstudios.coffeebreak.model;

import cbstudios.coffeebreak.model.tododatamodule.TODODataModule;

public class Model {

    private TODODataModule todoDataModule;

    public Model() {
        todoDataModule = new TODODataModule();
    }

    /**
     * Initializes the model with saved data.
     */
    public void initialize() {
        todoDataModule.initialize();
    }

    /**
     * @return Returns the ToDoDataModule.
     */
    public TODODataModule getToDoDataModule() {
        return this.todoDataModule;
    }
}
