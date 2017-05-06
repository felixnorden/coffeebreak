package cbstudios.coffeebreak.model.Achievements;

/**
 * Created by johan on 5/6/2017.
 */

public class numberAchievments implements IAchievements {
    String name;
    int numberLimit;
    Boolean isCompleted;

    public numberAchievments(String name, int numberLimit){
        this.name = name;
        this.numberLimit = numberLimit;
        this.isCompleted = false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setCompleted() {
        this.isCompleted = true;
    }

    public int hashCode() {
        int result = 13;
        result = 31 * result + (name != null ? name.hashCode() : 0);

        return result;
    }
}
