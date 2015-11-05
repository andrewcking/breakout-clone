package breakout;

import acm.graphics.*;
import acm.program.GraphicsProgram;
import java.awt.Color;
import java.awt.event.MouseEvent;
import svu.csc213.Dialog;

/**
 *
 * @author Adrix (Andrew King)
 */
public class Breakout extends GraphicsProgram {

    private int ballCount = 3;
    private int brickCount = 90;
    private Ball ball;
    private Paddle paddle;
    private GLabel ballLabel;
    private GLabel brickLabel;
    private GLabel gameLabel;
    private static final int BRICK_WIDTH = 80;
    private static final int BRICK_HEIGHT = 20;
    private static final int BRICK_SPACER = 3;

    @Override
    public void init() {
        setBackground(Color.black);
        gameLabel = new GLabel("Breakout!");
        gameLabel.setFont("Times-bold-24");
        gameLabel.setColor(new Color(209, 95, 238));
        add(gameLabel, (getWidth() - gameLabel.getWidth()) / 2, 22);
        ballLabel = new GLabel("Remaining Balls:" + ballCount);
        ballLabel.setFont("Times-bold-20");
        ballLabel.setColor(Color.white);
        add(ballLabel, 10, 22);
        brickLabel = new GLabel("Remaining Bricks:" + brickCount);
        brickLabel.setFont("Times-bold-20");
        brickLabel.setColor(Color.white);
        add(brickLabel, getWidth() - brickLabel.getWidth() - 30, 22);

        ball = new Ball(20, 20);
        add(ball, getWidth() / 2, 300);
        paddle = new Paddle(80, 15);
        add(paddle, (getWidth() - paddle.getWidth()) / 2, getHeight() - paddle.getHeight() * 7);
        for (int row = 0; row < BRICK_COLOR.length; ++row) {
            for (int col = 0; col < getWidth() / (BRICK_WIDTH + BRICK_SPACER); ++col) {
                Brick brick = new Brick(BRICK_WIDTH, BRICK_HEIGHT);
                brick.setFilled(true);
                brick.setColor(BRICK_COLOR[row]);
                add(brick, BRICK_SPACER + col * (BRICK_WIDTH + BRICK_SPACER), (BRICK_HEIGHT + row * (BRICK_HEIGHT + BRICK_SPACER)) + 10);
            }
        }
        this.addMouseListeners();

    }

    @Override
    public void run() {
        while (true) {
            ball.move();
            if (ball.getX() < 0 || ball.getX() + ball.getWidth() > getWidth()) {
                ball.bounceX();
            }
            if (ball.getY() < 0) {
                ball.bounceY();
            }
            if (ball.getY() + ball.getHeight() > getHeight()) {
                if (ballCount > 1) {
                    ball.setLocation(getWidth() / 2, 300);
                    ballCount--;
                    ballLabel.setLabel("Remaining Balls:" + ballCount);
                } else {
                    Dialog.showMessage(this, "Game Over.");
                    System.exit(0);
                }
                pause(1000);
            }
            checkForCollision();
            pause(5);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paddle.setLocation(e.getX() - paddle.getWidth() / 2, paddle.getY());
    }

    private static final Color[] BRICK_COLOR = {
        Color.red, Color.red, Color.orange, Color.orange, Color.yellow, Color.yellow, Color.green, Color.green, Color.blue, Color.blue
    };

    private void checkForCollision() {
        GObject obj;
        obj = getElementAt(ball.getX(), ball.getY());
        if (obj != null) {
            ball.bounceY();
            if (obj instanceof Brick) {
                remove(obj);
                brickCount--;
                brickLabel.setLabel("Remaining Bricks:" + brickCount);
                if (brickCount < 1) {
                    Dialog.showMessage(this, "You Won!");
                    System.exit(0);
                }
            }
            return;
        }
        obj = getElementAt(ball.getX() + ball.getWidth(), ball.getY());
        if (obj != null) {
            ball.bounceY();
            if (obj instanceof Brick) {
                remove(obj);
                brickCount--;
                brickLabel.setLabel("Remaining Bricks:" + brickCount);
                if (brickCount < 1) {
                    Dialog.showMessage(this, "You Won!");
                    System.exit(0);
                }
            }
            return;
        }
        obj = getElementAt(ball.getX() + ball.getWidth(), ball.getY() + ball.getHeight());
        if (obj != null) {
            ball.bounceY();
            if (obj instanceof Brick) {
                remove(obj);
                brickCount--;
                brickLabel.setLabel("Remaining Bricks:" + brickCount);
                if (brickCount < 1) {
                    Dialog.showMessage(this, "You Won!");
                    System.exit(0);
                }
            }
            return;
        }
        obj = getElementAt(ball.getX(), ball.getY() + ball.getHeight());
        if (obj != null) {
            ball.bounceY();
            if (obj instanceof Brick) {
                remove(obj);
                brickCount--;
                brickLabel.setLabel("Remaining Bricks:" + brickCount);
                if (brickCount < 1) {
                    Dialog.showMessage(this, "You Won!");
                    System.exit(0);
                }
            }
            return;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Breakout().start();
    }

}
