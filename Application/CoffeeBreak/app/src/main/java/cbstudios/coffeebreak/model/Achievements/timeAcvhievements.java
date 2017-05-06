package cbstudios.coffeebreak.model.Achievements;

/**
 * Created by johan on 5/6/2017.
 */

public class timeAcvhievements implements IAchievements {
    String name;
    Boolean isCompleted;

    public timeAcvhievements(String name){
        this.name = name;
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
