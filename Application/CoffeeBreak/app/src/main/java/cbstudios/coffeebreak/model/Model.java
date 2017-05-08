package cbstudios.coffeebreak.model;

import cbstudios.coffeebreak.model.tododatamodule.ToDoDataModule;

public class Model {

    private ToDoDataModule toDoDataModule;

    public Model() {
        toDoDataModule = new ToDoDataModule();
    }

    /**
     * @return Returns the ToDoDataModule.
     */
    public ToDoDataModule getToDoDataModule() {
        return this.toDoDataModule;
    }
}
