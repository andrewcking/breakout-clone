package breakout;

import acm.graphics.GRect;
import java.awt.Color;

public class Paddle extends GRect {

    public Paddle(double width, double height) {
        super(width, height);
        setFilled(true);
        setFillColor(new Color(209,95,238));
    }

}
