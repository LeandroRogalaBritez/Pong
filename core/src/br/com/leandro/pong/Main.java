package br.com.leandro.pong;

import br.com.leandro.pong.screen.menu.MenuScreen;
import com.badlogic.gdx.Game;

public class Main extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
