package cbstudios.coffeebreak.model;

import android.graphics.Color;


/*
Colors are represented by integers in Android. Full list of colors below.
 */
public enum Priority {
    ONE(Color.RED),
    TWO(Color.BLACK), //Orange
    THREE(Color.YELLOW),
    NONE(Color.TRANSPARENT);

    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    Priority(int color) {
        this.color = color;
    }

    Priority(String hexColor) {
        this.color = Color.parseColor(hexColor);
    }
}

/*
int BLACK
Constant Value: -16777216 (0xff000000)

int BLUE
Constant Value: -16776961 (0xff0000ff)

int CYAN
Constant Value: -16711681 (0xff00ffff)

int DKGRAY
Constant Value: -12303292 (0xff444444)

int GRAY
Constant Value: -7829368 (0xff888888)

int GREEN
Constant Value: -16711936 (0xff00ff00)

int LTGRAY
Constant Value: -3355444 (0xffcccccc)

int MAGENTA
Constant Value: -65281 (0xffff00ff)

int RED
Constant Value: -65536 (0xffff0000)

int TRANSPARENT
Constant Value: 0 (0x00000000)

int WHITE
Constant Value: -1 (0xffffffff)

YELLOW
int YELLOW
Constant Value: -256 (0xffffff00)
 */

