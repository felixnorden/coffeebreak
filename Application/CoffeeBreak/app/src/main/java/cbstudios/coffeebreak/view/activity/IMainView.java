package cbstudios.coffeebreak.view.activity;

import android.support.v7.app.AppCompatActivity;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategory;

public interface IMainView extends IView{
    ICategory getCurrentCategory();
}
