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
    public static boolean up, down, right, left;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH * 1.6, Sprite.SCALED_SIZE * HEIGHT);
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
                    }
                });
                scene.setOnKeyReleased(event -> {
                    switch (event.getCode()) {
                        case UP:    up = false; break;
                        case DOWN:  down = false; break;
                        case LEFT:  left  = false; break;
                        case RIGHT: right  = false; break;
                    }
                });
                update();
                render();
            }
        };
        timer.start();

        createMap();

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
    }

    public void createMap() {
        Map map = new Map(2);
        map.createMap();
        String [] lineTiles = map.get_lineTiles();
        WIDTH = map.getWidth();
        HEIGHT = map.getHeight();
        System.out.println(WIDTH + " : " + HEIGHT);
        System.out.println(lineTiles[1]);
        int count = 0;

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; ++j) {
                count ++;
                Entity object;
                switch (lineTiles[i].charAt(j)) {
                    case '#':
                        object = new Wall(j, i, Sprite.wall.getFxImage());
                        break;

                    case 'x':
                        object = new Portal(j, i, Sprite.portal.getFxImage());
                        Entity object2 = new Brick(j, i, Sprite.brick.getFxImage());
                        stillObjects.add(object2);
                        break;
                    case '*':
                        object = new Brick(j, i, Sprite.brick.getFxImage());
                        break;
                    default:
                        object = new Grass(j, i, Sprite.grass.getFxImage());
                        break;
                }

                stillObjects.add(object);
            }
        }

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; ++j) {
                count ++;
                switch (lineTiles[i].charAt(j)) {
//                    case '#':
//                        object = new Wall(j, i, Sprite.wall.getFxImage());
//                        break;
//                    case '*':
//                        object = new Brick(j, i, Sprite.brick.getFxImage());
//                        break;
                    case '1':
                        Entity object2 = new Balloon(j, i, Sprite.balloom_left1.getFxImage());
                        entities.add(object2);
                        break;
                    case '2':
                        entities.add(new Oneal(j, i, Sprite.oneal_right1.getFxImage()));
                }

            }
        }


    }



    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
