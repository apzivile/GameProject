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

    private static final int LOGO_BUTTON_Y = 650;
    private static final int LOGO_BUTTON_WIDTH = 600;
    private static final int LOGO_BUTTON_HEIGHT = 480;

    private final ShooterGame game;

    private Texture logo;
    private Texture playButtonActive;
    private Texture playButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;

    public MenuScreen(final ShooterGame game) {
        this.game = game;

        logo = new Texture("logo.png");

        playButtonActive = new Texture("play_button_active1.png");
        playButtonInactive = new Texture("play_button_inactive1.png");
        exitButtonActive = new Texture("exit_button_active1.png");
        exitButtonInactive = new Texture("exit_button_inactive1.png");


        game.background.setSpeedFixed(true);
        game.background.setSpeed(Background.DEFAULT_SPEED);

        final MenuScreen menuScreen = this;

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                int x = ShooterGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
                if (game.camera.getInputInGameWorld().x < x + EXIT_BUTTON_WIDTH && game.camera.getInputInGameWorld().x > x && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y > EXIT_BUTTON_Y) {
                    menuScreen.dispose();
                    Gdx.app.exit();
                }

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
        } else {
            game.batch.draw(playButtonInactive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }
        x = ShooterGame.WIDTH / 2 - LOGO_BUTTON_WIDTH / 2;
        game.batch.draw(logo, x, LOGO_BUTTON_Y, LOGO_BUTTON_WIDTH, LOGO_BUTTON_HEIGHT);

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
