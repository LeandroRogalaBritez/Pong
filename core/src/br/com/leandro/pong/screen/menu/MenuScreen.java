package br.com.leandro.pong.screen.menu;

import br.com.leandro.pong.controller.Controller;
import br.com.leandro.pong.model.GameState;
import br.com.leandro.pong.model.StateOptions;
import br.com.leandro.pong.screen.game.GameScreen;
import br.com.leandro.pong.screen.multiplayer.MultiPlayerScreen;
import br.com.leandro.pong.session.Session;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MenuScreen implements Screen {
    private Game game;
    private SpriteBatch spriteBatch;
    private BitmapFont bitmapFont;
    private MenuOption[] options = new MenuOption[] {MenuOption.ONE_PLAYER, MenuOption.TWO_PLAYER};
    private int optionSelected = 0;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        this.bitmapFont = new BitmapFont();
        this.spriteBatch = new SpriteBatch();
        new Controller();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();
        spriteBatch.begin();
        for (int x = 0; x < options.length; x++) {
            MenuOption option = options[x];
            if (x == optionSelected) {
                bitmapFont.draw(spriteBatch, ">", option.x - 20, option.y);
            }
            bitmapFont.draw(spriteBatch, option.description, option.x, option.y);
        }
        spriteBatch.end();
    }

    public void update() {
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
        GameState.getInstance().setMenuOption(options[optionSelected]);
        if (Controller.getInstance().enter()) {
            if (options[optionSelected] == MenuOption.TWO_PLAYER) {
                game.setScreen(new MultiPlayerScreen(game));
            }
            if (options[optionSelected] == MenuOption.ONE_PLAYER) {
                GameState.getInstance().setStateOption(StateOptions.PAUSED);
                game.setScreen(new GameScreen(game));
            }
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
        dispose();
    }

    @Override
    public void dispose() {
        bitmapFont.dispose();
        spriteBatch.dispose();
    }

}
