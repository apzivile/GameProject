package lt.baltictalents.clearingdarkness.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import lt.baltictalents.clearingdarkness.ShooterGame;
import lt.baltictalents.clearingdarkness.tools.Background;

public class GameOverScreen implements Screen {
    private static final int BANNER_WIDTH = 350;
    private static final int BANNER_HEIGHT = 100;


    private static final int EXIT_BUTTON_WIDTH = 200;
    private static final int EXIT_BUTTON_HEIGHT = 80;

    private static final int TRY_AGAIN_BUTTON_WIDTH = 200;
    private static final int TRY_AGAIN_BUTTON_HEIGHT = 80;

    private static final int PLAY_BUTTON_Y = 230;
    private static final int EXIT_BUTTON_Y = 100;

    private ShooterGame game;
    private Texture gameOverBanner;
    private Texture tryAgainButtonActive;
    private Texture tryAgainButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;


    GameOverScreen(final ShooterGame game) {
        this.game = game;
        gameOverBanner = new Texture("game_over_banner.png");

        tryAgainButtonActive = new Texture("try_again_active.png");
        tryAgainButtonInactive = new Texture("try_again_inactive.png");
        exitButtonActive = new Texture("exit_game_over_button_active.png");
        exitButtonInactive = new Texture("exit_game_over_button_inactive.png");

        game.background.setSpeedFixed(true);
        game.background.setSpeed(Background.DEFAULT_SPEED);
        final GameOverScreen gameOverScreen = this;
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                int x = ShooterGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
                if (game.camera.getInputInGameWorld().x < x + EXIT_BUTTON_WIDTH && game.camera.getInputInGameWorld().x > x && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y > EXIT_BUTTON_Y) {
                    gameOverScreen.dispose();
                    Gdx.app.exit();
                }

                x = ShooterGame.WIDTH / 2 - TRY_AGAIN_BUTTON_WIDTH / 2;
                if (game.camera.getInputInGameWorld().x < x + TRY_AGAIN_BUTTON_WIDTH && game.camera.getInputInGameWorld().x > x && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y < PLAY_BUTTON_Y + TRY_AGAIN_BUTTON_HEIGHT && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y > PLAY_BUTTON_Y) {
                    gameOverScreen.dispose();
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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        game.background.updateAndRender(delta, game.batch);
        game.batch.draw(gameOverBanner, ShooterGame.WIDTH / 2 - BANNER_WIDTH / 2, ShooterGame.HEIGHT - BANNER_HEIGHT - 100, BANNER_WIDTH, BANNER_HEIGHT);

        int x = ShooterGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
        if (game.camera.getInputInGameWorld().x < x + EXIT_BUTTON_WIDTH && game.camera.getInputInGameWorld().x > x && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y > EXIT_BUTTON_Y) {
            game.batch.draw(exitButtonActive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        } else {
            game.batch.draw(exitButtonInactive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }

        x = ShooterGame.WIDTH / 2 - TRY_AGAIN_BUTTON_WIDTH / 2;
        if (game.camera.getInputInGameWorld().x < x + TRY_AGAIN_BUTTON_WIDTH && game.camera.getInputInGameWorld().x > x && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y < PLAY_BUTTON_Y + TRY_AGAIN_BUTTON_HEIGHT && ShooterGame.HEIGHT - game.camera.getInputInGameWorld().y > PLAY_BUTTON_Y) {
            game.batch.draw(tryAgainButtonActive, x, PLAY_BUTTON_Y, TRY_AGAIN_BUTTON_WIDTH, TRY_AGAIN_BUTTON_HEIGHT);
        } else {
            game.batch.draw(tryAgainButtonInactive, x, PLAY_BUTTON_Y, TRY_AGAIN_BUTTON_WIDTH, TRY_AGAIN_BUTTON_HEIGHT);
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
