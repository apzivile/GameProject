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

import lt.baltictalents.clearingdarkness.ShooterGame;

public class ShotManager {
    private static final int SHOT_SPEED = 700;
    private static final int SHOT_Y_OFFSET = 340;
    private static final float MINIMUM_TIME_BETWEEN_SHOTS = .5f;
    private static final float ENEMY_SHOT_Y_OFFSET = 1090;
    private Texture enemyShotTexture;
    private float timeSinceLastShot = 0;
    private Texture playerShotTexture;
    private List<AnimationManager> shots = new ArrayList<AnimationManager>();
    private List<AnimationManager> enemyShots = new ArrayList<AnimationManager>();

    public ShotManager() {
        playerShotTexture = new Texture(Gdx.files.internal("shot_sprite_sheet.png"));

        enemyShotTexture = new Texture(Gdx.files.internal("enemy_shot_sheet.png"));
    }

    public void firePlayerShot(int whaleCenterXLocation) {
        if (Gdx.input.justTouched() && canFireShot()) {
            Sprite newShot = new Sprite(playerShotTexture);
            AnimationManager newShotAnimated = new AnimationManager(newShot);
            newShotAnimated.setPosition(whaleCenterXLocation, SHOT_Y_OFFSET);
            newShotAnimated.setVelocity(new Vector2(0, SHOT_SPEED));
            shots.add(newShotAnimated);
            timeSinceLastShot = 0f;
        }
    }

    public void fireEnemyShot(int enemyCenterXLocation) {

        Sprite newShot = new Sprite(enemyShotTexture);
        AnimationManager newShotAnimated = new AnimationManager(newShot);
        newShotAnimated.setPosition(enemyCenterXLocation, ENEMY_SHOT_Y_OFFSET);
        newShotAnimated.setVelocity(new Vector2(0, -SHOT_SPEED));
        enemyShots.add(newShotAnimated);
    }

    private boolean canFireShot() {
        return timeSinceLastShot > MINIMUM_TIME_BETWEEN_SHOTS;
    }

    public void update() {

        Iterator<AnimationManager> i = shots.iterator();
        while (i.hasNext()) {
            AnimationManager shot = i.next();
            shot.move();
            if (shot.getY() > ShooterGame.HEIGHT)
                i.remove();
        }
        Iterator<AnimationManager> j = enemyShots.iterator();
        while (j.hasNext()) {
            AnimationManager shot = j.next();
            shot.move();
            if (shot.getY() < 0)
                j.remove();
        }
        timeSinceLastShot += Gdx.graphics.getDeltaTime();
    }

    public void draw(SpriteBatch batch) {
        for (AnimationManager shot : shots) {
            shot.draw(batch);
        }
        for (AnimationManager shot : enemyShots) {
            shot.draw(batch);
        }
    }

    public boolean playerShotTouches(Rectangle boundingBox) {
        return shotTouches(shots, boundingBox);
    }

    public boolean enemyShotTouches(Rectangle boundingBox) {
        return shotTouches(enemyShots, boundingBox);
    }

    private boolean shotTouches(List<AnimationManager> shots, Rectangle boundingBox) {
        Iterator<AnimationManager> i = shots.iterator();
        Rectangle intersection = new Rectangle();
        while (i.hasNext()) {
            AnimationManager shot = i.next();
            if (Intersector.intersectRectangles(shot.getBoundingBox(), boundingBox, intersection)) {
                i.remove();
                return true;
            }

        }
        return false;
    }


}