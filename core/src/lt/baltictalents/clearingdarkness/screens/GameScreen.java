package lt.baltictalents.clearingdarkness.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import lt.baltictalents.clearingdarkness.ShooterGame;
import lt.baltictalents.clearingdarkness.entityManager.Player;
import static lt.baltictalents.clearingdarkness.ShooterGame.WIDTH;

public class GameScreen extends ApplicationAdapter implements Screen {

    float stateTime;
    ShooterGame game;
    Player player;
    float x;
    float y;

    public GameScreen(ShooterGame game) {
        this.game = game;
        y = 15;
        x = WIDTH / 2;
        player = new Player();
        game.background.setSpeedFixed(false);

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
        player.draw(game.batch);
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

    }

}
