package br.com.leandro.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class PongGame extends ApplicationAdapter {
	private static PongGame instance;
	private SpriteBatch spriteBatch;
	private Paddle leftPaddle;
	private Paddle rightPaddle;
	private Ball ball;
	private ShapeRenderer shapeRenderer;
	private Ui ui;
	private GameState gameState;

	@Override
	public void create () {
		this.shapeRenderer = new ShapeRenderer();
		this.leftPaddle = new Paddle(0, Gdx.graphics.getHeight() / 2 - 50, 10, 100, 5, Color.WHITE, false);
		this.rightPaddle = new Paddle(Gdx.graphics.getWidth() - 10, Gdx.graphics.getHeight() / 2 - 50, 10, 100, 5, Color.WHITE, true);
		this.ball = new Ball(10, Color.RED);
		this.ui = new Ui();
		this.spriteBatch = new SpriteBatch();
		this.gameState = new GameState();
		instance = this;
	}

	@Override
	public void render() {
		ScreenUtils.clear(Color.BLACK);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		switch (gameState.getStateOption()) {
			case MENU:
				renderMenu();
				break;
			case PAUSED:
				renderPaused();
				break;
			case RUNNING:
				renderRunning();
				break;
		}
	}

	private void renderRunning() {
		ball.update();
		leftPaddle.update();
		rightPaddle.update();
		ui.update();

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		leftPaddle.render(shapeRenderer);
		rightPaddle.render(shapeRenderer);
		ball.render(shapeRenderer);
		shapeRenderer.end();

		spriteBatch.begin();
		ui.render(spriteBatch);
		spriteBatch.end();
	}

	private void renderPaused() {
		ui.update();

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		leftPaddle.render(shapeRenderer);
		rightPaddle.render(shapeRenderer);
		ball.render(shapeRenderer);
		shapeRenderer.end();

		spriteBatch.begin();
		ui.render(spriteBatch);
		spriteBatch.end();
	}

	private void renderMenu() {
		ui.update();
		spriteBatch.begin();
		ui.render(spriteBatch);
		spriteBatch.end();
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
		ui.dispose();
		spriteBatch.dispose();
	}

	public static PongGame getInstance() {
		return instance;
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public Paddle getLeftPaddle() {
		return leftPaddle;
	}

	public Paddle getRightPaddle() {
		return rightPaddle;
	}

	public Ball getBall() {
		return ball;
	}

	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}

	public Ui getUi() {
		return ui;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public void resetGame() {
		ball.reset();
		leftPaddle.reset();
		rightPaddle.reset();
	}

	public void resetGameWithScore() {
		resetGame();
		leftPaddle.setScore(0);
		rightPaddle.setScore(0);
	}

}