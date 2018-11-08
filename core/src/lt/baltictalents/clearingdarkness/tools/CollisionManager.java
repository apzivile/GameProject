package lt.baltictalents.clearingdarkness.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lt.baltictalents.clearingdarkness.ShooterGame;
import lt.baltictalents.clearingdarkness.entityManager.AnimationManager;
import lt.baltictalents.clearingdarkness.entityManager.Asteroid;
import lt.baltictalents.clearingdarkness.entityManager.Enemy;
import lt.baltictalents.clearingdarkness.entityManager.ShotManager;

public class CollisionManager {

    private final AnimationManager playerAnimated;
    private final Enemy enemy;
    private final Asteroid asteroid;
    private final ShotManager shotManager;

    private float health = 1;
    private Texture blank;

    public CollisionManager(AnimationManager playerAnimated, Enemy enemy, Asteroid asteroid, ShotManager shotManager) {
        this.playerAnimated = playerAnimated;
        this.enemy = enemy;
        this.asteroid = asteroid;
        this.shotManager = shotManager;
        blank = new Texture("blank3.png");
    }


    public void handleCollision() {
        handlePlayerShot();
        handleEnemyShot();
    }

    private void handlePlayerShot() { // kill player
        if (shotManager.enemyShotTouches(playerAnimated.getBoundingBox()))
            health -= 0.1;
        if (asteroid.playerTouchesAsteroid(playerAnimated.getBoundingBox()))
            health -= 0.1;
    }

    private void handleEnemyShot() { // kill enemy
        if (shotManager.playerShotTouches(enemy.getBoundingBox()))
            enemy.hit();
    }

    public void draw(SpriteBatch batch) {
        if (Gdx.input.isTouched()) {
            if (health <= 0) {
                playerAnimated.setDead(false);
                health = 1;
            }
        }

        if (health > 0.6f)
            batch.setColor(Color.GREEN);
        else if (health > 0.2f)
            batch.setColor(Color.ORANGE);
        else
            batch.setColor(Color.RED);
        batch.draw(blank, 0, 0, ShooterGame.WIDTH * health, 150);
        batch.setColor(Color.WHITE);

    }

    public boolean isAlive() {
        return health > 0;
    }


}
