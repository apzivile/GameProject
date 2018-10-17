package lt.baltictalents.clearingdarkness;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lt.baltictalents.clearingdarkness.screens.MenuScreen;
import lt.baltictalents.clearingdarkness.tools.Background;
import lt.baltictalents.clearingdarkness.tools.CameraManager;

public class ShooterGame extends Game{
    public static final int WIDTH = 720;
    public static final int HEIGHT = 1280;
    public SpriteBatch batch;
    public Background background;
    public CameraManager camera;


    @Override
    public void create() {

        batch = new SpriteBatch();
        camera = new CameraManager(WIDTH, HEIGHT);

        this.background = new Background();
//        this.player = new Player();
        this.setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(camera.combined());
//        camera.update(WIDTH,HEIGHT);
//        camera.getInputInGameWorld();
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        camera.update(width,height);
        super.resize(width, height);
    }
}

