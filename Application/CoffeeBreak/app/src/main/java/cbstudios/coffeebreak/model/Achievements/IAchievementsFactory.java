package cbstudios.coffeebreak.model.Achievements;

/**
 * Created by johan on 5/6/2017.
 */

public interface IAchievementsFactory {

    numberAchievments createNumberAchievements(String name, int numberLimit);

    timeAcvhievements createTimeAchievements(String name);
}
