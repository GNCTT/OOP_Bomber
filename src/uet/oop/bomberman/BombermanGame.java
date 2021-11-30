package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.Map.Map;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Character.Balloon;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Character.Oneal;
import uet.oop.bomberman.entities.Items.Flame;
import uet.oop.bomberman.entities.Items.SpeedItem;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.entities.Tiles.Grass;
import uet.oop.bomberman.entities.Tiles.Portal;
import uet.oop.bomberman.entities.Tiles.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static int WIDTH = 20;
    public static int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public static boolean up, down, right, left, space;
    public static Map map;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        map = new Map(3);
        map.createMap();
        System.out.println(map.getHeight() +" " + HEIGHT);
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

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                scene.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case UP:    up = true; break;
                        case DOWN:  down = true; break;
                        case LEFT:  left  = true; break;
                        case RIGHT: right  = true; break;
                        case SPACE: space = true; break;
                    }
                });
                scene.setOnKeyReleased(event -> {
                    switch (event.getCode()) {
                        case UP:    up = false; break;
                        case DOWN:  down = false; break;
                        case LEFT:  left  = false; break;
                        case RIGHT: right  = false; break;
                        case SPACE: space = false; break;
                    }
                });
                update();
                render();

            }
        };
        timer.start();

        createMap();
    }

    public void createMap() {
        WIDTH = map.getWidth();
        HEIGHT = map.getHeight();
        stillObjects = map.getStillObjects();
        entities = map.getEntities();


    }



    public void update() {
        map.update();
    }

    public void render() {

        map.render(gc, canvas);
    }
}
