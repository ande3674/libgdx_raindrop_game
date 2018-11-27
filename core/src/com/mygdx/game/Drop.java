package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Drop extends Game {

    SpriteBatch batch;
    BitmapFont font;
    BitmapFont font2;
    Skin mySkin;

    public void create() {
        batch = new SpriteBatch();
        // Use LibGDX's default Arial font.
        font = new BitmapFont();
        // Or try the Skin font...
        mySkin = new Skin(Gdx.files.internal("skin/freezing-ui.json"));
        font2 = mySkin.getFont("font");
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}

