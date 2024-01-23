package br.com.leandro.pong.screen.game;

import br.com.leandro.pong.model.Ball;
import br.com.leandro.pong.model.GameState;
import br.com.leandro.pong.model.Paddle;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class MultiplayerGameScreen implements Screen {
    private Game game;
    private SpriteBatch spriteBatch;
    private Paddle playerOne;
    private Paddle playerTwo;
    private Ball ball;
    private ShapeRenderer shapeRenderer;
    private BitmapFont bitmapFont;

    public MultiplayerGameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        this.shapeRenderer = new ShapeRenderer();
        this.playerOne = new Paddle(0, Gdx.graphics.getHeight() / 2 - 50, 10, 100, 5, Color.WHITE, !GameState.getInstance().getMatchRoom().getPlayerOne().equals(GameState.getInstance().getYourPlayer()), true, GameState.getInstance().getMatchRoom().getPlayerOne(), GameState.getInstance().getMatchRoom().getPlayerTwo());
        this.playerTwo = new Paddle(Gdx.graphics.getWidth() - 10, Gdx.graphics.getHeight() / 2 - 50, 10, 100, 5, Color.WHITE, !GameState.getInstance().getMatchRoom().getPlayerTwo().equals(GameState.getInstance().getYourPlayer()), true, GameState.getInstance().getMatchRoom().getPlayerTwo(), GameState.getInstance().getMatchRoom().getPlayerOne());
        this.ball = new Ball(10, Color.RED, GameState.getInstance().getMatchRoom().getPlayerOne().equals(GameState.getInstance().getYourPlayer()), playerOne, playerTwo);
        this.spriteBatch = new SpriteBatch();
        this.bitmapFont = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //TODO IMPLEMENTAR SAIDA DO GAME

        switch (GameState.getInstance().getStateOption()) {
            case PAUSED:
                //TODO IMPLEMENTAR A PAUSA
                break;
            case RUNNING:
                renderRunning();
                break;
        }
    }

    private void renderRunning() {
        ball.update();
        playerOne.update(ball);
        playerTwo.update(ball);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        playerOne.render(shapeRenderer);
        playerTwo.render(shapeRenderer);
        ball.render(shapeRenderer);
        shapeRenderer.end();

        spriteBatch.begin();
        bitmapFont.draw(spriteBatch, "Placar: " + playerOne.getScore(), 20, Gdx.graphics.getHeight() - 20);
        bitmapFont.draw(spriteBatch, "Placar: " + playerTwo.getScore(), Gdx.graphics.getWidth() - 80, Gdx.graphics.getHeight() - 20);
        spriteBatch.end();
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
       dispose();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        spriteBatch.dispose();
    }

}
