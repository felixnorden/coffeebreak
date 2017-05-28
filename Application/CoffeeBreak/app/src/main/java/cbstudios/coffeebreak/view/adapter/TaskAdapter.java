package cbstudios.coffeebreak.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.eventbus.EditTaskEvent;
import cbstudios.coffeebreak.eventbus.RemoveTaskEvent;
import cbstudios.coffeebreak.eventbus.RequestTaskListEvent;
import cbstudios.coffeebreak.eventbus.ShowKeyboardEvent;
import cbstudios.coffeebreak.eventbus.TaskKeyboardClosedEvent;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IListTask;

/**
 * @author Felix , Elias
 * @version 1.1
 *          Responsibility: Handling the visual representation for all tasks in the Model.
 *          Uses: IAdvancedTask, IListTask, ICategory and interfaces for abstraction.
 *          Used by: MainActivity to represent the linear list of tasks in its viewport.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> implements ITaskAdapter {

    /**
     * @author Felix, Elias
     * @version 3.0
     *          <p>Responsibility: Base class for representing different types of tasks</br >
     *          Uses: {@link IAdvancedTask}</br>
     *          Extended by: {@link AdvancedTaskViewHolder} and {@link ListTaskViewHolder}
     *          </p>
     *
     */
    public abstract static class TaskViewHolder extends RecyclerView.ViewHolder {
        public IAdvancedTask task;
        public View vPriority;
        public CheckBox cbCheckBox;
        public EditText etTaskName;
        public ImageView ivCategory;
        public ImageButton ibMore;
        public Drawable etBackgroundDrawable;


        public TaskViewHolder(final View itemView) {
            super(itemView);

            // Layout references
            vPriority = itemView.findViewById(R.id.viewPriority);
            cbCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            etTaskName = (EditText) itemView.findViewById(R.id.editTextField);
            ivCategory = (ImageView) itemView.findViewById(R.id.imageViewCategory);
            ibMore = (ImageButton) itemView.findViewById(R.id.imageButtonMore);
            etBackgroundDrawable = etTaskName.getBackground();

            // Set the text field listener for checking on  depending on if the task is being created or not
            etTaskName.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() != KeyEvent.ACTION_DOWN) {
                        String input = etTaskName.getText().toString();

                        // Check if input is empty, if so, remove task from database
                        // and update adapter of removal
                        if (input.equalsIgnoreCase("") || input.equalsIgnoreCase(null)) {
                            EventBus.getDefault().post(new TaskKeyboardClosedEvent(itemView, getAdapterPosition(), true, task));
                            return false;
                        }

                        task.setName(input);
                        EventBus.getDefault().post(new TaskKeyboardClosedEvent(itemView, getAdapterPosition(), false, task));
                        EventBus.getDefault().post(new ShowKeyboardEvent(false, etTaskName));
                        etTaskName.clearFocus();
                    }
                    return false;
                }
            });
            ibMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new EditTaskEvent(task));
                }
            });
            addOnCheckedChangedListener();
        }

        /**
         * Sets up the appropriate visual representation of a task depending on if the task is finished
         * or if it has just been created.
         */
        void setUpTask() {
            if (task.getName() != null) {
                cbCheckBox.setChecked(false);
                cbCheckBox.setVisibility(View.VISIBLE);
                ivCategory.setVisibility(View.VISIBLE);
                ibMore.setVisibility(View.VISIBLE);
                vPriority.setVisibility(View.VISIBLE);
                etTaskName.setPaintFlags(etTaskName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                etTaskName.setText(task.getName());
                vPriority.setBackgroundColor(Color.parseColor(task.getPriority().getColor()));

                // Set Category-color if only one category is specified.
                if (task.getLabels().size() >= 1) {
                    ivCategory.setColorFilter(Color.parseColor(task.getLabels().get(0).getColor()), PorterDuff.Mode.MULTIPLY);
                } else {
                    ivCategory.setVisibility(View.INVISIBLE);
                }
                setTaskNameEnabled(false);
                setSpecificFields();
            } else {
                etTaskName.setEnabled(true);
                cbCheckBox.setChecked(false);
                cbCheckBox.setVisibility(View.INVISIBLE);
                ivCategory.setVisibility(View.INVISIBLE);
                ibMore.setVisibility(View.INVISIBLE);
                vPriority.setVisibility(View.INVISIBLE);
                etTaskName.setText("");

                setTaskNameEnabled(true);
                etTaskName.requestFocus();
            }
        }

        private void addOnCheckedChangedListener() {
            cbCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    task.setChecked(isChecked);
                    etTaskName.setEnabled(!isChecked);
                    if (isChecked)
                        etTaskName.setPaintFlags(etTaskName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    else
                        etTaskName.setPaintFlags(etTaskName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
            });
        }

        /**
         * Setups the EditText to display the name of the task if the task has a name.
         * If the Tasks name doesn't exist it makes the EditText editable and focusable.
         *
         * @param value true if the EditText should be enabled, false otherwise
         */
        private void setTaskNameEnabled(boolean value) {
            if (value) {
                etTaskName.getBackground().setTint(Color.parseColor("#dd2b25"));
            } else {
                etTaskName.getBackground().setTint(Color.TRANSPARENT);
            }
            etTaskName.setFocusable(value);
            etTaskName.setFocusableInTouchMode(value);
        }

        /**
         * Sets the unique fields for the different concrete implementations
         */
        abstract void setSpecificFields();
    }

    // Important ViewType constants
    private static final int ADVANCED_TASK = 0;
    private static final int LIST_TASK = 1;

    // Data from the model
    private List<IAdvancedTask> mTasks;
    private List<IAdvancedTask> tmpTasks;

    private Context mContext;

    public TaskAdapter(Context context, List<IAdvancedTask> tasks) {
        mContext = context;
        tmpTasks = tasks;
        mTasks = new ArrayList<>();
    }

    /**
     * @return Main context for the adapter
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View taskView;
        TaskViewHolder viewHolder;

        if (viewType == ADVANCED_TASK) {
            taskView = inflater.inflate(R.layout.advanced_task_layout, parent, false);
            viewHolder = new AdvancedTaskViewHolder(taskView);
        }
        // If not an AdvancedTask, then it must be a ListTask
        else {
            taskView = inflater.inflate(R.layout.list_task_layout, parent, false);
            viewHolder = new ListTaskViewHolder(taskView);
        }

        return viewHolder;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemViewType(int position) {
        if (mTasks.get(position) instanceof IListTask)
            return LIST_TASK;
        else
            return ADVANCED_TASK;
    }

    /**
     * Removes all the tasks that is checked or that has a name == null.
     * Updates the view if any changes was made.
     *
     * @param category the category that the user is in right now
     */
    @Override
    public void refreshItems(ICategory category) {
        removeUnvalidTasks();
        filterTasks(category);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(TaskAdapter.TaskViewHolder viewHolder, final int position) {
        //IAdvancedTask task = mTasks.get(position);
        viewHolder.task = mTasks.get(position);

        // Set up task layout based on whether the task has data or not.
        viewHolder.setUpTask();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    /**
     * {@inheritDoc}
     */
    public void filterTasks(ICategory currentCategory) {
        EventBus.getDefault().post(new RequestTaskListEvent(this));
        swapTasks(currentCategory.getValidTasks(tmpTasks));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTmpTasks(List<IAdvancedTask> tasks) {
        this.tmpTasks = tasks;
    }


    /**
     * Handles the update of a task when the name is supposed to have been given to
     * the task in the holding {@link TaskViewHolder} representation
     *
     * @param event The object containing necessary update information.
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleKeyboardClosed(TaskKeyboardClosedEvent event) {
        if (event.removeTask) {
            int rangeStart = mTasks.indexOf(event.task);
            EventBus.getDefault().post(new RemoveTaskEvent(event.task, false));
            mTasks.remove(event.task);
            notifyItemRemoved(event.position);
            notifyItemRangeChanged(rangeStart, mTasks.size());
        } else {
            notifyItemChanged(event.position);
        }
    }

    /**
     * Changes the tasks that the adapter is holding, uses DiffUtil to only update the views that has
     * been changed.
     *
     * @param newTasks The new list that is going to replace the current list of tasks
     */
    public void swapTasks(List<IAdvancedTask> newTasks) {
        final TaskDiffCallback diffCallback = new TaskDiffCallback(this.mTasks, newTasks);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.mTasks.clear();
        this.mTasks.addAll(newTasks);
        diffResult.dispatchUpdatesTo(this);
    }

    /**
     * Removes all checked tasks. Is used through removeUnvalidTasks().
     */
    private void removeCheckedTasks() {
        //EventBus.getDefault().post(new RequestTaskListEvent(this));
        for (IAdvancedTask task : tmpTasks) {
            if (task.isChecked()) {
                EventBus.getDefault().post(new RemoveTaskEvent(task, true));
            }
        }
    }

    /**
     * Removes all tasks with name == null. Is used through removeUnvalidTasks().
     */
    private void removeNullTasks() {
        //EventBus.getDefault().post(new RequestTaskListEvent(this));
        for (IAdvancedTask task : tmpTasks) {
            if (task.getName() == null) {
                EventBus.getDefault().post(new RemoveTaskEvent(task, false));
            }
        }
    }

    /**
     * Removes all tasks that should be deleted.
     */
    private void removeUnvalidTasks() {
        EventBus.getDefault().post(new RequestTaskListEvent(this));
        removeCheckedTasks();
        removeNullTasks();
    }

    /**
     * @author Elias
     * @version 1.1
     *          Responsibility: Given two lists of IAdvancedTasks, calculates the difference between them and
     *          calls the appropriate methods in the RecyclerView to update the view
     *          Uses: IAdvancedTask
     *          Used by: TaskAdapter
     */
    private class TaskDiffCallback extends DiffUtil.Callback {

        private final List<IAdvancedTask> mOldTaskList;
        private final List<IAdvancedTask> mNewTaskList;

        public TaskDiffCallback(List<IAdvancedTask> oldEmployeeList, List<IAdvancedTask> newEmployeeList) {
            this.mOldTaskList = oldEmployeeList;
            this.mNewTaskList = newEmployeeList;
        }

        @Override
        public int getOldListSize() {
            return mOldTaskList.size();
        }

        @Override
        public int getNewListSize() {
            return mNewTaskList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldTaskList.get(oldItemPosition).hashCode() == mNewTaskList.get(
                    newItemPosition).hashCode();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final IAdvancedTask oldTask = mOldTaskList.get(oldItemPosition);
            final IAdvancedTask newTask = mNewTaskList.get(newItemPosition);

            return oldTask.getName().equals(newTask.getName());
        }


        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {

            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }
}

