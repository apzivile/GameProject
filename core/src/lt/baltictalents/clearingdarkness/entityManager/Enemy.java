package lt.baltictalents.clearingdarkness.entityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import lt.baltictalents.clearingdarkness.ShooterGame;

public class Enemy {

    private static final float ENEMY_SPEED = 250;
    private Texture enemyTexture;
    private AnimationManager enemyAnimated;
    private final ShotManager shotManager;
    private float spawnTimeout = 0f;

    public Enemy(ShotManager shotManager) {
        this.shotManager = shotManager;
        enemyTexture = new Texture(Gdx.files.internal("enemy_sprite_sheet.png"));
        spawn();
    }

    private void spawn() {
        Sprite enemySprite = new Sprite(enemyTexture);
        enemyAnimated = new AnimationManager(enemySprite);
        int xPosition = createRandomPosition();
        enemyAnimated.setPosition(xPosition, ShooterGame.HEIGHT - enemyAnimated.getHeight()-50);
        enemyAnimated.setVelocity(new Vector2(ENEMY_SPEED, 0));
        enemyAnimated.setDead(false);

    }

    private int createRandomPosition() {
        Random random = new Random();
        int randomNumber = random.nextInt(ShooterGame.WIDTH - enemyAnimated.getWidth() + 1);
        return randomNumber + enemyAnimated.getWidth() / 2;
    }

    public void draw(SpriteBatch batch) {
        if (!enemyAnimated.isDead())
            enemyAnimated.draw(batch);

    }

    public void update() {
        if (enemyAnimated.isDead()) {
            spawnTimeout -= Gdx.graphics.getDeltaTime();
            if (spawnTimeout <= 0) {
                spawn();
            }
        } else {
            if (shouldChangeDirection())
                enemyAnimated.changeDirection();

            if (shouldFire())
                shotManager.fireEnemyShot(enemyAnimated.getX());
            enemyAnimated.move();
        }

    }

    private boolean shouldFire() {
        Random random = new Random();
        return random.nextInt(101) == 0;
    }

    private boolean shouldChangeDirection() {
        Random random = new Random();
        return random.nextInt(21) == 0;
    }

    public Rectangle getBoundingBox() {
        return enemyAnimated.getBoundingBox();
    }

    public void hit() {
        enemyAnimated.setDead(true);
        spawnTimeout = 2f;

    }
}