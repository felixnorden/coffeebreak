package cbstudios.coffeebreak.view.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.controller.ITaskEditPresenter;
import cbstudios.coffeebreak.eventbus.onCreateEvent;
import cbstudios.coffeebreak.eventbus.onDestroyEvent;
import cbstudios.coffeebreak.eventbus.onPauseEvent;
import cbstudios.coffeebreak.eventbus.onStopEvent;

public class TaskEditActivity extends AppCompatActivity implements ITaskEditView {

    //Toolbar Area
    private ImageButton backButton;
    private EditText taskName;

    //Notification Area
    private RelativeLayout notificationLayout;
    private ImageView notificationIcon;
    private TextView notificationText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        EventBus.getDefault().post(new onCreateEvent(this));

        //Get elements
        backButton = (ImageButton) findViewById(R.id.etBackButton);
        taskName = (EditText) findViewById(R.id.etTaskName);
        notificationLayout = (RelativeLayout) findViewById(R.id.etNotification);
        notificationIcon = (ImageView) findViewById(R.id.etTimeIcon);
        notificationText = (TextView) findViewById(R.id.etNotificationText);

        //On Backbutton-click, return to previous activity.
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //On notification area click, show time picker
        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showTimerPickerDialog(v);
            }
        });

        taskName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() != KeyEvent.ACTION_DOWN) {
                    String input = taskName.getText().toString();

                    // Check if valid input, otherwise reset name.
                    if (input.equalsIgnoreCase("") || input.equalsIgnoreCase(null)) {
                        //taskName.setText();
                    } else {
                        //presenter.setTaskName(input);
                    }

                    setShowKeyboard(false, v);
                    taskName.clearFocus();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().post(new onPauseEvent(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new onDestroyEvent(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().post(new onStopEvent(this));
    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        return this;
    }

    @Override
    public void setNameText(String nameText) {
        taskName.setText(nameText);
    }

    @Override
    public void setNotificationCalendar(Calendar cal) {
        Date date = cal.getTime();

        String dateTime = new SimpleDateFormat("EE", Locale.getDefault()).format(date)
                + " " + new SimpleDateFormat("dd", Locale.getDefault()).format(date)
                + " " + new SimpleDateFormat("MMM", Locale.getDefault()).format(date)
                + " " + new SimpleDateFormat("HH:mm", Locale.getDefault()).format(date);

        notificationText.setText(dateTime);
    }

    /**
     * Shows a time picker dialog.
     *
     * @param v
     * @param presenter A presenter to give the input to.
     */
    private void showTimerPickerDialog(View v, ITaskEditPresenter presenter) {
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setPresenter(presenter);
        fragment.show(getFragmentManager(), "timePicker");
    }

    /**
     * Shows or hides the keyboard
     *
     * @param show True to show keyboard, false to hide.
     * @param v    The view in which the keyboard is active.
     */
    private void setShowKeyboard(boolean show, View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!show) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        } else {
            imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /**
     * Creates a proper time picker.
     */
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        private ITaskEditPresenter presenter;

        public void setPresenter(ITaskEditPresenter presenter) {
            this.presenter = presenter;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
            cal.set(Calendar.MINUTE, minute);

            DatePickerFragment newFragment = new DatePickerFragment();
            newFragment.setCalendar(cal);
            newFragment.setPresenter(presenter);
            newFragment.show(getFragmentManager(), "datePicker");
        }
    }

    /**
     * Creates a proper date picker
     */
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private Calendar cal;
        private ITaskEditPresenter presenter;

        public void setPresenter(ITaskEditPresenter presenter) {
            this.presenter = presenter;
        }

        public void setCalendar(Calendar cal) {
            this.cal = cal;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_YEAR, day);

            presenter.setNotificationCalendar(cal);
        }
    }
}
