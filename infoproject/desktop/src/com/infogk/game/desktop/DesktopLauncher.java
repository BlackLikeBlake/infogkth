package com.infogk.game.desktop;

		import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
		import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

		import com.infogk.game.LittleRed;



public class DesktopLauncher {
	public static void main (String[] arg)  {


		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 1024;
		new LwjglApplication(new LittleRed(), config);
	}
}
