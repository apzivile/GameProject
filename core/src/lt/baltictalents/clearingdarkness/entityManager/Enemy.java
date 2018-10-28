package lt.baltictalents.clearingdarkness.entityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import lt.baltictalents.clearingdarkness.ShooterGame;

public class Enemy {

    private static final float ENEMY_SPEED = 250;
    Texture enemyTexture;
    Sprite enemySprite;
    AnimationManager enemyAnimated;
    private float spawnTimeout = 0;

    private static final float MINIMUM_TIME_BETWEEN_SPAWN = 3f;
    private float timeSinceLastSpawn = 0;

    private List<AnimationManager> enemies = new ArrayList<AnimationManager>();

    public Enemy() {
        enemyTexture = new Texture(Gdx.files.internal("enemy_sprite_sheet.png"));
        spawn();
    }

    private void spawn() {
        enemySprite = new Sprite(enemyTexture);
        enemyAnimated = new AnimationManager(enemySprite);
        int xPosition = createRandomPosition();
        enemyAnimated.setPosition(xPosition, ShooterGame.HEIGHT - enemyAnimated.getHeight());
        enemyAnimated.setVelocity(new Vector2(0, -ENEMY_SPEED));
        enemies.add(enemyAnimated);

        timeSinceLastSpawn = 0f;

    }

    private int createRandomPosition() {
        Random random = new Random();
        int randomNumber = random.nextInt(ShooterGame.WIDTH - enemyAnimated.getWidth() + 1);
        return randomNumber + enemyAnimated.getWidth() / 2;
    }

    public void draw(SpriteBatch batch) {
        for (AnimationManager enemy : enemies) {
            enemy.draw(batch);
        }
    }

    public void update() {
        Iterator<AnimationManager> i = enemies.iterator();
        while (i.hasNext()) {
            AnimationManager enemies = i.next();
            enemies.move();
            if (enemies.getY() > ShooterGame.HEIGHT)
                i.remove();
        }
        if (shouldAppear()) {
            spawn();
        }
        timeSinceLastSpawn += Gdx.graphics.getDeltaTime();
        enemyAnimated.move();
    }

    public boolean shouldAppear() {

        return timeSinceLastSpawn > MINIMUM_TIME_BETWEEN_SPAWN;

    }
}
