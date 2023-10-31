package br.com.leandro.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Paddle extends Rectangle {
    private final float initX;
    private final float initY;
    private float speed;
    private Color color;
    private int score;
    private boolean enemy;

    public Paddle(float x, float y, float width, float height, float speed, Color color, boolean enemy) {
        this.initX = x;
        this.initY = y;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color = color;
        this.score = 0;
        this.enemy = enemy;
    }

    public void update() {
        switch (PongGame.getInstance().getGameState().getMenuOption()) {
            case ONE_PLAYER: onePlayerUpdate(); break;
            case TWO_PLAYER: twoPlayerUpdate(); break;
        }
    }

    private void twoPlayerUpdate() {
        if (enemy) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                y = y + speed;
                if (y + height > Gdx.graphics.getHeight()) {
                    y = Gdx.graphics.getHeight() - height;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                y = y - speed;
                if (y < 0) {
                    y = 0;
                }
            }
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y = y + speed;
            if (y + height > Gdx.graphics.getHeight()) {
                y = Gdx.graphics.getHeight() - height;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y = y - speed;
            if (y < 0) {
                y = 0;
            }
        }
    }

    private void onePlayerUpdate() {
        if (enemy) {
            y += (PongGame.getInstance().getBall().y - y - 3 * 0.1);
            System.out.println(y);
            if (y + height > Gdx.graphics.getHeight()) {
                y = Gdx.graphics.getHeight() - height;
            }
            if (y < 0) {
                y = 0;
            }
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y = y + speed;
            if (y + height > Gdx.graphics.getHeight()) {
                y = Gdx.graphics.getHeight() - height;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y = y - speed;
            if (y < 0) {
                y = 0;
            }
        }
    }

    public void score() {
        this.score += 1;
    }

    public int getScore() {
        return score;
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x, y, width, height);
    }

    public void reset() {
        this.x = initX;
        this.y = initY;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
