package br.com.leandro.pong.screen.multiplayer;

import br.com.leandro.pong.model.GameState;
import br.com.leandro.pong.model.StateOptions;
import br.com.leandro.pong.screen.game.GameScreen;
import br.com.leandro.pong.session.Session;
import br.com.leandro.pong.session.SessionUtils;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import network.cmd.CreateRoomCommand;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiPlayerScreen implements Screen {
    private static List<String> players = new ArrayList<>();
    private Game game;
    private SpriteBatch spriteBatch;
    private BitmapFont bitmapFont;
    private MultiplayerOption[] options = new MultiplayerOption[] {MultiplayerOption.CREATE, MultiplayerOption.JOIN};
    private int optionSelected = 0;
    private MultiplayerOption selected;
    private static String roomId;

    public MultiPlayerScreen(Game game) {
        this.game = game;
    }

    public static void setRoomId(String newRoomId) {
        roomId = newRoomId;
    }

    private void connectServer() throws IOException {
        Socket cliente = new Socket("localhost", 1234);
        GameState.getInstance().setSession(new Session(cliente, "cliente"));
    }

    @Override
    public void show() {
        this.bitmapFont = new BitmapFont();
        this.spriteBatch = new SpriteBatch();
        try {
            connectServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();
        spriteBatch.begin();
        if (selected == null) {
            for (int x = 0; x < options.length; x++) {
                MultiplayerOption option = options[x];
                if (x == optionSelected) {
                    bitmapFont.draw(spriteBatch, ">", option.x - 20, option.y);
                }
                bitmapFont.draw(spriteBatch, option.description, option.x, option.y);
            }
        } else {
            switch (selected) {
                case JOIN:
                    break;
                case CREATE:
                    bitmapFont.draw(spriteBatch, "SALA CRIADA: " + roomId, Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);
                    break;
            }
        }
        bitmapFont.draw(spriteBatch, "Players Online: " + players.size(), 10, Gdx.graphics.getHeight() - 10);
        spriteBatch.end();
    }

    public void update() {
        if (selected == null) {
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                optionSelected--;
                if (optionSelected < 0) {
                    optionSelected = 0;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                optionSelected++;
                if (optionSelected == options.length) {
                    optionSelected = options.length - 1;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                selected = options[optionSelected];
                if (selected == MultiplayerOption.CREATE) {
                    SessionUtils.sendToOne(GameState.getInstance().getSession().getClient(), new CreateRoomCommand());
                }
            }
        } else {
            switch (selected) {
                case JOIN:
                    break;
                case CREATE:
                    break;
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

    }

    @Override
    public void dispose() {
        bitmapFont.dispose();
        spriteBatch.dispose();
    }

    public static void setNewPlayer(String player) {
        players.add(player);
    }

    public static void removePlayer(String player) {
        players.remove(player);
    }

}
