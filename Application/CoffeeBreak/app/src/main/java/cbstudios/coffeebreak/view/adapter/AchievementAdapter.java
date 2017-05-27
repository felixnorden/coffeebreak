package cbstudios.coffeebreak.view.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.model.Model;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.IAchievement;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.NumberAchievement;

/**
 * Created by johan on 5/25/2017.
 */

public class AchievementAdapter  extends RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder> implements IAchievementAdapter {

    private Context context;
    private List<IAchievement> achievementList;
    private Model model;

    public AchievementAdapter(Context context, List<IAchievement> achievementList, Model model){
        this.context = context;
        this.achievementList = achievementList;
        this.model = model;
    }


    @Override
    public AchievementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View achievementView;
        AchievementViewHolder viewHolder;

        achievementView = inflater.inflate(R.layout.achievement_badge_layout, parent, false);
        viewHolder = new AchievementViewHolder(achievementView, model);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AchievementViewHolder holder, int position) {
        holder.achievement = achievementList.get(position);
        holder.setUpAchievement();
    }

    @Override
    public int getItemCount() {
        return achievementList.size();
    }

    public static class AchievementViewHolder extends RecyclerView.ViewHolder {

        public IAchievement achievement;
        public ImageView achievementBackground;
        public ImageView achievementSymbol;
        public TextView achievementText;
        public ColorStateList colorStateList;
        public ImageView back;
        private Model model;


        public AchievementViewHolder(View itemView, Model model) {
            super(itemView);
            achievementBackground =(ImageView) itemView.findViewById(R.id.achievementBackground);
            achievementSymbol = (ImageView) itemView.findViewById(R.id.achievementSymbol);
            achievementText = (TextView) itemView.findViewById(R.id.achievementText);
            colorStateList = ColorStateList.valueOf(Color.BLUE);
            back = (ImageView)  itemView.findViewById(R.id.back);
            this.model = model;
        }

        public void setUpAchievement(){
            achievementText.setText(achievement.getName() + achievement.getNumberLimit());

            switch (achievement.getType()){
                case(NumberAchievement.CREATE):
                    achievementSymbol.setImageResource(R.drawable.ic_edit_black_24dp);
                    achievement.checkIfCompleted(model.getToDoDataModule().getStats().getCreatedTasks());
                    break;
                case(NumberAchievement.CHECK):
                    achievementSymbol.setImageResource(R.drawable.ic_done_black_24dp);
                    achievement.checkIfCompleted(model.getToDoDataModule().getStats().getCheckOffTasks());
                    break;
                case(NumberAchievement.DAYSINAROW):
                    achievementSymbol.setImageResource(R.drawable.ic_date_range_black_24dp);
                    achievement.checkIfCompleted(model.getToDoDataModule().getStats().getDaysInARow());
                    break;
                case(NumberAchievement.TASKSALIVE):
                    achievementSymbol.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    achievement.checkIfCompleted(model.getToDoDataModule().getStats().getTasksAlive());
                    break;
                case(NumberAchievement.TIMESAPPSTARTED):
                    achievementSymbol.setImageResource(R.drawable.ic_free_breakfast_black_24dp);
                    achievement.checkIfCompleted(model.getToDoDataModule().getStats().getTimesAppStarted());
                    break;
                case(NumberAchievement.TIMESCATEGORYCREATED):
                    achievementSymbol.setImageResource(R.drawable.ic_create_new_folder_black_24dp);
                    achievement.checkIfCompleted(model.getToDoDataModule().getStats().getTimesCategoryCreated());
                    break;
                case(NumberAchievement.TIMESNAVOPEN):
                    achievementSymbol.setImageResource(R.drawable.ic_menu_black_24dp);
                    achievement.checkIfCompleted(model.getToDoDataModule().getStats().getTimesNavOpen());
                    break;
                case(NumberAchievement.TIMESSETTINGSCHANGED):
                    achievementSymbol.setImageResource(R.drawable.ic_build_black_24dp);
                    achievement.checkIfCompleted(model.getToDoDataModule().getStats().getTimesSettingsChanged());
                    break;
                case(NumberAchievement.TIMESTASKDELETED):
                    achievementSymbol.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp);
                    achievement.checkIfCompleted(model.getToDoDataModule().getStats().getTimesTaskDeleted());
                    break;
                case(NumberAchievement.TIMESUPDATED):
                    achievementSymbol.setImageResource(R.drawable.ic_refresh_black_24dp);
                    achievement.checkIfCompleted(model.getToDoDataModule().getStats().getTimesUpdated());
                    break;
                default:
                    break;
            }

            achievementBackground.setImageResource(R.drawable.ic_badge_background);
            achievementBackground.setBackgroundColor(Color.WHITE);
            back.setBackgroundColor(Color.WHITE);

            checkWhichDifficulty();

            if (achievement.getIfCompleted()){
                checkWhichDifficultyCompleted();
            }
        }

        public  void checkWhichDifficultyCompleted(){
            if (achievement.getNumberLimit() == 2) {
                achievementBackground.setColorFilter(Color.rgb(205, 127, 50));
            } else if (achievement.getNumberLimit() == 5) {
                achievementBackground.setColorFilter(Color.rgb(192, 192, 192));
            } else if (achievement.getNumberLimit() == 8) {
                achievementBackground.setColorFilter(Color.rgb(255, 215, 0));
            } else if (achievement.getNumberLimit() == 10) {
                achievementBackground.setColorFilter(Color.rgb(185, 242, 255));
            }
        }

        public void checkWhichDifficulty(){
            if (achievement.getNumberLimit() == 2) {
                achievementBackground.setColorFilter(Color.rgb(80, 50, 20));
            } else if (achievement.getNumberLimit() == 5) {
                achievementBackground.setColorFilter(Color.rgb(75, 75, 75));
            } else if (achievement.getNumberLimit() == 8) {
                achievementBackground.setColorFilter(Color.rgb(100, 84, 0));
            } else if (achievement.getNumberLimit() == 10) {
                achievementBackground.setColorFilter(Color.rgb(0, 90, 110));
            }
        }
    }
}