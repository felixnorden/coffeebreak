package cbstudios.coffeebreak.model.Achievements;

/**
 * Created by johan on 5/6/2017.
 */


public class numberAchievments implements IAchievements {
    String name;
    int numberLimit;
    Boolean isCompleted;

    /**
     * Class constructor
     * @param name is the name of the achievement
     * @param numberLimit is the number that of times a specific assigment has to done
     *                    in order to complete the achievement
     */
    public numberAchievments(String name, int numberLimit){
        this.name = name + " " + numberLimit;
        this.numberLimit = numberLimit;
        this.isCompleted = false;
    }

    /**
     *
     * @return the name of the achievement
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * set the achievement to completed
     */
    @Override
    public void setCompleted() {
        this.isCompleted = true;
    }

    /**
     *
     * @return an int that is unique from different objects
     */
    public int hashCode() {
        int result = 13;
        result = 31 * result + (name != null ? name.hashCode() : 0);

        return result;
    }

    /**
     * An equals method for the class
     * @param o is the object that the method will compare to
     * @return True if equal, false if not equal
     */
    @Override
    public boolean equals(Object o){
        return false;
    }
}
