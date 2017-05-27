package cbstudios.coffeebreak.view.activity;

/**
 * @author Felix, Elias
 * @version 1.0
 * <p>Responsibility: Abstracting interface for {@link cbstudios.coffeebreak.controller.MainPresenter}
 * when it interacts with its {@link cbstudios.coffeebreak.view.activity.MainActivity}</br >
 * Used by: {@link cbstudios.coffeebreak.controller.MainPresenter} </br>
 * Implemented by: {@link cbstudios.coffeebreak.view.activity.MainActivity}
 * </p>
 */

import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.view.adapter.ITaskAdapter;

public interface IMainView extends IView {

    /**
     * @return current category of tasks shown in the view
     */
    ICategory getCurrentCategory();

    /**
     * @param labelCategories the list of {@link cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory}'s of
     *                        the model
     * @param timeCategories  the list of {@link cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory}'s of
     *                        the model
     */
    void setCategories(List<ILabelCategory> labelCategories, List<ITimeCategory> timeCategories);

    /**
     * @param adapter the adapter for the view's {@link android.support.v7.widget.RecyclerView}
     */
    void setTaskAdapter(ITaskAdapter adapter);

    /**
     * Sets up the navigation drawer of the view
     */
    void setNavDrawer();

    /**
     * Sets up the toolbar of the view
     */
    void setToolbar();

    /**
     * @param category category to be set
     */
    void setCurrentCategory(ICategory category);
    void updateNavDrawer();
}
