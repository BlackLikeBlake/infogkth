package com.infogk.game;

import com.badlogic.gdx.utils.TimeUtils;


/**
 * Created by Neku on 08.01.2017.
 * You get one Upgrade after each chest
 * The number of upgrades will be saved in Score
 */
    public class Upgrade {

    Score score;

    float time;

    public Upgrade(Score sco){
    score = sco;
    time = TimeUtils.nanoTime();
    score.UpgradesPlus();

    }


    public int getBonus() {

        if (TimeUtils.nanoTime() - time > 1000000000){
            time = TimeUtils.nanoTime();
            return 1;
        }
        else {return 0;}
    }
}
