package br.com.leandro.pong.screen.multiplayer;

import br.com.leandro.pong.controller.Controller;
import br.com.leandro.pong.model.GameState;
import br.com.leandro.pong.model.StateOptions;
import br.com.leandro.pong.screen.game.MultiplayerGameScreen;
import br.com.leandro.pong.session.Session;
import br.com.leandro.pong.session.SessionUtils;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import network.cmd.CreateRoomCommand;
import network.cmd.SearchRoomCommand;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiPlayerScreen implements Screen, InputProcessor {
    private static List<String> players = new ArrayList<>();
    private Game game;
    private SpriteBatch spriteBatch;
    private BitmapFont bitmapFont;
    private MultiplayerOption[] options = new MultiplayerOption[] {MultiplayerOption.CREATE, MultiplayerOption.JOIN};
    private int optionSelected = 1;
    private MultiplayerOption selected;
    private static String roomId = "";
    private static String feedback = "";

    public MultiPlayerScreen(Game game) {
        this.game = game;
    }

    public static void setRoomId(String newRoomId) {
        roomId = newRoomId;
    }

    public static void setFeedBack(String roomIDFeedBack) {
        feedback = roomIDFeedBack;
    }

    private void connectServer() throws IOException {
        Socket cliente = new Socket("localhost", 1234);
        GameState.getInstance().setSession(new Session(cliente, null));
    }

    @Override
    public void show() {
        this.bitmapFont = new BitmapFont();
        this.spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
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
                    bitmapFont.draw(spriteBatch, "DIGITE ID DA SALA: ", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);
                    bitmapFont.draw(spriteBatch, roomId, Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2 - 20);
                    if (feedback.length() > 0) {
                        bitmapFont.draw(spriteBatch, feedback, Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2 - 40);
                    }
                    break;
                case CREATE:
                    bitmapFont.draw(spriteBatch, "SALA CRIADA: " + roomId, Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);
                    break;
            }
            if (GameState.getInstance().getMatchRoom() != null) {
                bitmapFont.draw(spriteBatch, "Adversario encontrado, conectando...", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2 - 60);
            }
        }
        bitmapFont.draw(spriteBatch, "Players Online: " + players.size(), 10, Gdx.graphics.getHeight() - 10);
        spriteBatch.end();
    }

    public void update() {
        if (GameState.getInstance().getMatchRoom() != null) {
            GameState.getInstance().setStateOption(StateOptions.RUNNING);
            game.setScreen(new MultiplayerGameScreen(game));
            return;
        }
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
            if (Controller.getInstance().enter()) {
                selected = options[optionSelected];
                if (selected == MultiplayerOption.CREATE) {
                    SessionUtils.sendToOne(GameState.getInstance().getSession().getClient(), new CreateRoomCommand());
                }
            }
        } else {
            switch (selected) {
                case JOIN:
                    if (GameState.getInstance().getMatchRoom() == null) {
                        if (Controller.getInstance().enter() && roomId.length() == 3) {
                            SessionUtils.sendToOne(GameState.getInstance().getSession().getClient(), new SearchRoomCommand(roomId));
                        }
                    }
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
       dispose();
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

    @Override
    public boolean keyTyped(char character) {
        if (character == '\b' && roomId.length() > 0) {
            roomId = roomId.substring(0, roomId.length() - 1);
            feedback = "";
        }
        if (roomId.length() < 3 && Character.isDigit(character)) {
            roomId += character;
            feedback = "";
        }
        return true;
    }

    @Override
    public boolean keyDown(int keycode) { return false; }
    @Override
    public boolean keyUp(int keycode) { return false; }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    @Override
    public boolean mouseMoved(int screenX, int screenY) { return false; }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
