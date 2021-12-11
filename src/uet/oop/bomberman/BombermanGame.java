package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sun.font.FontFamily;
import sun.font.SunFontManager;
import uet.oop.bomberman.Map.Map;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import uet.oop.bomberman.sound.Sound;

import javax.sound.sampled.*;
import java.io.File;

public class BombermanGame extends Application {

    public static int WIDTH = 20;
    public static int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;

    public static boolean up, down, right, left, space;
    public static Map map;
    public int State = 1 ;
    public int checkAlive = 1;
    public int startnew = 0;
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
            map = new Map(1);
            map.createMap();
            System.out.println(map.getHeight() + " " + HEIGHT);
            HEIGHT = map.getHeight();
            WIDTH = map.getWidth();
            // Tao Canvas
            canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
            gc = canvas.getGraphicsContext2D();

            // Tao root container
            Group root = new Group();
            root.getChildren().add(canvas);

            // Tao scene
            Scene scene = new Scene(root);

            // Them scene vao stage
            stage.setScene(scene);
            stage.show();

//        Sound.play("D:\\DEV_FILE\\OOP_Bomber\\res\\sound\\soundtrack.wav");
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long l) {

                    //vong lap cua game day
                    //input
                    scene.setOnKeyPressed(event -> {
                        switch (event.getCode()) {
                            case UP: up = true;break;
                            case DOWN: down = true;break;
                            case LEFT: left = true;break;
                            case RIGHT: right = true;break;
                            case SPACE: space = true;break;
                            case ESCAPE: State = 0;break;
                            case ENTER: State = 1;break;
                            case A: startnew = 1;break;
                        }
                    });
                    scene.setOnKeyReleased(event -> {
                        switch (event.getCode()) {
                            case UP: up = false;break;
                            case DOWN: down = false;break;
                            case LEFT: left = false;break;
                            case RIGHT: right = false;break;
                            case SPACE: space = false;break;
                        }
                    });
//                scene.setOnKeyTyped(event -> {
//                    switch (event.getCode()) {
//                        case SPACE: space = true; break;
//                    }
//                });
                    //de update nhan vat(toa do , hinh anh ...)
                    update();
                    // ve ra man hinh
                    render();
                    if (State == 0 && checkAlive == 1) {
                        pauseGame();
                    }
                    for (int i = 0; i < map.getEntities().size(); i++) {
                        if (map.getEntities().get(i) instanceof Bomber) {
                            if (((Bomber) map.getEntities().get(i)).isAlive() == false) {
                                checkAlive = 0;
                            }
                        }
                    }
                    if (checkAlive == 0) {
                        Game_Over();
                    }
                }
            };

            timer.start();
            createMap();
    }

    public void pauseGame() {
        gc.clearRect(0,0,Sprite.SCALED_SIZE*WIDTH,Sprite.SCALED_SIZE*HEIGHT);
        gc.drawImage(new Image("textures/1686792-pause_menu.jpg"),0,0, Sprite.SCALED_SIZE*WIDTH,Sprite.SCALED_SIZE*HEIGHT);
        gc.setFont(new Font("Family",25));
        gc.fillText("nhấn enter để chơi tiếp ", 300, 400);
        gc.setFill(Color.GREEN);
    };
    public void Game_Over() {
        gc.clearRect(0,0,Sprite.SCALED_SIZE*WIDTH,Sprite.SCALED_SIZE*HEIGHT);
        gc.drawImage(new Image("textures/GameOver.jpg"),0,0, Sprite.SCALED_SIZE*WIDTH,Sprite.SCALED_SIZE*HEIGHT);
        gc.setFont(new Font("Family",25));
        gc.fillText("nhấn A để chơi lai ", 400, 400);
        gc.setFill(Color.GREEN);
    }

    public void createMap() {
        WIDTH = map.getWidth();
        HEIGHT = map.getHeight();
    }



    public void update() {
        map.update();
    }

    public void render() {

        map.render(gc, canvas);
    }
}
