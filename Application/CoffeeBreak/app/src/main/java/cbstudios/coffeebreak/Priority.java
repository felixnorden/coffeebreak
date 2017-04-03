package geometri;

import java.awt.*;

public enum Priority {
    ONE(Color.RED),
    TWO(Color.ORANGE),
    THREE(Color.YELLOW);

    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    Priority(Color color) {
        this.color = color;
    }
}
