package cbstudios.coffeebreak.view.activity;

import android.support.v7.app.AppCompatActivity;

import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ITimeCategory;
import cbstudios.coffeebreak.view.adapter.TaskAdapter;

public interface IMainView extends IView{
    ICategory getCurrentCategory();

    void setCategories(List<ILabelCategory> labelCategories, List<ITimeCategory> timeCategories);

    void setTaskAdapter(TaskAdapter adapter);
}
