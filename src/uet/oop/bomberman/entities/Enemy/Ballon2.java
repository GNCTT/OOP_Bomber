package uet.oop.bomberman.entities.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Ballon2 extends Enemy {

    private int speed;
    private int index;
    private int direction;

    public Ballon2(int x, int y, Image img) {
        super(x, y, img);
        index = 0;
    }
    public Ballon2(int x, int y, Image img, int animate) {
        super(x, y, img, animate);
        speed = 2;
        index = 0;
    }

    public Ballon2(int x, int y, Image img, int animate, int speed) {
        super(x, y, img, animate);
        this.speed = speed;
    }

    @Override
    public void update() {
        animate();

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
                img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, animate, 20).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.balloom_right2, Sprite.balloom_right3, animate, 20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, animate, 20).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.balloom_left2, Sprite.balloom_left3, animate, 20).getFxImage();
                break;
            default:
                img = Sprite.balloom_dead.getFxImage();
                break;
        }

    }

    @Override
    public boolean collide(Entity a) {
        if (a instanceof Bomber) {
            return true;
        }
        return false;
    }

}

