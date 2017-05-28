package cbstudios.coffeebreak.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cbstudios.coffeebreak.R;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.IAchievement;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.NumberAchievement;

/**
 * @author Johan
 * @version 1.0
 *          <p>Responsibility: Adapter to properly display data from achievements.</br >
 *          Used by: AchievementActivity.
 *          </p>
 */
public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder> implements IAchievementAdapter {

    private List<IAchievement> achievementList;

    /**
     * @param achievementList The list of achievement to show.
     */
    public AchievementAdapter(List<IAchievement> achievementList) {
        this.achievementList = achievementList;
    }


    @Override
    public AchievementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View achievementView;
        AchievementViewHolder viewHolder;

        achievementView = inflater.inflate(R.layout.achievement_badge_layout, parent, false);
        viewHolder = new AchievementViewHolder(achievementView);

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

    @Override
    public void refreshItems() {
        return;
    }

    public static class AchievementViewHolder extends RecyclerView.ViewHolder {

        public IAchievement achievement;
        public ImageView achievementBackground;
        public ImageView achievementSymbol;
        public TextView achievementText;
        public ImageView back;
        public TextView achievementNumber;


        /**
         * @param itemView The view in which the viewholder will be shown.
         */
        public AchievementViewHolder(View itemView) {
            super(itemView);
            achievementBackground = (ImageView) itemView.findViewById(R.id.achievementBackground);
            achievementSymbol = (ImageView) itemView.findViewById(R.id.achievementSymbol);
            achievementText = (TextView) itemView.findViewById(R.id.achievementText);
            back = (ImageView) itemView.findViewById(R.id.back);
            achievementNumber = (TextView) itemView.findViewById(R.id.achievementNumber);
        }

        /**
         * Setups the achievement.
         */
        public void setUpAchievement() {
            switch (achievement.getType()) {
                case (NumberAchievement.TASK_CREATED):
                    achievementSymbol.setImageResource(R.drawable.ic_edit_black_24dp);
                    achievementText.setText("Tasks Created");
                    break;
                case (NumberAchievement.TASK_CHECKED):
                    achievementSymbol.setImageResource(R.drawable.ic_done_black_24dp);
                    achievementText.setText("Tasks done");
                    break;
                case (NumberAchievement.DAYS_IN_A_ROW):
                    achievementSymbol.setImageResource(R.drawable.ic_date_range_black_24dp);
                    achievementText.setText("Days in a row");
                    break;
                case (NumberAchievement.TASKS_ALIVE):
                    achievementSymbol.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    achievementText.setText("Tasks alive");
                    break;
                case (NumberAchievement.TIMES_APP_STARTED):
                    achievementSymbol.setImageResource(R.drawable.ic_free_breakfast_black_24dp);
                    achievementText.setText("Addicted");
                    break;
                case (NumberAchievement.TIMES_CATEGORY_CREATED):
                    achievementSymbol.setImageResource(R.drawable.ic_create_new_folder_black_24dp);
                    achievementText.setText("Fine order");
                    break;
                case (NumberAchievement.TIMES_NAV_OPEN):
                    achievementSymbol.setImageResource(R.drawable.ic_menu_black_24dp);
                    achievementText.setText("Switcher");
                    break;
                case (NumberAchievement.TIMES_SETTINGS_CHANGED):
                    achievementSymbol.setImageResource(R.drawable.ic_build_black_24dp);
                    achievementText.setText("Changer");
                    break;
                case (NumberAchievement.TIMES_TASK_DELETED):
                    achievementSymbol.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp);
                    achievementText.setText("Tasks deleted");
                    break;
                case (NumberAchievement.TIMES_UPDATED):
                    achievementSymbol.setImageResource(R.drawable.ic_refresh_black_24dp);
                    achievementText.setText("Updater");
                    break;
                default:
                    break;
            }

            achievementBackground.setImageResource(R.drawable.ic_badge_background);
            achievementBackground.setBackgroundColor(Color.WHITE);
            back.setBackgroundColor(Color.WHITE);
            achievementNumber.setText(Integer.toString(achievement.getNumberLimit()));

            if (achievement.getIfCompleted()) {
                checkWhichDifficultyCompleted();
            } else {
                checkWhichDifficulty();
            }
        }


        /**
         * Checks if achievement is completed and sets color accordingly
         */
        public void checkWhichDifficultyCompleted() {
            if (achievement.getNumberLimit() == 2 || achievement.getNumberLimit() == 1 || achievement.getNumberLimit() == 5) {
                achievementBackground.setColorFilter(Color.rgb(205, 127, 50));
            } else if (achievement.getNumberLimit() == 3 || achievement.getNumberLimit() == 14 || achievement.getNumberLimit() == 25) {
                achievementBackground.setColorFilter(Color.rgb(192, 192, 192));
            } else if (achievement.getNumberLimit() == 7 || achievement.getNumberLimit() == 30 || achievement.getNumberLimit() == 100) {
                achievementBackground.setColorFilter(Color.rgb(255, 215, 0));
            } else if (achievement.getNumberLimit() == 10 || achievement.getNumberLimit() == 90 || achievement.getNumberLimit() == 500) {
                achievementBackground.setColorFilter(Color.rgb(185, 242, 255));
            }
        }


        /**
         * Sets the color of the task according to what difficulty currently resides.
         */
        public void checkWhichDifficulty() {
            if (achievement.getNumberLimit() == 2 || achievement.getNumberLimit() == 1 || achievement.getNumberLimit() == 5) {
                achievementBackground.setColorFilter(Color.rgb(80, 50, 20));
            } else if (achievement.getNumberLimit() == 3 || achievement.getNumberLimit() == 14 || achievement.getNumberLimit() == 25) {
                achievementBackground.setColorFilter(Color.rgb(75, 75, 75));
            } else if (achievement.getNumberLimit() == 7 || achievement.getNumberLimit() == 30 || achievement.getNumberLimit() == 100) {
                achievementBackground.setColorFilter(Color.rgb(100, 84, 0));
            } else if (achievement.getNumberLimit() == 10 || achievement.getNumberLimit() == 90 || achievement.getNumberLimit() == 500) {
                achievementBackground.setColorFilter(Color.rgb(0, 90, 110));
            }
        }
    }
}
