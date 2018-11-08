package lt.baltictalents.clearingdarkness.tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lt.baltictalents.clearingdarkness.ShooterGame;

public class Background {

    public static final int DEFAULT_SPEED = 80;
    private static final int ACCELERATION = 50;
    private static final int GOAL_REACH_ACCELERATION = 200;

    private Texture stars;
    private Texture background;
    private float y1, y2;
    private int speed;
    private int goalSpeed;
    private float backgroundScale;
    private boolean speedFixed;

    public Background() {
        background = new Texture("background.png");
        stars = new Texture("stars.png");
        y1 = 0;
        y2 = stars.getHeight();
        speed = 0;
        goalSpeed = DEFAULT_SPEED;
        backgroundScale = ShooterGame.WIDTH / stars.getWidth();
        speedFixed = true;
    }

    public void updateAndRender(float deltaTime, SpriteBatch batch) {
        if (speed < goalSpeed) {
            speed += GOAL_REACH_ACCELERATION * deltaTime;
            if (speed > goalSpeed)
                speed = goalSpeed;
        } else if (speed > goalSpeed) {
            speed -= GOAL_REACH_ACCELERATION * deltaTime;
            if (speed < goalSpeed)
                speed = goalSpeed;
        }

        if (!speedFixed)
            speed += ACCELERATION * deltaTime;

        y1 -= speed * deltaTime;
        y2 -= speed * deltaTime;

        if (y1 + stars.getHeight() * backgroundScale <= 0)
            y1 = y2 + stars.getHeight() * backgroundScale;

        if (y2 + stars.getHeight() * backgroundScale <= 0)
            y2 = y1 + stars.getHeight() * backgroundScale;

        batch.draw(background, 0, 0);
        batch.draw(stars, 0, y1, ShooterGame.WIDTH, stars.getHeight() * backgroundScale);
        batch.draw(stars, 0, y2, ShooterGame.WIDTH, stars.getHeight() * backgroundScale);
    }

    public void setSpeed(int goalSpeed) {
        this.goalSpeed = goalSpeed;
    }

    public void setSpeedFixed(boolean speedFixed) {
        this.speedFixed = speedFixed;
    }

}
