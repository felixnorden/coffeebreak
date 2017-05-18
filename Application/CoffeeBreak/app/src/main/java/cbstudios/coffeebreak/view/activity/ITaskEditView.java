package cbstudios.coffeebreak.view.activity;

import java.util.Calendar;

public interface ITaskEditView extends IView {
    String getNameText();

    void setNameText(String text);

    void setNotification(Calendar cal);

    Calendar getNotification();

    void setTitle(String title);
}
