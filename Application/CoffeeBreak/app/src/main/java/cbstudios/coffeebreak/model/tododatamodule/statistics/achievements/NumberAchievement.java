package cbstudios.coffeebreak.model.tododatamodule.statistics.achievements;

/**
 * Created by johan on 5/6/2017.
 */


public class NumberAchievement implements IAchievement {

    public static final int CREATE = 0;
    public static final int CHECK = 1;
    public static final int TIMESUPDATED = 2;
    public static final int TIMESAPPSTARTED = 3;
    public static final int TIMESNAVOPEN = 4;
    public static final int TIMESTASKDELETED = 5;
    public static final int TIMESCATEGORYCREATED = 6;
    public static final int TIMESSETTINGSCHANGED = 7;
    public static final int TASKSALIVE = 8;
    public static final int DAYSINAROW = 9;

    private int type;

    private String name;
    private int numberLimit;
    private Boolean isCompleted;

    /**
     * Class constructor
     * @param name is the name of the achievement
     * @param numberLimit is the number of times a specific assigment has to done
     *                    in order to complete the achievement
     */
    public NumberAchievement(String name, int numberLimit, int type){
        this.name = name;
        this.numberLimit = numberLimit;
        this.isCompleted = false;
        this.type = type;
    }


    public NumberAchievement(){

    }

    /**
     * @return the achievement numberLimit int
     */
    public int getNumberLimit(){
        return this.numberLimit;
    }

    /**
     * sets the name of achievement
     * @param name is the name of the achievement
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * sets the numberLimit in the achievement
     * @param numberLimit is an int that represent how many times a specific thing must be done in
     *                    order to complete the achievement
     */
    public void setNumberLimit(int numberLimit){
        this.numberLimit = numberLimit;
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
     * Checks if the achievement is completed by comparing the achievement numberLimit and another int
     * @param number is the number of times a specific thing has been done
     */
    public void checkIfCompleted(int number){
        if(number >= numberLimit) {
            isCompleted = true;
        }
    }

    /**
     * set the achievement to completed
     */
    @Override
    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * @return the achievements boolean isCompleted
     */
    @Override
    public boolean getIfCompleted() {
        return isCompleted;
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

    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type;
    }
}
