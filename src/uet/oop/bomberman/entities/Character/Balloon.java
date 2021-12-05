package uet.oop.bomberman.entities.Character;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;


public class Balloon extends Entity {
    private int animate = 0;
    private int maxAnimate = 7500;
    private int direction;
    private int speed;
    private int index = 0;

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Balloon(int x, int y, Image img) {
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
        // random ncc vl random
        int dx = 0, dy = 0;
        if (index <= 1000) {
            index ++;
        } else {
            index = 0;
        }
        if (index % 100 == 0) {
            Random rand = new Random();
            direction = rand.nextInt(4);
        }


//        if (index < 50 && index >= 0) {
//            direction = (int)(Math.random() * 4);
////            dy = -1 * speed;
////            direction = 0;
//        }
//        if (index > 50 && index < 100) {
//            direction = (int)(Math.random() * 4);
////            dx = 1 * speed;
////            direction = 1;
//        }
//        if (index > 100 && index < 150) {
//            direction = (int)(Math.random() * 4);
////            dx = -1 * speed;
////            direction = 2;
//        }
//        if (index > 150) {
//            direction = (int)(Math.random() * 4);
////            dy = 1 * speed;
////            direction = 3;
//        }
//        if (canMove(dx, dy)) {
//            x += dx;
//            y += dy;
//        }

        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, animate, 20).getFxImage();
                dy = -speed;
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.balloom_right2, Sprite.balloom_right3, animate, 20).getFxImage();
                dx = speed;
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, animate, 20).getFxImage();
                dy = speed;
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.balloom_left2, Sprite.balloom_left3, animate, 20).getFxImage();
                dx = -speed;
                break;
            default:
                img = Sprite.balloom_left1.getFxImage();
                break;
        }
        if (canMove(dx, dy)) {
            x += dx;
            y += dy;
        }

    }

    private boolean canMove(int _x, int _y) {
        int dx = x + _x;
        int dy = y + _y;
        int x1 = myround(dx);
        int x2 = myround2(dx);
        int y1 = myround(dy);
        int y2 = myround3(dy);
        Entity o1 = BombermanGame.map.getObject(x1, y1);
        Entity o2 = BombermanGame.map.getObject(x1, y2);
        Entity o3 = BombermanGame.map.getObject(x2, y1);
        Entity o4 = BombermanGame.map.getObject(x2, y2);
        if (o1 != null) {
            if (o1.collide(this)) {
                return false;
            }
        }
        if (o2 != null) {
            if (o2.collide(this)) {
                return false;
            }
        }
        if (o3 != null) {
            if (o3.collide(this)) {
                return false;
            }
        }
        if (o4 != null) {
            if (o4.collide(this)) {
                return false;
            }
        }

//        System.out.println(myround(dx) + " " + myround2(dx));
        return true;
    }

    private int myround(double x) {
//        int scale = (int)(x / Sprite.SCALED_SIZE);
//        if (x > scale * Sprite.SCALED_SIZE) {
//            return scale + 1;
//        }
//        else
//            return scale;
        return (int) (x / Sprite.SCALED_SIZE);
    }

    private int myround2(double x) {
        return (int) ((x + Sprite.SCALED_SIZE - 4) / Sprite.SCALED_SIZE);
    }

    private int myround3(double x) {
        return (int) ((x + Sprite.SCALED_SIZE - 4) / Sprite.SCALED_SIZE);
    }

    @Override
    public boolean collide(Entity a) {
        if (a instanceof Bomber) {
            return true;
        }
        return false;
    }

}
