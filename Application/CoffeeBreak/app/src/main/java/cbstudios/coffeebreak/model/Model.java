package cbstudios.coffeebreak.model;

import cbstudios.coffeebreak.model.tododatamodule.ToDoDataModule;

/**
 * @author Zack
 * @version 1.1
 *          Responsibility: Entrypoint into model.
 *          Uses: ToDoDataModule
 *          Used by: AchivementPresenter, AchievementAdapter, BasePresenter, DelegatingPresenter,
 *          IPresenter, IPresenterFactory, MainPresenter, PresenterFactory, TaskEditPresenter.
 */
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
