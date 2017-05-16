package cbstudios.coffeebreak.model.tododatamodule.statistics.achievements;

import org.greenrobot.eventbus.EventBus;

import cbstudios.coffeebreak.eventbus.AchievementEvent;

/**
 * Created by johan on 5/6/2017.
 */


public class NumberAchievement implements IAchievement {
    String name;
    int numberLimit;
    Boolean isCompleted;

    /**
     * Class constructor
     * @param name is the name of the achievement
     * @param numberLimit is the number of times a specific assigment has to done
     *                    in order to complete the achievement
     */
    public NumberAchievement(String name, int numberLimit){
        this.name = name;
        this.numberLimit = numberLimit;
        this.isCompleted = false;
    }

    public NumberAchievement(){

    }

    public int getNumberLimit(){
        return this.numberLimit;
    }

    public void setName(String name){
        this.name = name;
    }

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

    public void checkIfCompleted(int number){
        if(number >= numberLimit) {
            isCompleted = true;
            EventBus.getDefault().post(new AchievementEvent(this));

        }
    }

    /**
     * set the achievement to completed
     */
    @Override
    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

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
}
