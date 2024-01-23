package br.com.leandro.pong.screen.menu;

import com.badlogic.gdx.Gdx;

public enum MenuOption {
    ONE_PLAYER("1 Jogador", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2),
    TWO_PLAYER("2 Jogador", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2 - 20);

    float x;
    float y;
    String description;

    MenuOption(String description, float x, float y) {
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
