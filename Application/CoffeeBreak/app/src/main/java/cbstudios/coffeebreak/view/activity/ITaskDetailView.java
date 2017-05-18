package cbstudios.coffeebreak.view.activity;

import java.util.Calendar;

public interface ITaskDetailView extends IView {
    void setNameText(String nameText);
    void setNotificationCalendar(Calendar cal);
}
