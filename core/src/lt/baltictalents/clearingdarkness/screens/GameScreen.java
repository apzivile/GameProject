package lt.baltictalents.clearingdarkness.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.lang.reflect.WildcardType;

import lt.baltictalents.clearingdarkness.ShooterGame;
import lt.baltictalents.clearingdarkness.entityManager.AnimationManager;
import lt.baltictalents.clearingdarkness.entityManager.Player;
import lt.baltictalents.clearingdarkness.entityManager.ShotManager;

import static lt.baltictalents.clearingdarkness.ShooterGame.HEIGHT;
import static lt.baltictalents.clearingdarkness.ShooterGame.WIDTH;

//vibration

public class GameScreen extends ApplicationAdapter implements Screen {

    private static final int SHOT_BUTTON_WIDTH = 300;
    private static final int SHOT_BUTTON_HEIGHT =120;
    private static final int SHOT_BUTTON_Y = 230;
    AnimationManager animationManager;
    float stateTime;
    ShooterGame game;
    //    Player player;
    float x;
    float y;
    Texture playerTexture;
    Texture shotButtonActive;
    Texture shotButtonInactive;
    Sprite playerSprite;
    AnimationManager playerAnimated;
    ShotManager shotManager;
//    Player player;

    public GameScreen(final ShooterGame game) {
        this.game = game;
        y = 15;
        x = WIDTH / 2;
//        player = new Player();
        game.background.setSpeedFixed(false);
//        shotButtonActive = new Texture(Gdx.files.internal("shot_button_active.png"));
//        shotButtonInactive = new Texture(Gdx.files.internal("shot_button_inactive.png"));
        playerTexture = new Texture(Gdx.files.internal("player_sprite_sheet.png"));
        playerSprite = new Sprite(playerTexture);
        playerAnimated = new AnimationManager(playerSprite);
        playerAnimated.setPosition(WIDTH / 2, HEIGHT / 7);
        shotManager = new ShotManager();
//        Gdx.input.setInputProcessor(new InputAdapter() {
//            @Override
//            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//                int x = ShooterGame.WIDTH / 2 - SHOT_BUTTON_WIDTH / 2;
//                if (game.camera.getInputInGameWorld().x < x + SHOT_BUTTON_WIDTH && game.camera.getInputInGameWorld().x > x && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y < SHOT_BUTTON_Y + SHOT_BUTTON_HEIGHT && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y > SHOT_BUTTON_Y) {
//                    int offset = 4;
//                    shotManager.firePlayerShot(playerAnimated.getWidth() - offset);
//                }
//                return super.touchUp(screenX, screenY, pointer, button);
//            }
//        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stateTime += delta;
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.background.updateAndRender(delta, game.batch);

        playerAnimated.draw(game.batch);

//        int x = ShooterGame.WIDTH / 2 - SHOT_BUTTON_WIDTH / 2;
//        if (game.camera.getInputInGameWorld().x < x + SHOT_BUTTON_WIDTH && game.camera.getInputInGameWorld().x > x && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y < SHOT_BUTTON_Y + SHOT_BUTTON_HEIGHT && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y > SHOT_BUTTON_Y) {
//            game.batch.draw(shotButtonActive, x, SHOT_BUTTON_Y, SHOT_BUTTON_WIDTH, SHOT_BUTTON_HEIGHT);
//        } else {
//            game.batch.draw(shotButtonInactive, x, SHOT_BUTTON_Y, SHOT_BUTTON_WIDTH, SHOT_BUTTON_HEIGHT);
//        }
//        player.draw(game.batch);

        shotManager.draw(game.batch);
        game.batch.end();

//       while (game.camera.getInputInGameWorld().x != playerAnimated.getX() ){
           handleInput();

//           if (game.camera.getInputInGameWorld().x == playerAnimated.getX()) {
//              playerAnimated.noMove();
//           } else
               playerAnimated.move();
        shotManager.update();
//        }
//        if (game.camera.getInputInGameWorld().x == playerAnimated.getX()){
//       }
    }

    private void handleInput() {

        if (Gdx.input.isTouched()) {
            if (game.camera.getInputInGameWorld().x >= playerAnimated.getX())
                playerAnimated.moveRight();
             else playerAnimated.moveLeft();
//            if (game.camera.getInputInGameWorld().x == playerAnimated.getX())
//                playerAnimated.slowDown();
//            else if (game.camera.getInputInGameWorld().y == playerAnimated.getY())
//                playerAnimated.dontMove();

//            } else if (Gdx.input.isTouched() && game.camera.getInputInGameWorld().x == WIDTH / playerAnimated.getX())
//                playerAnimated.noMove();
            shotManager.firePlayerShot(playerAnimated.getX());
        } else playerAnimated.dontMove();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
