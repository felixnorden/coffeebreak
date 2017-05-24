package cbstudios.coffeebreak.view.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.eventbus.OnCreateEvent;
import cbstudios.coffeebreak.eventbus.OnDestroyEvent;
import cbstudios.coffeebreak.eventbus.OnPauseEvent;
import cbstudios.coffeebreak.eventbus.OnResumeEvent;
import cbstudios.coffeebreak.eventbus.OnStopEvent;
import cbstudios.coffeebreak.eventbus.TaskEditedEvent;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.view.adapter.TaskEditCategoryAdapter;

public class TaskEditActivity extends AppCompatActivity implements ITaskEditView {

    private Toolbar toolbar;
    private TaskEditCategoryAdapter adapter;

    //Taskname Area
    private RelativeLayout nameLayout;
    private EditText nameText;
    private String backupName;  //Used for when empty string is set as task name.

    //Notification Area
    private RelativeLayout notificationLayout;
    private ImageView notificationIcon;
    private TextView notificationText;
    private Calendar cal;
    private Calendar backupCal; //Used to reset calendar if setup of new calendar is cancelled.
    private ImageButton notificationRemoveButton;

    //Categories Area
    private RelativeLayout categoriesLayout;
    private ListView categoriesListView;
    private ImageView categoriesIcon;
    private ImageButton categoriesAddButton;
    private AutoCompleteTextView categoriesAddText;

    //Priority Area
    private RelativeLayout priorityLayout;
    private ImageView priorityIcon;
    private TextView priorityText;
    private ImageButton priorityRemoveButton;

    //Note Area
    private ImageView noteIcon;
    private EditText noteText;

    /**
     * {@inheritDoc}
     */
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

        categoriesLayout = (RelativeLayout) findViewById(R.id.task_edit_categories_layout);
        categoriesListView = (ListView) findViewById(R.id.task_edit_categories_list);
        categoriesIcon = (ImageView) findViewById(R.id.task_edit_categories_icon);
        categoriesAddButton = (ImageButton) findViewById(R.id.task_edit_categories_add_image_button);
        categoriesAddText = (AutoCompleteTextView) findViewById(R.id.task_edit_categories_add_text_view);

        priorityLayout = (RelativeLayout) findViewById(R.id.task_edit_priority_layout);
        priorityIcon = (ImageView) findViewById(R.id.task_edit_priority_icon);
        priorityText = (TextView) findViewById(R.id.task_edit_priority_text);
        priorityRemoveButton = (ImageButton) findViewById(R.id.task_edit_priority_remove_button);

        noteIcon = (ImageView) findViewById(R.id.task_edit_note_icon);
        noteText = (EditText) findViewById(R.id.task_edit_note_text);

        //Setup view
        setupToolbar();
        setupNameLayout();
        setupNotificationLayout();
        setupCategoriesLayout();
        setupPriorityLayout();
        setupNoteLayout();

        EventBus.getDefault().post(new OnCreateEvent(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onPause() {
        EventBus.getDefault().post(new OnPauseEvent(this));
        super.onPause();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDestroy() {
        EventBus.getDefault().post(new OnDestroyEvent(this));
        super.onDestroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStop() {
        EventBus.getDefault().post(new OnStopEvent(this));
        super.onStop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new OnResumeEvent(this));
    }

    /**
     * Setups toolbar by setting it as SupportActionBar and adding a "backbutton" to it.
     */
    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.edit_task_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * Setups the name-layout by adding a handler for input.
     * On enter closes keyboard and notifies controller that it should update model.
     * Although, if the new name is an empty string it gets reset to the model.
     * Also disables auto-suggestions for name.
     */
    private void setupNameLayout() {
        nameText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        nameText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() != KeyEvent.ACTION_DOWN) {
                    String input = nameText.getText().toString();

                    //Check if valid input, otherwise reset name.
                    //If valid input, trim and send event
                    if (input.equalsIgnoreCase("") || input.equalsIgnoreCase(null)) {
                        nameText.setText(backupName);
                    } else {
                        nameText.setText(input.trim());
                        EventBus.getDefault().post(new TaskEditedEvent());
                    }

                    setShowKeyboard(false, v);
                    nameText.clearFocus();
                }
                return false;
            }
        });

        nameText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    nameText.getBackground().setTint(getResources().getColor(R.color.colorAccent));
                } else {
                    nameText.getBackground().setTint(Color.TRANSPARENT);
                }
            }
        });
    }

    /**
     * Setups notification layout by adding listeners to touch and click events for the different
     * elements.
     */
    private void setupNotificationLayout() {
        //Setup notification area to begin date-set process on click.
        notificationLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showTimerPickerDialog();
                return false;
            }
        });

        //Remove date from task and updated view
        notificationRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal = null;
                EventBus.getDefault().post(new TaskEditedEvent());
            }
        });
    }

    /**
     * Adds listener to new-category-text-field.
     */
    private void setupCategoriesLayout() {
        categoriesAddText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() != KeyEvent.ACTION_DOWN) {
                    String input = categoriesAddText.getText().toString();

                    // Check if valid input, otherwise reset name.
                    if (!input.equalsIgnoreCase("")) {
                        EventBus.getDefault().post(new TaskEditedEvent());
                    }

                    setShowKeyboard(false, v);
                    categoriesAddText.clearFocus();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Creates logic for "circling" of priorites when layout is tapped.
     * Gives remove-button functionality.
     */
    private void setupPriorityLayout() {
        priorityLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final String current = priorityText.getText().toString();
                final String low = getResources().getString(R.string.priority_low);
                final String medium = getResources().getString(R.string.priority_medium);
                final String high = getResources().getString(R.string.priority_high);

                //Switch-case fungerar inte med variabler...
                //Går null -> low -> medium -> high -> null
                if (current.equals(low)) {
                    priorityText.setText(medium);
                    priorityRemoveButton.setVisibility(View.VISIBLE);
                } else if (current.equals(medium)) {
                    priorityText.setText(high);
                    priorityRemoveButton.setVisibility(View.VISIBLE);
                } else if (current.equals(high)) {
                    priorityText.setText("");
                    priorityRemoveButton.setVisibility(View.INVISIBLE);
                } else {    //In this case, string will (should) be empty
                    priorityText.setText(low);
                    priorityRemoveButton.setVisibility(View.VISIBLE);
                }

                EventBus.getDefault().post(new TaskEditedEvent());
                return false;
            }
        });

        priorityRemoveButton.setVisibility(View.INVISIBLE);
        priorityRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priorityText.setText(null);
                priorityRemoveButton.setVisibility(View.INVISIBLE);
                EventBus.getDefault().post(new TaskEditedEvent());
            }
        });
    }

    /**
     * Setups textfield to save and clear focus on enter.
     */
    private void setupNoteLayout() {
        noteText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() != KeyEvent.ACTION_DOWN) {
                    String input = noteText.getText().toString();
                    noteText.setText(input.trim());

                    EventBus.getDefault().post(new TaskEditedEvent());
                    setShowKeyboard(false, v);
                    noteText.clearFocus();
                }
                return false;
            }
        });

        noteText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    noteText.getBackground().setTint(getResources().getColor(R.color.colorAccent));
                } else {
                    noteText.getBackground().setTint(Color.TRANSPARENT);
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AppCompatActivity getAppCompatActivity() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNameText() {
        return nameText.getText().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNameText(String text) {
        nameText.setText(text);
        backupName = text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNotification(Calendar cal) {
        this.cal = cal;

        if (cal == null) {
            notificationRemoveButton.setVisibility(View.INVISIBLE);
            notificationIcon.setAlpha(0.2f);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Calendar getNotification() {
        return this.cal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNewLabelText() {
        String text = categoriesAddText.getText().toString();
        categoriesAddText.setText(null);
        adapter.notifyDataSetChanged();
        return text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyCategoriesChanged() {
        adapter.notifyDataSetChanged();
        updateCategoriesListHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPriority() {
        return priorityText.getText().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPriority(String priority, int color) {
        priorityText.setText(priority);
        priorityText.setTextColor(color);

        if (priority == null || priority.isEmpty()) {
            priorityIcon.setAlpha(0.2f);
        } else {
            priorityIcon.setAlpha(1.0f);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNote() {
        return noteText.getText().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNote(String note) {
        noteText.setText(note);

        if (note == null || note.isEmpty()) {
            noteIcon.setAlpha(0.2f);
        } else {
            noteIcon.setAlpha(1.0f);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setupCategories(List<ILabelCategory> taskLabels, List<ILabelCategory> allLabels) {
        //resource = 0 because of no need for custom layout ID.
        adapter = new TaskEditCategoryAdapter(this, 0, taskLabels);
        categoriesListView.setAdapter(adapter);
        updateCategoriesListHeight();

        //Set icon visibilty
        if (taskLabels.size() < 1) {
            categoriesIcon.setAlpha(0.2f);
        } else {
            categoriesIcon.setAlpha(1.0f);
        }

        //Fix autocomplete field
        List<String> strings = new ArrayList<>();
        for (ILabelCategory c : allLabels) {
            strings.add(c.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings);
        categoriesAddText.setAdapter(adapter);
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
     * Sets the ListView to the proper height.
     */
    private void updateCategoriesListHeight() {
        ViewGroup.LayoutParams params = categoriesListView.getLayoutParams();
        int newHeight = 50;

        //I don't know why that needs a factor of 3 when deciding height. It shouldn't need that as
        //height of a child is 64dp...
        newHeight += categoriesListView.getCount() * 65 * 3;
        params.height = newHeight;

        categoriesListView.setLayoutParams(params);
        categoriesListView.requestLayout();
    }

    /**
     * Shows a time picker dialog.
     */
    private void showTimerPickerDialog() {
        backupCal = cal;
        if (cal == null) {
            cal = Calendar.getInstance();
        }

        TimePickerFragment fragment = new TimePickerFragment();

        fragment.setCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                cal = backupCal;
                EventBus.getDefault().post(new TaskEditedEvent());
            }
        });

        fragment.setCalendar(cal);
        fragment.show(getFragmentManager(), "timePicker");
    }

    /**
     * Creates a proper time picker.
     */
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        private Calendar cal;
        private DialogInterface.OnCancelListener cancelListener;

        public void setCalendar(Calendar cal) {
            this.cal = cal;
        }

        public void setCancelListener(DialogInterface.OnCancelListener cancelListener) {
            this.cancelListener = cancelListener;
        }


        @Override
        public void onCancel(DialogInterface dialog) {
            cancelListener.onCancel(dialog);
            super.onCancel(dialog);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of DatePickerDialog and return it
            TimePickerDialog dialog = new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));

            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface
                    .OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (which == DialogInterface.BUTTON_NEGATIVE) {
                        dialog.cancel();
                    }
                }
            });

            return dialog;
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
            cal.set(Calendar.MINUTE, minute);

            DatePickerFragment newFragment = new DatePickerFragment();
            newFragment.setCancelListener(this.cancelListener);
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

        private DialogInterface.OnCancelListener cancelListener;

        public void setCalendar(Calendar cal) {
            this.cal = cal;
        }

        public void setCancelListener(DialogInterface.OnCancelListener cancelListener) {
            this.cancelListener = cancelListener;
        }

        @Override
        public void onCancel(DialogInterface dialog) {
            cancelListener.onCancel(dialog);
            super.onCancel(dialog);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);

            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface
                    .OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (which == DialogInterface.BUTTON_NEGATIVE) {
                        dialog.cancel();
                    }
                }
            });

            return dialog;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, day);

            EventBus.getDefault().post(new TaskEditedEvent());
        }
    }


}
