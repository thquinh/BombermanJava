package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.GamePlay;
import uet.oop.bomberman.entities.staticEntity.Brick;
import uet.oop.bomberman.entities.staticEntity.Wall;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.GamePlay.score;

public abstract class AnimatedEntity extends Entity {
    public boolean isMoving = false;
    protected boolean alive = true;
    protected boolean caughtFlame = false;
    protected int animation = 0;

    protected int bomber_speed = 1;
    protected Point distance = new Point(0, 0);

    public AnimatedEntity(Point coordinate, Image img) {
        super(coordinate, img);
    }

    public void move(int x, int y) {
        canvas_coordinate.setX(canvas_coordinate.getX() + x);
        canvas_coordinate.setY(canvas_coordinate.getY() + y);
        coordinate = canvas_coordinate.toCoordinate();
        if (distance.getY() < 0) {
            direction = Direction.up;
        }
        if (distance.getY() > 0) {
            direction = Direction.down;
        }
        if (distance.getX() < 0) {
            direction = Direction.left;
        }
        if (distance.getX() > 0) {
            direction = Direction.right;
        }
    }

    public void animation(Sprite status1, Sprite status2) {
        img = Sprite.movingSprite(status1, status2, animation, 16).getFxImage();
    }

    public void animation(Sprite status1, Sprite status2, Sprite status3) {
        img = Sprite.movingSprite(status2, status1, status3, animation, 16).getFxImage();
    }

    public void displayAnimation(Sprite up1, Sprite up2,
                                Sprite down1, Sprite down2,
                                Sprite left1, Sprite left2,
                                Sprite right1, Sprite right2) {
        switch (direction) {
            case up:
                if (isMoving) {
                    animation(up1, up2);
                }
                break;
            case down:
                if (isMoving) {
                    animation(down1, down2);
                }
                break;
            case left:
                if (isMoving) {
                    animation(left1, left2);
                }
                break;
            case right:
                if (isMoving) {
                    animation(right1, right2);
                }
                break;
            default:
        }
    }

    public void handleAnimation(Sprite left1, Sprite left2, Sprite left3,
                                Sprite right1, Sprite right2, Sprite right3) {
        switch (direction) {
            case left:
                if (isMoving) {
                    animation(left1, left2, left3);
                }
                break;
            case right:
                if (isMoving) {
                    animation(right1, right2, right3);
                }
                break;
            case up:
            case down:
                if (isMoving) {
                    animation(right1, right2);
                }
                break;
            default:
        }
    }

    public abstract boolean canMove(int x, int y);

    public abstract void die();

    public abstract void handleDieAnimation();

    public boolean isAlive() {
        return alive;
    }

    public boolean isCaughtFlame() {
        return caughtFlame;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Point getDistance() {
        return distance;
    }

    public abstract void handleMove();

    public abstract void handleCollision();
}
