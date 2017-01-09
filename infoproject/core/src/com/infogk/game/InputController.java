package com.infogk.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.infogk.game.UI.GameFrame;

/**
 * Created by Neku on 06.01.2017.
 * This Class is called whenever an input command is issued
 */
public class InputController implements InputProcessor {


    GameFrame frame;
    Score score;

    boolean ButtonHit;

    public InputController(GameFrame fram,Score sco){
        frame = fram;
        score = sco;
        ButtonHit = false;

    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    /**
     * Math vodoo
     * @param screenX y coord of the cursor
     * @param screenY x coord of the cursor
     * @param pointer pizza
     * @param button pasta
     * @return always false
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        float sx = screenX;
        float sy = screenY;
        float h = Gdx.graphics.getHeight();
        float w = Gdx.graphics.getWidth();

        if( (w/sx < 14 && w/sx >= 2)&&(h/sy <= 11 && (h/sy) > 1.632) ){
            ButtonHit = true;
            frame.button = false;

        }
        if( (w/sx < 8.5333 && w/sx > 3.35)&&(h/sy < 1.4 && (h/sy) > 1.12) ){
            frame.chestclicked = true;
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if(ButtonHit) {
            frame.addScore(true);
            ButtonHit = false;
            frame.button =true;

        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
       return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
