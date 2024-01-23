package br.com.leandro.pong.screen.game;

import br.com.leandro.pong.model.Ball;
import br.com.leandro.pong.model.GameState;
import br.com.leandro.pong.model.Paddle;
import br.com.leandro.pong.model.StateOptions;
import br.com.leandro.pong.screen.menu.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private Game game;
    private SpriteBatch spriteBatch;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Ball ball;
    private ShapeRenderer shapeRenderer;
    private BitmapFont bitmapFont;

    public GameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        this.shapeRenderer = new ShapeRenderer();
        this.leftPaddle = new Paddle(0, Gdx.graphics.getHeight() / 2 - 50, 10, 100, 5, Color.WHITE, false);
        this.rightPaddle = new Paddle(Gdx.graphics.getWidth() - 10, Gdx.graphics.getHeight() / 2 - 50, 10, 100, 5, Color.WHITE, true);
        this.ball = new Ball(10, Color.RED, false, leftPaddle, rightPaddle);
        this.spriteBatch = new SpriteBatch();
        this.bitmapFont = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            GameState.getInstance().setStateOption(StateOptions.MENU);
            game.setScreen(new MenuScreen(game));
            return;
        }

        switch (GameState.getInstance().getStateOption()) {
            case PAUSED:
                updatePaused();
                renderPaused();
                break;
            case RUNNING:
                renderRunning();
                break;
        }
    }

    private void renderRunning() {
        ball.update();
        leftPaddle.update(ball);
        rightPaddle.update(ball);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        leftPaddle.render(shapeRenderer);
        rightPaddle.render(shapeRenderer);
        ball.render(shapeRenderer);
        shapeRenderer.end();

        spriteBatch.begin();
        bitmapFont.draw(spriteBatch, "Placar: " + leftPaddle.getScore(), 20, Gdx.graphics.getHeight() - 20);
        bitmapFont.draw(spriteBatch, "Placar: " + rightPaddle.getScore(), Gdx.graphics.getWidth() - 80, Gdx.graphics.getHeight() - 20);
        spriteBatch.end();
    }

    private void renderPaused() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        leftPaddle.render(shapeRenderer);
        rightPaddle.render(shapeRenderer);
        ball.render(shapeRenderer);
        shapeRenderer.end();

        spriteBatch.begin();
        bitmapFont.draw(spriteBatch, "Placar: " + leftPaddle.getScore(), 20, Gdx.graphics.getHeight() - 20);
        bitmapFont.draw(spriteBatch, "Placar: " + rightPaddle.getScore(), Gdx.graphics.getWidth() - 80, Gdx.graphics.getHeight() - 20);
        bitmapFont.draw(spriteBatch, "Aperte espaço para continuar", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);
        bitmapFont.draw(spriteBatch, "Aperte ESC para menu inicial", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2 + 20);
        spriteBatch.end();
    }

    private void updatePaused() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
           GameState.getInstance().setStateOption(StateOptions.RUNNING);
        }
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        spriteBatch.dispose();
    }

}
