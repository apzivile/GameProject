package lt.baltictalents.clearingdarkness.entityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lt.baltictalents.clearingdarkness.ShooterGame;

public class ShotManager {
    static final int SHOT_SPEED = 300;
    private static final int SHOT_Y_OFFSET = 340;
    Texture shotTexture;
    private List<AnimationManager> shots = new ArrayList<AnimationManager>();

    public ShotManager() {
        shotTexture = new Texture(Gdx.files.internal("shot_sprite_sheet.png"));
    }

    public void firePlayerShot(int whaleCenterXLocation) {
        if(Gdx.input.justTouched()){
        Sprite newShot = new Sprite(shotTexture);
        AnimationManager newShotAnimated = new AnimationManager(newShot);
        newShotAnimated.setPosition(whaleCenterXLocation, SHOT_Y_OFFSET);
        newShotAnimated.setVelocity(new Vector2(0, SHOT_SPEED));
        shots.add(newShotAnimated);
        }
    }

    public void update() {

        Iterator<AnimationManager> i = shots.iterator();
        while (i.hasNext()) {
            AnimationManager shot = i.next();
            shot.move();
            if (shot.getY() > ShooterGame.HEIGHT)
                i.remove();
        }
    }

    public void draw(SpriteBatch batch) {
        for (AnimationManager shot : shots) {
            shot.draw(batch);
        }
    }
}
