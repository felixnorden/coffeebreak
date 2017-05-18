package cbstudios.coffeebreak.view.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.view.adapter.ITaskAdapter;
import cbstudios.coffeebreak.view.adapter.TaskAdapter;

public interface IMainView extends IView{
    ICategory getCurrentCategory();

    void setCategories(List<ILabelCategory> labelCategories, List<ITimeCategory> timeCategories);

    void setTaskAdapter(ITaskAdapter adapter);

    void setNavDrawer();

    void setToolbar();

}
