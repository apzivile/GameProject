package lt.baltictalents.clearingdarkness.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import lt.baltictalents.clearingdarkness.ShooterGame;
import lt.baltictalents.clearingdarkness.entityManager.AnimationManager;

import static lt.baltictalents.clearingdarkness.ShooterGame.HEIGHT;
import static lt.baltictalents.clearingdarkness.ShooterGame.WIDTH;

//vibration

public class GameScreen extends ApplicationAdapter implements Screen {

    AnimationManager animationManager;
    float stateTime;
    ShooterGame game;
    //    Player player;
    float x;
    float y;
    Texture playerTexture;
    Sprite playerSprite;
    AnimationManager playerAnimated;
    Camera camera;

    public GameScreen(final ShooterGame game) {
        this.game = game;
        y = 15;
        x = WIDTH / 2;
//        player = new Player();
        game.background.setSpeedFixed(false);
        playerTexture = new Texture(Gdx.files.internal("player_sprite_sheet.png"));
        playerSprite = new Sprite(playerTexture);
        playerAnimated = new AnimationManager(playerSprite);
        playerAnimated.setPosition(WIDTH / 2, HEIGHT / 7);
//        Gdx.input.setInputProcessor(new InputAdapter() {
//            @Override
//            public boolean touchDragged (int screenX, int screenY, int pointer) {
//                 if ((Gdx.input.isTouched() && game.camera.getInputInGameWorld().x >= WIDTH/2)) {
//
//            if (game.camera.getInputInGameWorld().x > playerAnimated.getX()) {
//                playerAnimated.moveRight();
//            } else {
//                playerAnimated.moveLeft();
//            }
//        }
//                return true;
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
//        player.draw(game.batch);
        playerAnimated.draw(game.batch);
        game.batch.end();
        handleInput();
        playerAnimated.move();
//        Gdx.input.setInputProcessor(new InputAdapter() {
//            @Override
//            public boolean touchDragged (int screenX, int screenY, int pointer) {
//                if ((Gdx.input.isTouched() && game.camera.getInputInGameWorld().x >= WIDTH/2)) {
//
//                    if (game.camera.getInputInGameWorld().x > playerAnimated.getX()) {
//                        playerAnimated.moveRight();
//                    } else {
//                        playerAnimated.moveLeft();
//                    }
//                }
//                return true;
//            }
//        });
    }

    private void handleInput() {
//        if (Gdx.input.isTouched()){
        if ((Gdx.input.isTouched() && game.camera.getInputInGameWorld().x >= WIDTH / playerAnimated.getX())) {
            if (game.camera.getInputInGameWorld().x > playerAnimated.getX()) {
                playerAnimated.moveRight();
            } else {
                playerAnimated.moveLeft();
            }
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
