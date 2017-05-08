package cbstudios.coffeebreak.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.controller.IMainPresenter;
import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategory;
import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * Created by Felix on 2017-04-14.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> implements ITaskAdapter{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View vPriority;
        public CheckBox cbCheckBox;
        public EditText etTaskName;
        public ImageView ivCategory;
        public ImageButton ibMore;

        public ViewHolder(View itemView){
            super(itemView);

            vPriority = (View) itemView.findViewById(R.id.viewPriority);
            cbCheckBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            etTaskName = (EditText) itemView.findViewById(R.id.editTextField);
            ivCategory = (ImageView) itemView.findViewById(R.id.imageViewCategory);
            ibMore = (ImageButton) itemView.findViewById(R.id.imageButtonMore);
        }
    }

    private List<IAdvancedTask> mTasks = new ArrayList<>();
    private Context mContext;
    private IMainPresenter mainPresenter;

    public TaskAdapter(Context context, IMainPresenter mainPresenter){
        mContext = context;
        this.mainPresenter = mainPresenter;
        mTasks.addAll(mainPresenter.getTasks());
    }

    public TaskAdapter(Context context, IMainPresenter mainPresenter, ICategory category){
        mContext = context;
        this.mainPresenter = mainPresenter;
        mTasks.addAll(category.getValidTasks(mainPresenter.getTasks()));
    }

    /**
     *
     * @return Main context for the adapter
     */
    public Context getContext(){
        return mContext;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View taskView = inflater.inflate(R.layout.advanced_task_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(taskView);
        return viewHolder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder viewHolder, final int position){
        final IAdvancedTask task = mTasks.get(position);

        final View vPriority = viewHolder.vPriority;
        final CheckBox cbCheckBox = viewHolder.cbCheckBox;
        final EditText etTaskName = viewHolder.etTaskName;
        final ImageView ivCategory = viewHolder.ivCategory;
        final ImageButton ibMore = viewHolder.ibMore;

        if(task.getLabels().size() == 1)
            ivCategory.setColorFilter(Color.parseColor(task.getLabels().get(0).getColor()), PorterDuff.Mode.MULTIPLY);

        // Set up task layout based on whether the task has data or not.
        setUpTask(viewHolder, task, position);

        // Listen for checked off by user
        cbCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                task.setChecked(isChecked);
                etTaskName.setEnabled(!isChecked);
                if(isChecked)
                    etTaskName.setPaintFlags(etTaskName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                else
                    etTaskName.setPaintFlags(etTaskName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount(){
        return mTasks.size();
    }

    private void disableTaskName(EditText taskName) {
        taskName.setKeyListener(null);
        taskName.setBackground(null);
        taskName.setFocusable(false);
    }

    private void setUpTask(final ViewHolder taskHolder, final IAdvancedTask task, final int position){
        if(task.getName() != null){
            taskHolder.etTaskName.setText(task.getName());

            disableTaskName(taskHolder.etTaskName);
            taskHolder.vPriority.setBackgroundColor(Color.parseColor(task.getPriority().getColor()));
        }
        else{
            // Task creation, hide all unset elements except for text field
            taskHolder.cbCheckBox.setVisibility(View.INVISIBLE);
            taskHolder.ivCategory.setVisibility(View.INVISIBLE);
            taskHolder.ibMore.setVisibility(View.INVISIBLE);
            taskHolder.vPriority.setVisibility(View.INVISIBLE);
            taskHolder.etTaskName.setText(null);

            // Listen for enter key to hide Keyboard
            taskHolder.etTaskName.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() != KeyEvent.ACTION_DOWN){
                        String input = taskHolder.etTaskName.getText().toString();

                        // Check if input is empty, if so, remove task from database
                        // and update adapter of removal
                        //TODO FIX SHIEET
                        if(input.equalsIgnoreCase("") || input.equalsIgnoreCase(null)){
                            mainPresenter.removeTask(task);
                            mTasks.remove(task);
                            TaskAdapter.super.notifyItemRemoved(position);
                            return false;
                        }
                        task.setName(input);
                        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(taskHolder.etTaskName.getWindowToken(), 0);
                        TaskAdapter.super.notifyItemChanged(position);
                    }
                    return false;
                }
            });

            // Request focus for keyboard input
            taskHolder.etTaskName.requestFocus();
        }
    }
}

