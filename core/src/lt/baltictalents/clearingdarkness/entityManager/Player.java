package lt.baltictalents.clearingdarkness.entityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static lt.baltictalents.clearingdarkness.ShooterGame.HEIGHT;
import static lt.baltictalents.clearingdarkness.ShooterGame.WIDTH;

public class Player {
    //    private static final int PLAYER_SPEED = 400;
//    private static final int FRAMES_COL = 2;
//    private static final int FRAMES_ROW = 2;
//    CameraManager camera;
    Texture playerTexture;
    Sprite playerSprite;
    AnimationManager playerAnimated;
//    Vector2 velocity = new Vector2();


    public Player() {
//        camera = new CameraManager(WIDTH, HEIGHT);
        playerTexture = new Texture(Gdx.files.internal("player_sprite_sheet.png"));
        playerSprite = new Sprite(playerTexture);
        playerAnimated = new AnimationManager(playerSprite);
        playerAnimated.setPosition(WIDTH / 2, HEIGHT/7);

    }

    public void draw(SpriteBatch batch) {
        playerAnimated.draw(batch);
    }
//
//    public void setVelocity(Vector2 velocity) {
//        this.velocity = velocity;
//    }
//    public int getX() {
//        return (int) (playerSprite.getX() + getSpriteCenterOffset());
//    }
//    public void moveRight() {
//        velocity = new Vector2(PLAYER_SPEED, 0);
//    }
//
//    public void moveLeft() {
//        velocity = new Vector2(-PLAYER_SPEED, 0);
//    }
//    private float getSpriteCenterOffset() {
//        return getSpriteWidth() / 2;
//    }
//
//    private float getSpriteWidth() {
//        return playerSprite.getWidth() / FRAMES_COL;
//    }
//    public void move() {
//        int xMovement = (int) (velocity.x * Gdx.graphics.getDeltaTime());
//        int yMovement = (int) (velocity.y * Gdx.graphics.getDeltaTime());
//        playerSprite.setPosition(playerSprite.getX() + xMovement, playerSprite.getY() + yMovement);
//
//        if (playerSprite.getX() < 0) {
//            playerSprite.setX(0);
//
//        }
//
//        if (playerSprite.getX() + getSpriteWidth() > WIDTH) {
//            playerSprite.setX(WIDTH - getSpriteWidth());
//
//        }
//
//    }
//    public void handleInput() {
//        if (Gdx.input.isTouched()) {
//
//            Vector3 touchPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//            camera.getInputInGameWorld();
//            if (touchPosition.x > playerSprite.getX()) {
//                moveRight();
//            } else {
//              moveLeft();
//            }
//        }
//    }
}
