package uet.oop.bomberman.entities.Enemy;

import com.sun.xml.internal.ws.server.EndpointAwareTube;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Items.Explode;
import uet.oop.bomberman.entities.Items.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public abstract class Enemy extends Entity {
    protected int animate;
    protected int speed;
    protected boolean alive;
    protected final int maxStep = 50;
    protected int step;
    protected int direction;
    private final int maxAnimate = 7500;
    protected int afterKill;

    protected boolean die;
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        animate = 0;
        step = 160;
        direction = 0;
        speed = Sprite.SCALED_SIZE / 32;
        afterKill = 100;
        die = false;
    }

    public Enemy(int x, int y, Image img, int animate) {
        super(x, y, img);
        this.animate = animate;
    }


    public void animate() {
        if (animate < 7500) {
            animate ++;
        } else animate = 0;
    }

    public void setSpeed () {
        this.speed = speed * 2;
    }

    public void update() {
        animate();
        if (alive) {
            if ( step <= 0 ) {
                step = 160;
                Random rand = new Random();
                direction = rand.nextInt(4);
            }
            else {
                step --;
                move();
            }
        }
        else {
            killed();
        }
//        if ( step <= 0 ) {
//            step = 160;
//            Random rand = new Random();
//            direction = rand.nextInt(4);
//        }
//        else {
//            step --;
//            move();
//        }
    }

    private void move() {
        int dx = 0;
        int dy = 0;
        switch (direction) {
            case 0:
                dy -= speed;
                break;
            case 1:
                dx += speed;
                break;
            case 2:
                dy += speed;
                break;
            case 3:
                dx -= speed;
                break;
        }

//        if (BombermanGame.up) dy --;
//        if (BombermanGame.down) dy ++;
//        if (BombermanGame.left) dx --;
//        if (BombermanGame.right) dx ++;

        chooseSprite();

        if (canMove(dx, dy)) {
            x += dx;
            y += dy;
        }
        else {
            Random rand = new Random();
            direction = rand.nextInt(4);
            step = 96;
        }


    }

    public abstract void chooseSprite();


    public boolean canMove(int _x, int _y) {
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
        Entity b1 = BombermanGame.map.getEntity(x1, y1);
        Entity b2 = BombermanGame.map.getEntity(x1, y2);
        Entity b3 = BombermanGame.map.getEntity(x2, y1);
        Entity b4 = BombermanGame.map.getEntity(x2, y2);
        if (b1 != null) {
            if (b1.collide(this)) {
                return false;
            }
        }
        if (b2 != null) {
            if (b2.collide(this)) {
                return false;
            }
        }
        if (b3 != null) {
            if (b3.collide(this)) {
                return false;
            }
        }
        if (b4 != null) {
            if (b4.collide(this)) {
                return false;
            }
        }

        if (o4 instanceof SpeedItem) {
            speed = Sprite.SCALED_SIZE / 8;
        }

        if (o3 instanceof SpeedItem) {
            speed = Sprite.SCALED_SIZE / 8;
        }
        if (o2 instanceof SpeedItem) {
            speed = Sprite.SCALED_SIZE / 8;
        }
        if (o1 instanceof SpeedItem) {
            speed = Sprite.SCALED_SIZE / 8;
        }

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
    private boolean canMove() {
        return true;
    }

    /** Góc trái trên. */
    private int myround(double x) {
//        int scale = (int)(x / Sprite.SCALED_SIZE);
//        if (x > scale * Sprite.SCALED_SIZE) {
//            return scale + 1;
//        }
//        else
//            return scale;
        return (int) ((x + 2) / Sprite.SCALED_SIZE );
    }

    /** Góc phải trên. */
    private int myround2(double x) {
        return (int) ((x + Sprite.SCALED_SIZE - 2 ) / Sprite.SCALED_SIZE);
    }

    /** Trục bên dưới. */
    private int myround3(double x) {
        return (int) ((x + Sprite.SCALED_SIZE - 2) / Sprite.SCALED_SIZE);
    }

    public void killed() {

    }

    public void setAlive() {
        alive = false;
    }


    public boolean collide(Entity a) {
        if ( a instanceof Bomber) {
            ((Bomber) a).setAlive();
        }
        return false;
    }

}
