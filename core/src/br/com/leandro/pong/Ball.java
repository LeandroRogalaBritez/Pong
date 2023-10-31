package br.com.leandro.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

public class Ball extends Circle {
    private float speedX;
    private float speedY;
    private Color color;
    public Ball(float radius, Color color) {
        this.radius = radius;
        this.color = color;
        reset();
    }

    public void update() {
        x += speedX;
        y += speedY;

        if (y < 0 || y > Gdx.graphics.getHeight()) {
            speedY *= -1;
        }
        if (x < 0) {
            PongGame.getInstance().getRightPaddle().score();
            PongGame.getInstance().resetGame();
            PongGame.getInstance().getGameState().setStateOption(StateOptions.PAUSED);
            return;
        }

        if (x > Gdx.graphics.getWidth()) {
            PongGame.getInstance().getLeftPaddle().score();
            PongGame.getInstance().resetGame();
            PongGame.getInstance().getGameState().setStateOption(StateOptions.PAUSED);
            return;
        }

        if (Intersector.overlaps(this, PongGame.getInstance().getLeftPaddle())) {
            speedX *= -1;
        }
        if (Intersector.overlaps(this, PongGame.getInstance().getRightPaddle())) {
            speedX *= -1;
        }
    }

    public void reset() {
        this.x = Gdx.graphics.getWidth() / 2;
        this.y = Gdx.graphics.getHeight() / 2;
        this.speedX = (new Random().nextInt(2) == 0) ? 5 : -5;
        this.speedY = (new Random().nextInt(2) == 0) ? 5 : -5;
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, radius);
    }
}