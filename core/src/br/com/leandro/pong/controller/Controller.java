package br.com.leandro.pong.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Controller {
    private static Controller instance;
    private static boolean enterPressed;

    public Controller() {
        this.instance = this;
    }

    public static Controller getInstance() {
        return instance;
    }

    public boolean enter() {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            if (!enterPressed) {
                enterPressed = true;
                return true;
            }
            return false;
        }
        enterPressed = false;
        return false;
    }
}
