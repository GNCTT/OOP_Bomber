package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.Input.Keyhandle;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    boolean goNorth, goSouth, goWest, goEast, placeBomb;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
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
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:    goNorth = true; break;
                case DOWN:  goSouth = true; break;
                case LEFT:  goWest  = true; break;
                case RIGHT: goEast  = true; break;
                case SHIFT: placeBomb = true; break;
            }
        });
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:    goNorth = false; break;
                case DOWN:  goSouth = false; break;
                case LEFT:  goWest  = false; break;
                case RIGHT: goEast  = false; break;
                case SHIFT: placeBomb = false; break;
            }
        });

        stage.show();
        Bomber bomberman = new Bomber(1, 1, Sprite.player_right);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                int dx = 0, dy = 0;
                int dir = -1;
                render();
                input();
                if (goNorth) {
                    dy -= 1;
                    dir = 0;
                }
                if (goSouth) {
                    dy += 1;
                    dir = 2;
                }
                if (goEast)  {
                    dx += 1;
                    dir = 1;
                }
                if (goWest)  {
                    dx -= 1;
                    dir = 3;
                }
                bomberman.setDirection(dir);
                bomberman.move(dx * 3, dy * 3);
                update();
            }
        };
        timer.start();

        createMap();

        Wall wall = new Wall(2, 2, Sprite.wall);
        Enemy1 enemy1 = new Enemy1(10, 3, Sprite.mob_dead2);
        Entity wall2 = new Wall( 1, 10, Sprite.wall);
        entities.add(bomberman);
        entities.add(wall);
        entities.add(wall2);
        entities.add(enemy1);
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall);
                }
                else {
                    object = new Grass(i, j, Sprite.grass);
                }
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void input() {

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
