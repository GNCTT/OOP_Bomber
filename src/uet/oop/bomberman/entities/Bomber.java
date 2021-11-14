package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private int animate;
    private int direction;

    public Bomber(int x, int y, Sprite sprite) {
        super( x, y, sprite);
        animate = 0;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (direction == 0) {
            sprite = Sprite.movingSprite(Sprite.player_up_2, Sprite.player_up_1, Sprite.player_up, animate, 60);
        }
        if (direction == 1) sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, Sprite.player_right, animate, 60);
        if (direction == 2) sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, Sprite.player_down, animate, 60);
        if (direction == 3) sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, Sprite.player_left, animate, 60);
        gc.drawImage(sprite.getFxImage(), x, y);
    }

    @Override
    public void update() {
        if (animate < 7500) {
            animate ++;
        }
        else {
            animate = 0;
        }
    }



    public void move(double px, double py) {

        x += px;
        y += py;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public Sprite getSprite() {
        return super.getSprite();
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
