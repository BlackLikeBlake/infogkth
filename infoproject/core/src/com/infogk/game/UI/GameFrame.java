package com.infogk.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.infogk.game.InputController;
import com.infogk.game.LittleRed;
import com.infogk.game.Score;
import com.infogk.game.Upgrade;

import java.util.Iterator;

/**
 * Created by Neku on 8.7.2017
 *@version 0.02 early alpha pre release steam greenlight demo version
 * This Class is the main (and only) Screen of this project.
 */
public class GameFrame implements Screen {


    final LittleRed red;
    final Score score;
    private Upgrade u;
    private Array<Upgrade> upgrades;


    public OrthographicCamera camera;
    private InputController input;


    private Texture Background;
    private TextureRegion ButtonImage1;
    private TextureRegion ButtonImage2;
    private TextureRegion RP;
    public TextureRegion ChestImage;
    public Animation<TextureRegion> Drop;
    public Animation<TextureRegion> Chest;


    public Rectangle Button;
    private Rectangle Rp;
    private Rectangle ChestR;
    private Array<Rectangle> Rps;


    public float aniStateTime;
    public float aniStateTime2 = 0;
    public boolean button = true;
    private boolean chestav = false;
    public boolean chestclicked = false;

    /**
     *
     * @param gam
     */
    public GameFrame(LittleRed gam){
        this.red = gam;
        score = new Score(this);
        input = new InputController(this,score);

        Gdx.input.setInputProcessor(input);

        Background = new Texture("Hintergrund.png");
        RP = new TextureRegion (new Texture("RP.png"));
        ButtonImage1 = new TextureRegion (new Texture("Button_1.png"));
        ButtonImage2 = new TextureRegion(new Texture("Button_2.png"));
        ChestImage = new TextureRegion(new Texture("Chest_1.png"));
        Texture Tube_1 = new Texture("Tube_1.png");
        Texture Tube_2 = new Texture("Tube_2.png");
        Texture Tube_3 = new Texture("Tube_3.png");
        Texture Tube_4 = new Texture("Tube_4.png");
        Texture Tube_5 = new Texture("Tube_5.png");
        Texture Tube_6 = new Texture("Tube_6.png");

        Drop = new Animation<TextureRegion>(0.14f,new TextureRegion(Tube_1),new TextureRegion(Tube_2),new TextureRegion(Tube_2),new TextureRegion(Tube_3),new TextureRegion(Tube_3),new TextureRegion(Tube_4),new TextureRegion(Tube_5),new TextureRegion(Tube_6),new TextureRegion(Tube_1));
        Drop.setPlayMode(Animation.PlayMode.NORMAL);

        Chest = new Animation<TextureRegion>(0.33f,new TextureRegion(new Texture("Chest_1.png")),new TextureRegion(new Texture("Chest_2.png")),new TextureRegion(new Texture("Chest_3.png")));

        camera = new OrthographicCamera();
        camera.setToOrtho(false,1280,1024);

        Button = new Rectangle();
        Button.x = 89;
        Button.y = 1024-633;
        Button.width = 638 - 89;
        Button.height = 1024-633-83;

        Rps = new Array<Rectangle>();
        upgrades = new Array<Upgrade>();
        SpawnRp();

    }

    public void SpawnChest(){
        ChestR = new Rectangle();
        ChestR.x = 150;
        ChestR.y = 117;
        ChestR.width = 232;
        ChestR.height = 176;
        chestav = true;

    }

    public void SpawnRp(){

            Rp = new Rectangle();
            Rp.x = MathUtils.random(-500,1000);
            Rp.y = 800;
            Rp.width = 64;
            Rp.height = 64;
            Rps.add(Rp);
        if (Drop.isAnimationFinished(aniStateTime)) {
            Drop.setPlayMode(Animation.PlayMode.NORMAL);
            aniStateTime = 0f;
        }
    }

    public void OpenChest(){
        aniStateTime2 += Gdx.graphics.getDeltaTime();

        if(Chest.isAnimationFinished(aniStateTime2)){
            aniStateTime2 = 0;
            chestav = false;
            chestclicked = false;
            u = new Upgrade(score);
            upgrades.add(u);
        }
    }



    public TextureRegion ChangeButtonImage(boolean b){

        if(b){
            return ButtonImage1;
        }
        else{
            return ButtonImage2;
        }
    }

    public void update(){
        float deltaTime = Gdx.graphics.getDeltaTime();
        aniStateTime += deltaTime;

        Iterator<Rectangle> iter = Rps.iterator();
        while(iter.hasNext()){
            Rectangle Rp = iter.next();
            Rp.y = Rp.y - 200* Gdx.graphics.getDeltaTime();
            if (Rp.y + 200 < 0){
                iter.remove();
            }
        }
        Iterator<Upgrade> iter2 = upgrades.iterator();
        while(iter2.hasNext()){
            Upgrade u = iter2.next();
            score.add(u.getBonus());

        }

        if (score.getScore() > score.getChestLimit()) {
            SpawnChest();
            score.nextChestLimit();
        }

        if (chestav && chestclicked){
            OpenChest();
        }

    }

    public void draw(){
        camera.update();
        red.batch.setProjectionMatrix(camera.combined);

        red.batch.begin();
        red.font.draw(red.batch,"Score: "+score.getScore(),0,480);
        red.batch.draw(Background,0,0);
        red.batch.draw(ChangeButtonImage(button),Button.x,Button.y);
        red.batch.draw(Drop.getKeyFrame(aniStateTime),720,796);
        red.font.draw(red.batch,new String (score.getScoreS()),1000,900);
        red.font.draw(red.batch,new String(score.getUpgradenrS()),1000,870);
        for (Rectangle Rp : Rps) {
            red.batch.draw(RP, Rp.x, Rp.y);
        }
        if (chestav){
            red.batch.draw(Chest.getKeyFrame(aniStateTime2),ChestR.x,ChestR.y);
        }
        red.batch.end();

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.7f,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();
        draw();

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void addScore(boolean t){
        if (t){
            score.plus();
            t = false;
        }
    }

    @Override
    public void dispose() {
        Background.dispose();


    }

}

