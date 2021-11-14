package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private int animate;

    public Bomber(int x, int y, Sprite sprite) {
        super( x, y, sprite);
        animate = 0;
    }

    @Override
    public void render(GraphicsContext gc) {
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
    }
}
