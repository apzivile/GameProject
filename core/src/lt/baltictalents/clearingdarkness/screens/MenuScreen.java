package lt.baltictalents.clearingdarkness.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import lt.baltictalents.clearingdarkness.ShooterGame;
import lt.baltictalents.clearingdarkness.tools.Background;

public class MenuScreen implements Screen {
    private static final int PLAY_BUTTON_WIDTH = 300;
    private static final int PLAY_BUTTON_HEIGHT = 120;
    private static final int EXIT_BUTTON_WIDTH = 200;
    private static final int EXIT_BUTTON_HEIGHT = 80;
    private static final int PLAY_BUTTON_Y = 230;
    private static final int EXIT_BUTTON_Y = 100;

    final ShooterGame game;

    Texture playButtonActive;
    Texture playButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;

    public MenuScreen(final ShooterGame game) {
        this.game = game;

        playButtonActive = new Texture("play_button_active.png");
        playButtonInactive = new Texture("play_button_inactive.png");
        exitButtonActive = new Texture("exit_button_active.png");
        exitButtonInactive = new Texture("exit_button_inactive.png");


        game.background.setSpeedFixed(true);
        game.background.setSpeed(Background.DEFAULT_SPEED);

        final MenuScreen menuScreen = this;

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//                Exit button
                int x = ShooterGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
                if (game.camera.getInputInGameWorld().x < x + EXIT_BUTTON_WIDTH && game.camera.getInputInGameWorld().x > x && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y > EXIT_BUTTON_Y) {
                    menuScreen.dispose();
                    Gdx.app.exit();
                }

                //Play game button
                x = ShooterGame.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
                if (game.camera.getInputInGameWorld().x < x + PLAY_BUTTON_WIDTH && game.camera.getInputInGameWorld().x > x && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y > PLAY_BUTTON_Y) {
                    menuScreen.dispose();
                    game.setScreen(new GameScreen(game));
                }

                return super.touchUp(screenX, screenY, pointer, button);
            }

        });
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        game.background.updateAndRender(delta, game.batch);

        int x = ShooterGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
        if (game.camera.getInputInGameWorld().x < x + EXIT_BUTTON_WIDTH && game.camera.getInputInGameWorld().x > x && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y > EXIT_BUTTON_Y) {
            game.batch.draw(exitButtonActive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        } else {
            game.batch.draw(exitButtonInactive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }

         x = ShooterGame.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
        if (game.camera.getInputInGameWorld().x < x + PLAY_BUTTON_WIDTH && game.camera.getInputInGameWorld().x > x && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y > PLAY_BUTTON_Y) {
            game.batch.draw(playButtonActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }
        else {
            game.batch.draw(playButtonInactive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }

        game.batch.end();

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
        Gdx.input.setInputProcessor(null);
    }
}
