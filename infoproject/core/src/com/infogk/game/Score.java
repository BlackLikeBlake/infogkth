package com.infogk.game;

import com.infogk.game.UI.GameFrame;

/**
 * Created by Neku on 06.01.2017
 * manages updating the score and and number of upgrades
 */
public class Score {

    int score;
    GameFrame frame;
    int ChestLimit;
    int Upgradenr;

    /**
     * Constructs a Score class object with score = 0,Chest Limit 50 and nr of Upgrades 0.
     * @param fram is the frame the score counts for
     */
    public Score(GameFrame fram) {
        score = 0;
        frame = fram;
        ChestLimit = 50;
        Upgradenr = 0;
    }

    /**
     * Adds 1 to the total score and spawns an point on the frame
     */
    public void plus(){
        score++;
        System.out.println(this.getScore());
        frame.SpawnRp();

    }

    /**
     * @param a adds a to the score
     */
    public void add(int a){
        if (a >= 1) {
            score = score + a;
            frame.SpawnRp();
        }
    }

    /**
     * getter for ChestLimit
     */
    public int getChestLimit(){
        return ChestLimit;

    }

    /**
     * Sets next ChestLimit as ChestLimit * 2
     */
    public void nextChestLimit(){
        if ( score > ChestLimit) {
            ChestLimit = ChestLimit * 2;
        }
    }

    /**
     * score -1
     */
    public void minus(){
        score --;

    }

    /**
     * adds 1 to nr of Upgrades
     */
    public void UpgradesPlus(){
        Upgradenr ++;
    }

    /**
     *a getter for nr of Upgrades
     */
    public int getUpgradenr() {
        return Upgradenr;
    }

    /**
     * a getter for nr of Upgrades as String
     * @return "Upgrades: "nr of Upgrades
     */
    public String getUpgradenrS(){
        return "Upgrades: " + Upgradenr;
    }

    public int getScore() {
        return score;
    }

    /**
     *a getter for scoreas String
     * @return "Score: "score;
     */
    public String getScoreS(){

        return "Score: "+score;
    }
}
