package cbstudios.coffeebreak.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
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
import android.widget.ViewSwitcher;

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
import cbstudios.coffeebreak.eventbus.TaskDeletedEvent;
import cbstudios.coffeebreak.eventbus.TaskKeyboardClosedEvent;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IListTask;
import cbstudios.coffeebreak.view.activity.IMainView;
import cbstudios.coffeebreak.view.fragment.DeleteFragment;

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
     */
    public abstract static class TaskViewHolder extends RecyclerView.ViewHolder {
        Context mContext;
        public IAdvancedTask task;
        public View vPriority;
        public CheckBox cbCheckBox;
        public ViewSwitcher viewSwitcher;
        public AppCompatEditText etTaskName;
        public AppCompatTextView tvTaskName;
        public ImageView ivCategory;
        public ImageButton ibMore;
        public Drawable etBackgroundDrawable;


        /**
         * Represents a task in the list.
         *
         * @param itemView The view in which this item is shown.
         */
        public TaskViewHolder(final View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;

            // Layout references
            vPriority = itemView.findViewById(R.id.viewPriority);
            cbCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            viewSwitcher = (ViewSwitcher) itemView.findViewById(R.id.view_switcher);
            etTaskName = (AppCompatEditText) itemView.findViewById(R.id.editTextField);
            tvTaskName = (AppCompatTextView) itemView.findViewById(R.id.textViewField);
            ivCategory = (ImageView) itemView.findViewById(R.id.imageViewCategory);
            ibMore = (ImageButton) itemView.findViewById(R.id.imageButtonMore);

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
                        tvTaskName.setText(input);
                        viewSwitcher.showNext();
                        EventBus.getDefault().post(new TaskKeyboardClosedEvent(itemView, getAdapterPosition(), false, task));
                        EventBus.getDefault().post(new ShowKeyboardEvent(false, etTaskName));
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
            setClickListeners();
        }

        /**
         * Sets up the appropriate visual representation of a task depending on if the task is finished
         * or if it has just been created.
         */
        void setUpTask() {

            if (task.getName() != null) {
                tvTaskName.setText(task.getName());
                tvTaskName.setPaintFlags(tvTaskName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                if (viewSwitcher.getDisplayedChild() == 0) {
                    viewSwitcher.showNext();
                }
                cbCheckBox.setChecked(false);
                cbCheckBox.setVisibility(View.VISIBLE);
                ivCategory.setVisibility(View.VISIBLE);
                ibMore.setVisibility(View.VISIBLE);

                vPriority.setVisibility(View.VISIBLE);
                vPriority.setBackgroundColor(Color.parseColor(task.getPriority().getColor()));

                // Set Category-color if only one category is specified.
                if (task.getLabels().size() >= 1) {
                    ivCategory.setColorFilter(Color.parseColor(task.getLabels().get(0).getColor()), PorterDuff.Mode.MULTIPLY);
                } else {
                    ivCategory.setVisibility(View.INVISIBLE);
                }
                setClickListeners();
                setSpecificFields();
            } else {
                etTaskName.setText("");
                tvTaskName.setText("");
                if (viewSwitcher.getDisplayedChild() == 1) {
                    viewSwitcher.showNext();
                }

                cbCheckBox.setChecked(false);
                cbCheckBox.setVisibility(View.INVISIBLE);
                ivCategory.setVisibility(View.INVISIBLE);
                ibMore.setVisibility(View.INVISIBLE);
                vPriority.setVisibility(View.INVISIBLE);

                etTaskName.requestFocus();
            }
        }

        private void setClickListeners() {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    DeleteFragment dialog = new DeleteFragment();
                    dialog.setType(DeleteFragment.TASK_TYPE);
                    dialog.setPosition(getAdapterPosition());
                    dialog.show(((IMainView) mContext).getAppCompatActivity().getSupportFragmentManager(), "task");
                    return true;
                }
            });
        }

        private void addOnCheckedChangedListener() {
            cbCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    task.setChecked(isChecked);
                    if (isChecked) {
                        tvTaskName.setPaintFlags(tvTaskName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        tvTaskName.setPaintFlags(tvTaskName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                }
            });
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

    /**
     * @param context The context in which this adapter is used
     * @param tasks   The list of task to display
     */
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

    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View taskView;
        TaskViewHolder viewHolder;

        if (viewType == ADVANCED_TASK) {
            taskView = inflater.inflate(R.layout.advanced_task_layout, parent, false);
            viewHolder = new AdvancedTaskViewHolder(taskView, mContext);
        }
        // If not an AdvancedTask, then it must be a ListTask
        else {
            taskView = inflater.inflate(R.layout.list_task_layout, parent, false);
            viewHolder = new ListTaskViewHolder(taskView, mContext);
        }

        return viewHolder;
    }


    @Override
    public int getItemViewType(int position) {
        if (mTasks.get(position) instanceof IListTask) {
            return LIST_TASK;
        } else {
            return ADVANCED_TASK;
        }
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

    @Override
    public void onBindViewHolder(TaskAdapter.TaskViewHolder viewHolder, final int position) {
        viewHolder.task = mTasks.get(position);
        // Set up task layout based on whether the task has data or not.
        viewHolder.setUpTask();
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public void filterTasks(ICategory currentCategory) {
        EventBus.getDefault().post(new RequestTaskListEvent(this));
        swapTasks(currentCategory.getValidTasks(tmpTasks));
    }

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
     * Handles deletion of a task when it is requested by a {@link DeleteFragment}
     *
     * @param event The object containing necessary task information
     */
    @Subscribe (threadMode = ThreadMode.MAIN)
    void deleteTask(TaskDeletedEvent event){
        if(event.which == DialogInterface.BUTTON_POSITIVE){
            EventBus.getDefault().post(new RemoveTaskEvent(mTasks.get(event.position), false));
            mTasks.remove(event.position);
            notifyItemRemoved(event.position);
            notifyItemRangeChanged(event.position, mTasks.size());
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

        /**
         * @param oldTaskList Current list of tasks
         * @param newTaskList The new list of tasks to be shown.
         */
        public TaskDiffCallback(List<IAdvancedTask> oldTaskList, List<IAdvancedTask> newTaskList) {
            this.mOldTaskList = oldTaskList;
            this.mNewTaskList = newTaskList;
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

