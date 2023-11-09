package br.com.leandro.pong.model;

import br.com.leandro.pong.screen.menu.MenuOption;
import br.com.leandro.pong.session.Session;

public class GameState {
    private StateOptions stateOption;
    private MenuOption menuOption;
    private static GameState instance;
    private Session session;

    public GameState() {
        stateOption = StateOptions.MENU;
        menuOption = MenuOption.ONE_PLAYER;
    }

    public StateOptions getStateOption() {
        return stateOption;
    }

    public void setStateOption(StateOptions stateOption) {
        this.stateOption = stateOption;
    }

    public MenuOption getMenuOption() {
        return menuOption;
    }

    public void setMenuOption(MenuOption menuOption) {
        this.menuOption = menuOption;
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
