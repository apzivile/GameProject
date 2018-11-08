package lt.baltictalents.clearingdarkness.entityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import lt.baltictalents.clearingdarkness.ShooterGame;

public class Asteroid {
    private static final float ASTEROID_SPEED = 150;
    private static final float MINIMUM_TIME_BETWEEN_SPAWN = 5f;
    private float timeSinceLastSpawn = 0;
    private Texture asteroidTexture;
    private AnimationManager newAsteroidAnimated;

    private List<AnimationManager> asteroids = new ArrayList<AnimationManager>();

    public Asteroid() {
        asteroidTexture = new Texture(Gdx.files.internal("asteroid_enemy_sheet.png"));
        spawn();
    }

    private void spawn() {
        Sprite newAsteroid = new Sprite(asteroidTexture);
        newAsteroidAnimated = new AnimationManager(newAsteroid);
        int xPosition = createRandomPosition();
        newAsteroidAnimated.setPosition(xPosition, ShooterGame.HEIGHT - newAsteroidAnimated.getHeight() - 1);
        newAsteroidAnimated.setVelocity(new Vector2(0, -ASTEROID_SPEED));
        asteroids.add(newAsteroidAnimated);
        timeSinceLastSpawn = 0f;
    }

    private int createRandomPosition() {
        Random random = new Random();
        int randomNumber = random.nextInt(ShooterGame.WIDTH - newAsteroidAnimated.getWidth() + 1);
        return randomNumber + newAsteroidAnimated.getWidth() / 2;
    }

    public void draw(SpriteBatch batch) {
        for (AnimationManager asteroid : asteroids) {
            asteroid.draw(batch);
        }
    }

    public void update() {
        Iterator<AnimationManager> i = asteroids.iterator();
        while (i.hasNext()) {
            AnimationManager asteroid = i.next();
            asteroid.move();
            if (asteroid.getY() > ShooterGame.HEIGHT)
                i.remove();
        }
        timeSinceLastSpawn += Gdx.graphics.getDeltaTime();
        if (shouldAppear()) {
            spawn();
        }
        newAsteroidAnimated.move();
    }

    private boolean shouldAppear() {
        return timeSinceLastSpawn > MINIMUM_TIME_BETWEEN_SPAWN;
    }

    public boolean playerTouchesAsteroid(Rectangle boundingBox) {
        return shotTouches(asteroids, boundingBox);
    }

    private boolean shotTouches(List<AnimationManager> asteroids, Rectangle boundingBox) {
        Iterator<AnimationManager> i = asteroids.iterator();
        Rectangle intersection = new Rectangle();
        while (i.hasNext()) {
            AnimationManager asteroid = i.next();
            if (Intersector.intersectRectangles(asteroid.getBoundingBox(), boundingBox, intersection)) {
                return true;
            }

        }
        return false;
    }
}