package uet.oop.bomberman.entities.Items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Enemy.Enemy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tiles.Brick;
import uet.oop.bomberman.graphics.Sprite;

public class Explode extends Entity {

    private GraphicsContext gc;
    private int explodeTime;
    private int animate;
    private int dir;
    public Explode(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        explodeTime = 20;
        animate = 0;
    }

    public Explode(int x, int y, int dir) {
        super(x, y);
        this.dir = dir;
        animate = 0;
        explodeTime = 20;
    }

    public void animate() {
        if (animate < 7500) {
            animate ++;
        } else animate = 0;
    }

    @Override
    public void update() {
        explodeTime--;
        animate();
        switch (dir) {
            case 0:
                img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1,Sprite.explosion_vertical_top_last2, animate, 20).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1,Sprite.explosion_horizontal_left_last2, animate, 20).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1,Sprite.explosion_horizontal_right_last2, animate, 20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, animate, 20).getFxImage();
                break;
            default:
                img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, animate, 20).getFxImage();
                break;
        }
        explode(explodeTime);
    }

    @Override
    public boolean collide(Entity a) {
        if (a instanceof Brick) {
            return true;
        }
        if (a instanceof Bomber) {
            ((Bomber) a).setAlive();
        }
        if (a instanceof Enemy) {
            ((Enemy) a).killed();
        }
        if (a instanceof Bomb) {
            System.out.println("oooooooooo");
            ((Bomb) a).explode();
        }
        return false;
    }

    private void explode(int time) {
        if (time < 0) {
            remove = true;
        }
    }

}
