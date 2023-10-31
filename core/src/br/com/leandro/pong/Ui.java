package br.com.leandro.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ui extends BitmapFont {
    private MenuOption[] options = new MenuOption[] {MenuOption.ONE_PLAYER, MenuOption.TWO_PLAYER};
    private int optionSelected = 0;
    private static Ui instance;

    public Ui() {
        super();
        instance = this;
    }

    public void render(SpriteBatch spriteBatch) {
        switch (PongGame.getInstance().getGameState().getStateOption()) {
            case RUNNING:
                renderRunning(spriteBatch);
                break;
            case PAUSED:
                renderPaused(spriteBatch);
                break;
            case MENU:
                renderMenu(spriteBatch);
                break;
        }
    }

    private void renderMenu(SpriteBatch spriteBatch) {
        for (int x = 0; x < options.length; x++) {
            MenuOption option = options[x];
            if (x == optionSelected) {
                draw(spriteBatch, ">", option.x - 20, option.y);
            }
            draw(spriteBatch, option.description, option.x, option.y);
        }
    }

    private void renderPaused(SpriteBatch spriteBatch) {
        draw(spriteBatch, "Placar: " + PongGame.getInstance().getLeftPaddle().getScore(), 20, Gdx.graphics.getHeight() - 20);
        draw(spriteBatch, "Placar: " + PongGame.getInstance().getRightPaddle().getScore(), Gdx.graphics.getWidth() - 80, Gdx.graphics.getHeight() - 20);
        draw(spriteBatch, "Aperte espaço para continuar", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);
        draw(spriteBatch, "Aperte ESC para menu inicial", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2 + 20);
    }

    private void renderRunning(SpriteBatch spriteBatch) {
        draw(spriteBatch, "Placar: " + PongGame.getInstance().getLeftPaddle().getScore(), 20, Gdx.graphics.getHeight() - 20);
        draw(spriteBatch, "Placar: " + PongGame.getInstance().getRightPaddle().getScore(), Gdx.graphics.getWidth() - 80, Gdx.graphics.getHeight() - 20);
    }

    public void update() {
        switch (PongGame.getInstance().getGameState().getStateOption()) {
            case MENU: updateMenu(); break;
            case PAUSED: updatePaused(); break;
            case RUNNING: updateRunning(); break;
        }

    }

    private void updateRunning() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            PongGame.getInstance().getGameState().setStateOption(StateOptions.MENU);
            PongGame.getInstance().resetGameWithScore();
        }
    }

    private void updatePaused() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            PongGame.getInstance().getGameState().setStateOption(StateOptions.RUNNING);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            PongGame.getInstance().getGameState().setStateOption(StateOptions.MENU);
            PongGame.getInstance().resetGameWithScore();
        }
    }

    private void updateMenu() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            optionSelected--;
            if (optionSelected < 0) {
                optionSelected = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            optionSelected++;

            if (optionSelected == options.length) {
                optionSelected = options.length - 1;
            }
        }
        PongGame.getInstance().getGameState().setMenuOption(options[optionSelected]);
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            PongGame.getInstance().getGameState().setStateOption(StateOptions.PAUSED);
        }
    }

    public int getOptionSelected() {
        return optionSelected;
    }

    public MenuOption[] getOptions() {
        return options;
    }

    public static Ui getInstance() {
        return instance;
    }
}
