package uet.oop.bomberman.entities.Character;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private int animate = 0;
    private int maxAnimate = 7500;
    private int direction;
    private int speed;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        speed = 2;
    }

    @Override
    public void update() {
        if (animate < maxAnimate) {
            animate ++;
        } else {
            animate = 0;
        }
        input();

    }

    private void input() {
        int dx = 0, dy = 0;
        if (BombermanGame.up) {
            dy -= 1 * speed;
        }
        if (BombermanGame.down) {
            dy = 1 * speed;
        }
        if (BombermanGame.left) {
            dx = -1 * speed;
        }
        if (BombermanGame.right) {
            dx = 1* speed;
        }
        move(dx, dy);

//        if (!BombermanGame.up && !BombermanGame.down && !BombermanGame.right && !BombermanGame.left) {
//            move(0, 0);
//        }
    }

    public void move(int dx, int dy) {
        direction = -1;
        if (dx == 0 && dy < 0) {
            direction = 0;
        }
        if (dx== 0 && dy > 0) {
            direction = 2;
        }
        if (dx > 0 && dy == 0) {
            direction = 1;
        }
        if (dx < 0 && dy == 0) {
            direction = 3;
        }
        if (dx == 0 && dy == 0) {
            direction = -1;
        }

        x += dx;
        y += dy;

        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 20).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 20).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 20).getFxImage();
                break;
            default:
                img = Sprite.player_down.getFxImage();
                break;
        }

    }

    private void canMove() {

    }

}
