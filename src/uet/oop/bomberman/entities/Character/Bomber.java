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
        move();

    }

    public void move() {

        if (BombermanGame.up) {
            y -= 1 * speed;
            Sprite a = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 20);
            img = a.getFxImage();
        }
        if (BombermanGame.down) {
            y += 1 * speed;
            Sprite a = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 20);
            img = a.getFxImage();
        }
        if (BombermanGame.left) {
            x-= 1 * speed;
            Sprite a = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 20);
            img = a.getFxImage();
        }
        if (BombermanGame.right) {
            x+= 1* speed;
            Sprite a = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20);
            img = a.getFxImage();
        }

    }
}
