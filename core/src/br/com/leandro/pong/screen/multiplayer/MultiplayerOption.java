package br.com.leandro.pong.screen.multiplayer;

import com.badlogic.gdx.Gdx;

public enum MultiplayerOption {
    JOIN("Entrar em uma Sala", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2),
    CREATE("Criar uma Sala", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2 - 20);

    float x;
    float y;
    String description;

    MultiplayerOption(String description, float x, float y) {
        this.description = description;
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getDescription() {
        return description;
    }
}
