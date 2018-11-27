package com.mygdx.game;

//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class MyGdxGame implements Screen {
	final Drop game;

	OrthographicCamera camera;
	SpriteBatch batch;
	Texture dropImage;
	Texture bucketImage;
	Sound dropSound;
	Music rainMusic;
	Rectangle bucket;
	Array<Rectangle> raindrops;
	long lastDropTime;
	int dropsGathered;
	int dropsMissed;

	public MyGdxGame(final Drop gam) {
		this.game = gam;

		// load the images for the droplet and the bucket,
		// 64x64 pixels each
		dropImage = new Texture(Gdx.files.internal("droplet.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));

		// load the drop sound effect and the rain background "music"
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		// start the playback of the background music immediately
		rainMusic.setLooping(true);
		//rainMusic.play();

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		//batch = new SpriteBatch();

		// bucket
		bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 / 2;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;

		// raindrops
		raindrops = new Array<Rectangle>();
		spawnRaindrop();
	}

	@Override
	public void render(float delta) {
		// clear the screen with a dark blue color. The
		// arguments to glClearColor are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// update camera
		camera.update();
		// now render bucket
		// this tells the spritebatch to use the coord system specified by the camera
		game.batch.setProjectionMatrix(camera.combined);

		// SpriteBatch now renders everything described in create() method
		game.batch.begin();
		game.font2.draw(game.batch, "Drops Collected: " + dropsGathered, 0, 480);
		game.font2.draw(game.batch, "Drops Missed: " + dropsMissed, 400, 480);
		game.batch.draw(bucketImage, bucket.x, bucket.y);
		for (Rectangle raindrop : raindrops){
			game.batch.draw(dropImage, raindrop.x, raindrop.y);
		}
		game.batch.end();

		// moving the bucket
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = touchPos.x - 64 / 2;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			bucket.x += 200 * Gdx.graphics.getDeltaTime();
		}
		// keep bucket within screen tho
		if(bucket.x < 0) bucket.x = 0;
		if(bucket.x > 800 - 64) bucket.x = 800 - 64;

		// spawn raindrops
		if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();
		// make it rain
		for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if(raindrop.y + 64 < 0) {
				dropsMissed++;
				iter.remove();
			}

			// play sound if it hits the bucket
			if(raindrop.overlaps(bucket)) {
				dropsGathered++;
				dropSound.play();
				iter.remove();
			}
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		// start the playback of the background music
		// when the screen is shown
		rainMusic.play();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	@Override
	public void dispose () {
		dropImage.dispose();
		bucketImage.dispose();
		dropSound.dispose();
		rainMusic.dispose();
		//batch.dispose();
	}

	private void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800-64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}
}
