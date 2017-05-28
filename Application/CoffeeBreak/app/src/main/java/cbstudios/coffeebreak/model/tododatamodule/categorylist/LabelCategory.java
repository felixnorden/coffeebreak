package cbstudios.coffeebreak.model.tododatamodule.categorylist;////

/**
 * @author Johan, Elias, Felix
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import cbstudios.coffeebreak.model.tododatamodule.todolist.IAdvancedTask;

/**
 * A class that is responsibly for creating and changing the LabelCategories
 */
class LabelCategory implements ILabelCategory {
    // Max integer for random interval for color generation
    private static final int HEX_MAX = 256;

    private String name;
    private String color;

    /**
     * Creating a labelCategory with no name
     */
    LabelCategory() {
        this.name = "";
        this.color = getRandomColor();
    }

    /**
     * Creating a labelCateogory with a name and a random color
     *
     * @param name of the labelCategory
     */
    LabelCategory(String name) {
        this.name = name;
        this.color = getRandomColor();
    }

    /**
     * Creates a LabelCategory with the given name and color.
     *
     * @param name  Name of the category
     * @param color Color in hex
     */
    LabelCategory(String name, String color) {
        this.name = name;
        this.color = color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTaskCount(List<IAdvancedTask> tasks) {
        int num = 0;
        for (IAdvancedTask task : tasks) {
            for (int i = 0; i < task.getLabels().size(); i++) {
                if (task.getLabels().get(i).equals(this)) {
                    num++;
                }
            }
        }
        return num;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IAdvancedTask> getValidTasks(List<IAdvancedTask> tasks) {
        List<IAdvancedTask> validTasks = new ArrayList<>();
        for (IAdvancedTask task : tasks) {
            if (task.getName() == null) {
                task.addLabel(this);
            }
            for (ILabelCategory labelCategory : task.getLabels()) {
                if (labelCategory.getName().equals(this.getName())) {
                    validTasks.add(task);
                }
            }

        }
        return validTasks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getColor() {
        return color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * An equals method for the class
     *
     * @param o is the object that the method will compare to
     * @return True if equal, false if not equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){ return false;}

        LabelCategory that = (LabelCategory) o;

        return name != null ? name.equals(that.getName()) : that.name == null && Objects.equals(color, that.color);
    }

    /**
     * @return an int that is unique from different objects
     */
    @Override
    public int hashCode() {
        int result = 13;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /**
     * @return a random Color
     */
    private String getRandomColor() {
        Random rand = new Random();

        // Create RGB values
        int R = rand.nextInt(HEX_MAX);
        int G = rand.nextInt(HEX_MAX);
        int B = rand.nextInt(HEX_MAX);

        //Format to a RGB color string of hex values for parsing in application.
        return String.format("#%02X%02X%02X", R, G, B);
        //return (Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        //return Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
