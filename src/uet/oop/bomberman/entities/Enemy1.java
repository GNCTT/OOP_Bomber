package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Enemy1 extends Entity{
    private int animate;
    private int direction;

    public Enemy1(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animate = 0;
        direction = 0;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (direction > 10) {
            sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 60);
        } else {
            sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 60);
        }

        gc.drawImage(sprite.getFxImage(), x, y);
    }

    @Override
    public void update() {
        calDirection();
        if (animate < 7500) {
            animate ++;
        } else {
            animate = 0;
        }
        if (direction > 100) {
            x--;
        } else {
            x++;
        }


    }

    @Override
    public void move(double px, double py) {

    }


    public void calDirection () {
        if (direction < 200) {
            direction ++;
        } else {
            direction = 0;
        }
    }
}
