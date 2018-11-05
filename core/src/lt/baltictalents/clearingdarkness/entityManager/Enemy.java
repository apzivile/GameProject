package lt.baltictalents.clearingdarkness.entityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import lt.baltictalents.clearingdarkness.ShooterGame;

import static lt.baltictalents.clearingdarkness.entityManager.ShotManager.SHOT_SPEED;
import static lt.baltictalents.clearingdarkness.entityManager.ShotManager.SHOT_Y_OFFSET;

public class Enemy {

    private static final float ENEMY_SPEED = 250;
    Texture enemyShotTexture;
    Texture enemyTexture;
    Sprite enemySprite;
    AnimationManager enemyAnimated;
    private float spawnTimeout = 0;
    ShotManager shotManager;

    private static final float MINIMUM_TIME_BETWEEN_SPAWN = 3f;
    private static final float MINIMUM_TIME_BETWEEN_SHOT = 1f;
    private float timeSinceLastSpawn = 0;
    private float timeSinceLastShot = 0;

    private List<AnimationManager> enemies = new ArrayList<AnimationManager>();

    private List<AnimationManager> enemyShots = new ArrayList<AnimationManager>();

    public Enemy() {
        shotManager = new ShotManager();

        enemyShotTexture = new Texture(Gdx.files.internal("enemy_shot_sheet.png"));

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
        if (!enemyAnimated.isDead()) {
            for (AnimationManager enemy : enemies) {
                enemy.draw(batch);
            }

            for (AnimationManager shot : enemyShots) {
                shot.draw(batch);
            }
        }
    }

    public void update() {
//        if (enemyAnimated.isDead()) {
            Iterator<AnimationManager> i = enemies.iterator();
            while (i.hasNext()) {
                AnimationManager enemies = i.next();
                enemies.move();
                if (enemies.getY() > ShooterGame.HEIGHT)
                    i.remove();
            }

            Iterator<AnimationManager> j = enemyShots.iterator();
            while (j.hasNext()) {
                AnimationManager shot = j.next();
                shot.move();
                if (shot.getY() < 0)
                    j.remove();
            }


            if (shouldAppear()) {
                spawn();
            }
            if (shouldFire()) {
                fireEnemyShot(enemyAnimated.getX());
            }
            timeSinceLastSpawn += Gdx.graphics.getDeltaTime();
            timeSinceLastShot += Gdx.graphics.getDeltaTime();
            enemyAnimated.move();
        }
//    }

    private boolean shouldFire() {
//        Random random = new Random();
//        return random.nextInt(61)==0;

        return timeSinceLastShot > MINIMUM_TIME_BETWEEN_SHOT;
    }

    public boolean shouldAppear() {
        return timeSinceLastSpawn > MINIMUM_TIME_BETWEEN_SPAWN;

    }

    public void fireEnemyShot(int enemyCenterXLocation) {
        Sprite newShot = new Sprite(enemyShotTexture);
        AnimationManager newShotAnimated = new AnimationManager(newShot);
        newShotAnimated.setPosition(enemyCenterXLocation, enemyAnimated.getY() - 20);
        newShotAnimated.setVelocity(new Vector2(0, -SHOT_SPEED));
        enemyShots.add(newShotAnimated);
        timeSinceLastShot = 0f;
    }
//    public void handlePlayerShot() {
//        if (shotManager.enemyShotTouches(.getBoundingBox()))
//            spaceshipAnimated.setDead(true);
//
//    }
public Rectangle getBoundingBox() {
    return enemyAnimated.getBoundingBox();
}

    public void hit() {
        enemyAnimated.setDead(true);
        spawnTimeout = 2f;

    }
}