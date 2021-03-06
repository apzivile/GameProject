package lt.baltictalents.clearingdarkness.entityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import lt.baltictalents.clearingdarkness.ShooterGame;

public class AnimationManager {
    private static final int PLAYER_SPEED = 700;
    private static final int FRAMES_COL = 2;
    private static final int FRAMES_ROW = 2;

    private Sprite sprite;
    private Animation<TextureRegion> animation;
    private Vector2 velocity = new Vector2();

    private float stateTime;
    private boolean isDead = false;


    public AnimationManager(Sprite sprite) {
        this.sprite = sprite;
        Texture texture = sprite.getTexture();
        TextureRegion[][] temp = TextureRegion.split(texture, (int) getSpriteWidth(), texture.getHeight() / FRAMES_ROW);
        TextureRegion[] frames = new TextureRegion[FRAMES_COL * FRAMES_ROW];
        int index = 0;
        for (int i = 0; i < FRAMES_ROW; i++) {
            for (int j = 0; j < FRAMES_COL; j++) {
                frames[index++] = temp[i][j];
            }
        }

        animation = new Animation<TextureRegion>(0.1f, frames);
        stateTime = 0f;
    }

    public void draw(SpriteBatch spriteBatch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);

        spriteBatch.draw(currentFrame, sprite.getX(), sprite.getY());
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x - getSpriteCenterOffset(), y);
    }

    private float getSpriteCenterOffset() {
        return getSpriteWidth() / 2;
    }

    private float getSpriteWidth() {
        return sprite.getWidth() / FRAMES_COL;
    }

    public void moveRight() {
        velocity = new Vector2(PLAYER_SPEED, 0);
    }

    public void moveLeft() {
        velocity = new Vector2(-PLAYER_SPEED, 0);
    }

    public void dontMove() {
        velocity = new Vector2(0, 0);
    }


    public int getX() {
        return (int) (sprite.getX() + getSpriteCenterOffset());
    }


    public void move() {
        int xMovement = (int) (velocity.x * Gdx.graphics.getDeltaTime());
        int yMovement = (int) (velocity.y * Gdx.graphics.getDeltaTime());
        sprite.setPosition(sprite.getX() + xMovement, sprite.getY() + yMovement);

        if (sprite.getX() < 0)
            sprite.setX(0);

        if (sprite.getX() + getSpriteWidth() > ShooterGame.WIDTH) {
            sprite.setX(ShooterGame.WIDTH - getSpriteWidth());
        }
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public int getY() {
        return (int) sprite.getY();
    }

    public int getWidth() {
        return (int) getSpriteWidth();
    }

    public int getHeight() {
        return (int) sprite.getHeight() / FRAMES_ROW;
    }

    public void changeDirection() {
        velocity.x = -velocity.x;
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(sprite.getX(), sprite.getY(), getWidth()-50, getHeight()-50);
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    public boolean isDead() {
        return isDead;
    }
}
