package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {

    final Drop game;
    OrthographicCamera camera;
    //private Stage stage;
    private Label outputLabel;

    public MainMenuScreen(final Drop gam){
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        //stage = new Stage(new ScreenViewport());
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        // Use the freezing Skin / font
        Skin mySkin = new Skin(Gdx.files.internal("skin/freezing-ui.json"));
        // As of now we don't use this label....
        Label label1 = new Label("This is a Label (skin) on  5 columns ",mySkin,"default");
        label1.setSize(Gdx.graphics.getWidth()/12, Gdx.graphics.getWidth() / 12);
        label1.setPosition(Gdx.graphics.getWidth()/12 * 2,Gdx.graphics.getHeight()-(Gdx.graphics.getWidth() / 12)*6);
        //stage.addActor(label1);

//        Label label4 = new Label("This is a Label (skin) with a 5 columns width but WITH wrap",mySkin,"black");
//        label4.setSize(Gdx.graphics.getWidth()/Help_Guides*5,row_height);
//        label4.setPosition(col_width*2,Gdx.graphics.getHeight()-row_height*7);
//        label4.setWrap(true);
//        stage.addActor(label4);

        // make a button....
        Button button1 = new TextButton("Play", mySkin, "default");
        button1.setSize(Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/15);
        button1.setPosition(Gdx.graphics.getWidth()-150, 150);
        // addListener.....
        button1.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Press a button");
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                outputLabel.setText("Pressed Text Button");
                return true;
            }
        });
        //label
        outputLabel = new Label("Press a Button",mySkin,"default");
        outputLabel.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/12);
        outputLabel.setPosition(0, Gdx.graphics.getHeight()/15);
        outputLabel.setAlignment(Align.center);

        game.batch.begin();
        label1.draw(game.batch, 1);
        game.font2.draw(game.batch, "Welcome to Drop!!! ", 100, 200);
        //game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
        game.font2.draw(game.batch, "Tap anywhere to begin!", 100, 150);
        outputLabel.draw(game.batch, 1);
        button1.draw(game.batch, 1);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new MyGdxGame(game));
            dispose();
        }
//        if (button1.isPressed()){
//            game.setScreen(new MyGdxGame(game));
//            dispose();
//        }

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
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
    public void dispose() {
        //game.dispose();
    }

}
