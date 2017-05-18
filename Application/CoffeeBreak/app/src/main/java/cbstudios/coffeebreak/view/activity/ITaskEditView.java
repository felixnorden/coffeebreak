package cbstudios.coffeebreak.view.activity;

import java.util.Calendar;

public interface ITaskEditView extends IView {
    String getNameText();

    void setNameText(String text);

    void setNotificationCalendar(Calendar cal);

    Calendar getNotificationCalendar();
}
