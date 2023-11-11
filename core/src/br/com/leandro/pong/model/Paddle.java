package br.com.leandro.pong.model;

import br.com.leandro.pong.session.SessionUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import network.cmd.BallMovedCommand;
import network.cmd.PaddleMovedCommand;

public class Paddle extends Rectangle {
    private final float initX;
    private final float initY;
    private float speed;
    private Color color;
    private int score;
    private boolean enemy;
    private boolean multiplayer;
    private String player;
    private String otherPlayer;

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
        this.multiplayer = false;
    }

    public Paddle(float x, float y, float width, float height, float speed, Color color, boolean enemy, boolean multiplayer, String player, String otherPlayer) {
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
        this.multiplayer = multiplayer;
        this.player = player;
        this.otherPlayer = otherPlayer;
    }

    public String getPlayer() {
        return player;
    }

    public void update(Ball ball) {
        switch (GameState.getInstance().getMenuOption()) {
            case ONE_PLAYER: onePlayerUpdate(ball); break;
            case TWO_PLAYER: twoPlayerUpdate(); break;
        }
    }

    private void twoPlayerUpdate() {
        if (enemy && multiplayer) {
            return;
        }
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

        if (multiplayer) {
            SessionUtils.sendToOne(GameState.getInstance().getSession().getClient(), new PaddleMovedCommand(y, getOtherPlayer(), getPlayer()));
        }
    }

    public String getOtherPlayer() {
        return otherPlayer;
    }

    private void onePlayerUpdate(Ball ball) {
        if (enemy) {
            y += (ball.y - y - 3 * 0.1);
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

    public void updatePosition(float y) {
        this.y = y;
    }
}
