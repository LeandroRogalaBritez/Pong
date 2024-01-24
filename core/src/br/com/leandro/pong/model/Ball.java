package br.com.leandro.pong.model;

import br.com.leandro.pong.screen.menu.MenuOption;
import br.com.leandro.pong.session.SessionUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import network.cmd.BallMovedCommand;
import network.cmd.ScoreCommand;

import java.util.Random;

public class Ball extends Circle {
    private float speedX;
    private float speedY;
    private Color color;
    private boolean multiplayer;
    private static Ball instance;
    private Paddle playerOne;
    private Paddle playerTwo;
    private Random random = new Random();
    
    public Ball(float radius, Color color, boolean multiplayer, Paddle playerOne, Paddle playerTwo) {
        this.radius = radius;
        this.color = color;
        this.multiplayer = multiplayer;
        reset();
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        instance = this;
    }

    public static Ball getInstance() {
        return instance;
    }

    public void update() {
        if (multiplayer) {
            return;
        }
        x += speedX;
        y += speedY;

        if (y < 0 || y > Gdx.graphics.getHeight()) {
            speedY *= -1;
        }
        if (x < 0) {
            playerTwo.score();
            resetGame();
            if (GameState.getInstance().getMenuOption() == MenuOption.TWO_PLAYER) {
                SessionUtils.sendToOne(GameState.getInstance().getSession().getClient(), new ScoreCommand(playerOne.getPlayer(), playerTwo.getPlayer()));
            }
            return;
        }

        if (x > Gdx.graphics.getWidth()) {
            playerOne.score();
            resetGame();
            if (GameState.getInstance().getMenuOption() == MenuOption.TWO_PLAYER) {
                SessionUtils.sendToOne(GameState.getInstance().getSession().getClient(), new ScoreCommand(playerOne.getPlayer(), playerOne.getPlayer()));
            }
            return;
        }

        if (Intersector.overlaps(this, playerOne)) {
            speedX *= -1;
        }
        if (Intersector.overlaps(this, playerTwo)) {
            speedX *= -1;
        }

        if (GameState.getInstance().getMenuOption() == MenuOption.TWO_PLAYER) {
            SessionUtils.sendToOne(GameState.getInstance().getSession().getClient(), new BallMovedCommand(x, y, speedX, speedY, playerOne.getPlayer()));
        }
    }

    public void reset() {
        this.x = Gdx.graphics.getWidth() / 2;
        this.y = Gdx.graphics.getHeight() / 2;
        this.speedX = (random.nextInt(2) == 0) ? 5 : -5;
        this.speedY = (random.nextInt(2) == 0) ? 5 : -5;
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, radius);
    }

    public void resetGame() {
        reset();
        playerOne.reset();
        playerTwo.reset();
    }

    public void updateBall(float x, float y, float speedX, float speedY) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public Paddle getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Paddle playerOne) {
        this.playerOne = playerOne;
    }

    public Paddle getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Paddle playerTwo) {
        this.playerTwo = playerTwo;
    }
}