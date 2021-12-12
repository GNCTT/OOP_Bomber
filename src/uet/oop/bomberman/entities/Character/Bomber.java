package uet.oop.bomberman.entities.Character;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Items.Bomb;
import uet.oop.bomberman.entities.Items.SpeedItem;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

public class Bomber extends Entity {
    private int animate = 0;
    private int maxAnimate = 7500;
    private int direction;
    private int speed;
    private boolean moving;
    private boolean alive;
    private int timeBomb = 0;
    private int timePower;
    private boolean checkSpeed;
    private int afterKill;
    private boolean flame;
    private int timeFlame;

    private int timeABomb;
    private int countBomb;
    public static int score = 0;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        speed = Sprite.SCALED_SIZE / 16;
        alive = true;
        timePower = 200;
        checkSpeed = false;
        afterKill = 50;
        countBomb = 2;
        timeABomb = 0;
        score = 0;
        flame = false;
        timeFlame = 400;
    }

    @Override
    public void update() {
        if (animate < maxAnimate) {
            animate ++;
        } else {
            animate = 0;
        }
        if (timeBomb < -10) {
            timeBomb = 0;
//            countBomb = 1;
        }
        else timeBomb --;
        if (timeABomb < -7500) {
            timeABomb = 0;
            countBomb = 2;

        } else {
            timeABomb --;
        }

        input();
        if (checkSpeed && timePower >= 0) {
            speed = 1 + Sprite.SCALED_SIZE / 16;
            timePower--;
            if (timePower < 0) {
                checkSpeed = false;
                speed = Sprite.SCALED_SIZE / 16;
                timePower = 200;
            }
        }
        if (!alive) {
            killed();
        }
    }

    @Override
    public boolean collide(Entity a) {
        return false;
    }

    private void input() {
        int dx = 0, dy = 0;
        if (BombermanGame.up) {
            dy -= 1 * speed;
//            Sound.play("D:\\DEV_FILE\\OOP_Bomber\\res\\SFX\\verticalmove.wav");
            moving = true;
        }
        if (BombermanGame.down) {
            dy = 1 * speed;
            moving = true;
//            Sound.play("D:\\DEV_FILE\\OOP_Bomber\\res\\SFX\\verticalmove.wav");
        }
        if (BombermanGame.left) {
            dx = -1 * speed;
            moving = true;
//            Sound.play("D:\\DEV_FILE\\OOP_Bomber\\res\\SFX\\horizonalmove.wav");
        }
        if (BombermanGame.right) {
            dx = 1* speed;
            moving = true;
//            Sound.play("D:\\DEV_FILE\\OOP_Bomber\\res\\sound\\AA126_11.wav");
        }
        System.out.println(timeFlame);
        if (flame) {
            timeFlame--;
            if (BombermanGame.space && timeBomb < 0) {
                Sound.play("D:\\DEV_FILE\\OOP_Bomber\\res\\sound\\BOM_SET.wav");
                int xBom = (int) (x + Sprite.SCALED_SIZE / 2);
                int yBomb = (int) (y + Sprite.SCALED_SIZE / 2);

                if (!(BombermanGame.map.getBomb(xBom, yBomb) instanceof Bomb)) {
                    BombermanGame.map.addBomb2(xBom, yBomb);
                    timeBomb = 120;
                    countBomb--;
                    if (countBomb == 0 && timeABomb < 0) {
                        timeABomb = 120;
                    }
                }
                if (timeFlame < 0) {
                    flame = false;
                    timeFlame = 120;
                }

//            Entity a = new Bomber(x, y, Sprite.movingSprite(Sprite.bomb_1, Sprite.bomb_2, animate, 20).getFxImage());

            }
        }
        else {
            if (BombermanGame.space && timeBomb < 0) {
                Sound.play("D:\\DEV_FILE\\OOP_Bomber\\res\\sound\\BOM_SET.wav");
                int xBom = (int) (x + Sprite.SCALED_SIZE / 2);
                int yBomb = (int) (y + Sprite.SCALED_SIZE / 2);
                if (!(BombermanGame.map.getBomb(xBom, yBomb) instanceof Bomb)) {
                    BombermanGame.map.addBomb(xBom, yBomb);
                    timeBomb = 120;
                    countBomb--;
                    if (countBomb == 0 && timeABomb < 0) {
                        timeABomb = 120;
                    }
                }
            }
        }
        move(dx, dy);
        chooseSprite(direction);

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

        if (canMove(dx, dy)) {
            x += dx;
            y += dy;
        }
        else {
            if ((x + dx) % 32 > 20 && (direction == 0 || direction == 2)) {
                int r = 32 - ((x + dx) % 32);
                x = x + dx + r;
            }

            if ((y + dy) % 32 > 20 && (direction == 1 || direction == 3)) {
                int r = 32 - ((y + dy) % 32);
                y = y + dy + r;
            }

            if ((x + dx) % 32 < 10 && (direction == 0 || direction == 2)) {
                int r = (x + dx) % 32;
                x = x + dx - r;
            }

            if ((y + dy) % 32 < 10 && (direction == 1 || direction == 3)) {
                int r = (y + dy) % 32;
                y = y + dy - r;
            }
        }
    }

    private void chooseSprite(int _direction) {
        if (alive) {
            switch (direction) {
                case 0:
                    img = Sprite.player_up.getFxImage();
                    if (moving) {
                        img = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 20).getFxImage();
                    }
                    break;
                case 1:
                    img = Sprite.player_right.getFxImage();
                    if (moving) {
                        img = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20).getFxImage();
                    }
                    break;
                case 2:
                    img = Sprite.player_down.getFxImage();
                    if (moving) {
                        img = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 20).getFxImage();
                    }
                    break;
                case 3:
                    img = Sprite.player_left.getFxImage();
                    if (moving) {
                        img = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 20).getFxImage();
                    }
                    break;
                default:
                    img = Sprite.player_down.getFxImage();
                    break;
            }
        }

    }

    public boolean canMove(int _x, int _y) {
        int dx = x + _x;
        int dy = y + _y;
        int x1 = myround(dx);
        int x2 = myround2(dx);
        int y1 = myround(dy);
        int y2 = myround3(dy);
        Entity o9 = BombermanGame.map.getEntity(x1, x2, y1, y2);
        Entity o1 = BombermanGame.map.getObjectnotGrass(x1, y1);
        Entity o2 = BombermanGame.map.getObjectnotGrass(x1, y2);
        Entity o3 = BombermanGame.map.getObjectnotGrass(x2, y1);
        Entity o4 = BombermanGame.map.getObjectnotGrass(x2, y2);
        Entity b1 = BombermanGame.map.getEntity(x1, y1, this);
        Entity b2 = BombermanGame.map.getEntity(x1, y2, this);
        Entity b3 = BombermanGame.map.getEntity(x2, y1, this);
        Entity b4 = BombermanGame.map.getEntity(x2, y2, this);
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

        return true;
    }
    private boolean canMove() {
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
        return (int) ((x + Sprite.SCALED_SIZE - 8) / Sprite.SCALED_SIZE);
    }

    private int myround3(double x) {
        return (int) ((x + Sprite.SCALED_SIZE - 4) / Sprite.SCALED_SIZE);
    }

    public void killed() {
        if (afterKill >= 0) {
            afterKill--;
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, animate, 20).getFxImage();
            Sound.play("D:\\DEV_FILE\\OOP_Bomber\\res\\sound\\endgame3.wav");
        } else {
            remove = true;
            BombermanGame.State = 3;
        }
    }

    public void setSpeed() {
        checkSpeed = true;
    }

    public void setCountBomb(int countBomb) {
        this.countBomb = countBomb;
    }

    public void setAlive() {
        alive = false;
    }

    public void setFlame() {
        flame = true;
    }
}
