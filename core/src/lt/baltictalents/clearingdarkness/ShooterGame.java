package lt.baltictalents.clearingdarkness;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
        this.setScreen(new MenuScreen(this));
        Music music = Gdx.audio.newMusic(Gdx.files.internal("background-music.mp3"));
        music.setVolume(.25f);
        music.setLooping(true);
        music.play();
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(camera.combined());
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        camera.update(width,height);
        super.resize(width, height);
    }
}

