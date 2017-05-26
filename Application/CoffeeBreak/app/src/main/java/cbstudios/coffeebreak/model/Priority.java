package cbstudios.coffeebreak.model;

/**
 * @author Zack, Felix
 * @version 1.2
 *          Responsibility: Represents a tasks priority in the model
 *          Used by: IAdvancedTask, AdvancedTask, TaskConverter, TaskEditPresenter
 */
public enum Priority {
    ONE("#FF0000"),
    TWO("#FFA00F"),
    THREE("#FFFA00"),
    NONE("#FFFFFFFF");

    private String color;

    /**
     * Returns the color of a Priority.
     *
     * @return The color in hex in string.
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of a Priority.
     *
     * @param color The color in hex in string.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @param color Color for the Priority.
     */
    Priority(String color) {
        this.color = color;
    }
}