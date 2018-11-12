package lt.baltictalents.clearingdarkness.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;

import lt.baltictalents.clearingdarkness.ShooterGame;
import lt.baltictalents.clearingdarkness.entityManager.AnimationManager;
import lt.baltictalents.clearingdarkness.entityManager.Asteroid;
import lt.baltictalents.clearingdarkness.entityManager.Enemy;
import lt.baltictalents.clearingdarkness.entityManager.ShotManager;
import lt.baltictalents.clearingdarkness.tools.CollisionManager;

import static lt.baltictalents.clearingdarkness.ShooterGame.HEIGHT;
import static lt.baltictalents.clearingdarkness.ShooterGame.WIDTH;

public class GameScreen extends ApplicationAdapter implements Screen {

    private ShooterGame game;
    private Enemy enemy;
    private Asteroid asteroid;
    private AnimationManager playerAnimated;
    private ShotManager shotManager;
    private CollisionManager collisionManager;

    private BitmapFont scoreFont;

    GameScreen(final ShooterGame game) {
        this.game = game;
        asteroid = new Asteroid();
        scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        Texture playerTexture = new Texture(Gdx.files.internal("player_sprite_sheet.png"));
        Sprite playerSprite = new Sprite(playerTexture);
        playerAnimated = new AnimationManager(playerSprite);
        playerAnimated.setPosition(WIDTH / 2, HEIGHT / 7);
        shotManager = new ShotManager();
        enemy = new Enemy(shotManager);
        collisionManager = new CollisionManager(playerAnimated, enemy, asteroid, shotManager);
        game.background.setSpeedFixed(false);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (!collisionManager.isAlive()) {
            this.dispose();
            game.setScreen(new GameOverScreen(game, enemy.score));
            return;
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.background.updateAndRender(delta, game.batch);

        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "" + enemy.score);
        scoreFont.draw(game.batch, scoreLayout, ShooterGame.WIDTH / 2 - scoreLayout.width / 2, ShooterGame.HEIGHT - scoreLayout.height - 10);

        collisionManager.draw(game.batch);
        playerAnimated.draw(game.batch);
        asteroid.draw(game.batch);
        enemy.draw(game.batch);
        shotManager.draw(game.batch);
        game.batch.end();

        handleInput();

        if (collisionManager.isAlive()) {
            playerAnimated.move();
            enemy.update();
            asteroid.update();
            shotManager.update();
            collisionManager.handleCollision();
        }
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            if (game.camera.getInputInGameWorld().x >= playerAnimated.getX())
                playerAnimated.moveRight();
            else playerAnimated.moveLeft();
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
        Gdx.input.setInputProcessor(null);

    }

}