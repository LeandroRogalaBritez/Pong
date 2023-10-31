package br.com.leandro.pong;

public class GameState {
    private StateOptions stateOption;
    private MenuOption menuOption;

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
}
