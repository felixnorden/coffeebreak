package cbstudios.coffeebreak.model.tododatamodule.statistics.achievements;

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
        this.name = name + " " + numberLimit;
        this.numberLimit = numberLimit;
        this.isCompleted = false;
    }

    public NumberAchievement(){

    }

    public int getNumberLimit(){
        return this.numberLimit;
    }

    public boolean checkIfCompleted(){
        return this.isCompleted;
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

    /**
     * set the achievement to completed
     */
    @Override
    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Check if an achievement is completed
     *
     * @param number is the number of times a specific assigment has to done
     *               in order to complete the achievement
     * @return true  if achievement is completed, false if not
     */
    @Override
    public boolean isCompleted(int number) {
        if (this.numberLimit == number) {
            return true;
        }
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
    public boolean equals(Object o){
        return false;
    }
}
