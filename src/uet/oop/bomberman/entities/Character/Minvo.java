package uet.oop.bomberman.entities.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;


public class Minvo extends Entity {
    private int animate = 0;
    private int maxAnimate = 7500;
    private int direction;
    private int speed;
    private int index = 0;

    public Minvo(int x, int y, Image img) {
        super(x, y, img);
        speed = 1;
    }

    @Override
    public void update() {
        if (animate < maxAnimate) {
            animate ++;
        } else {
            animate = 0;
        }
        move();
    }


    public void move() {
        int dx = 0, dy = 0;
        if (index < 200) {
            index ++;
        } else {
            index = 0;
        }
        if (index < 50 && index >= 0) {
            dy = -1 * speed;
            direction = 0;
        }
        if (index > 50 && index < 100) {
            dx = 1 * speed;
            direction = 1;
        }
        if (index > 100 && index < 150) {
            dx = -1 * speed;
            direction = 2;
        }
        if (index > 150) {
            dy = 1 * speed;
            direction = 3;
        }

        x += dx;
        y += dy;

        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, animate, 20).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.minvo_right2, Sprite.minvo_right3, animate, 20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, animate, 20).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.minvo_left2, Sprite.minvo_left3, animate, 20).getFxImage();
                break;
            default:
                img = Sprite.minvo_dead.getFxImage();
                break;
        }

    }

}