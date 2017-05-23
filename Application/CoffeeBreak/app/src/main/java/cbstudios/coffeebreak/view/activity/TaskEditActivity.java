package cbstudios.coffeebreak.view.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import cbstudios.coffeebreak.eventbus.TaskEditedEvent;
import cbstudios.coffeebreak.eventbus.OnCreateEvent;
import cbstudios.coffeebreak.eventbus.OnDestroyEvent;
import cbstudios.coffeebreak.eventbus.OnPauseEvent;
import cbstudios.coffeebreak.eventbus.OnStopEvent;

public class TaskEditActivity extends AppCompatActivity implements ITaskEditView {

    private String backupName;  //Used for when empty string is set as task name.

    //Taskname Area
    private RelativeLayout nameLayout;
    private EditText nameText;

    //Notification Area
    private RelativeLayout notificationLayout;
    private ImageView notificationIcon;
    private TextView notificationText;
    private Calendar cal;
    private ImageButton notificationRemoveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_edit);

        //Get elements
        nameLayout = (RelativeLayout) findViewById(R.id.task_edit_name_layout);
        nameText = (EditText) findViewById(R.id.task_edit_name_text);

        notificationLayout = (RelativeLayout) findViewById(R.id.task_edit_notification_layout);
        notificationIcon = (ImageView) findViewById(R.id.task_edit_notification_icon);
        notificationText = (TextView) findViewById(R.id.task_edit_notification_text);
        notificationRemoveButton = (ImageButton) findViewById(R.id.task_edit_notification_remove_button);

        //On notification area click, show time picker
        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimerPickerDialog();
            }
        });

        //Handle name input
        nameText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() != KeyEvent.ACTION_DOWN) {
                    String input = nameText.getText().toString();

                    System.out.println(input);

                    // Check if valid input, otherwise reset name.
                    if (input.equalsIgnoreCase("") || input.equalsIgnoreCase(null)) {
                        nameText.setText(backupName);
                    } else {
                        System.out.println("Event");
                        EventBus.getDefault().post(new TaskEditedEvent());
                    }

                    setShowKeyboard(false, v);
                    nameText.clearFocus();
                    return true;
                }
                return false;
            }
        });

        notificationRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal = null;
                EventBus.getDefault().post(new TaskEditedEvent());
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.edit_task_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        EventBus.getDefault().post(new OnCreateEvent(this));
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().post(new OnPauseEvent(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new OnDestroyEvent(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().post(new OnStopEvent(this));
    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        return this;
    }

    @Override
    public String getNameText() {
        System.out.println(nameText.getText().toString());
        return nameText.getText().toString();
    }

    @Override
    public void setNameText(String text) {
        nameText.setText(text);
        backupName = text;
    }

    @Override
    public void setNotification(Calendar cal) {
        this.cal = cal;

        if (cal == null) {
            notificationRemoveButton.setVisibility(View.INVISIBLE);
            notificationIcon.setAlpha(0.1f);
            notificationText.setText("");
        } else {
            notificationRemoveButton.setVisibility(View.VISIBLE);
            notificationIcon.setAlpha(1.0f);
            Date date = cal.getTime();
            String dateTime = new SimpleDateFormat("EE", Locale.getDefault()).format(date)
                    + " " + new SimpleDateFormat("dd", Locale.getDefault()).format(date)
                    + " " + new SimpleDateFormat("MMM", Locale.getDefault()).format(date)
                    + " " + new SimpleDateFormat("HH:mm", Locale.getDefault()).format(date);
            notificationText.setText(dateTime);
        }
    }

    @Override
    public Calendar getNotification() {
        return this.cal;
    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    /**
     * Shows a time picker dialog.
     */
    private void showTimerPickerDialog() {
        if (cal == null) {
            cal = Calendar.getInstance();
        }

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setCalendar(cal);
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

        private Calendar cal;

        public void setCalendar(Calendar cal) {
            this.cal = cal;
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
            cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
            cal.set(Calendar.MINUTE, minute);

            DatePickerFragment newFragment = new DatePickerFragment();
            newFragment.setCalendar(cal);
            newFragment.show(getFragmentManager(), "datePicker");
        }
    }

    /**
     * Creates a proper date picker
     */
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private Calendar cal;

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
            cal.set(Calendar.DAY_OF_MONTH, day);


            EventBus.getDefault().post(new TaskEditedEvent());
        }
    }
}
