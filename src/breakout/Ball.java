package breakout;

import acm.graphics.GOval;
import java.awt.Color;

public class Ball extends GOval {

    private double dx;
    private double dy;

    public Ball(double width, double height) {
        this(0, 0, width, height);
    }

    public Ball(double x, double y, double width, double height) {
        super(x, y, width, height);
        setFilled(true);
        setFillColor(Color.white);
        dx = 1;
        dy = 1;
    }

    public void move() {
        move(dx, dy);

    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void bounceX() {
        dx *= -1;
    }

    public void bounceY() {
        dy *= -1;
    }
}
