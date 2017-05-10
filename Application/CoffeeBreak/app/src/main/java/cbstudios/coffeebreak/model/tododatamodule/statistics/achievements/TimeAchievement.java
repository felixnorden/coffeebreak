package cbstudios.coffeebreak.model.tododatamodule.statistics.achievements;

/**
 * Created by johan on 5/6/2017.
 */


public class TimeAchievement implements IAchievement {
    String name;
    Boolean isCompleted;

    /**
     * The class constructor
     * @param name is the name of the achievement
     */
    public TimeAchievement(String name){
        this.name = name;
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
     * Set the achievement to completed if boolean is true
     *
     * @param isCompleted
     */
    @Override
    public void setIsCompleted(boolean isCompleted) {

    }

    /**
     * Check if an achievement is completed
     *
     * @param number is the number of times a specific assigment has to done
     *               in order to complete the achievement
     * @return true if achievement is completed, false if not
     */
    @Override
    public boolean isCompleted(int number) {
        return false;
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
    public boolean equals(Object o) {
        return false;
    }
}
