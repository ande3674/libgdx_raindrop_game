package com.mygdx.game.desktop;
import com.mygdx.game.*;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Drop";
		config.width = 800;
		config.height = 480;
		//MyGdxGame myGdxGame = new MyGdxGame(new Drop());
		Drop d = new Drop();
		new LwjglApplication(d, config);
		//Drop g = new Drop();
		//MyGdxGame myg = new MyGdxGame(new Drop());

	}
}
