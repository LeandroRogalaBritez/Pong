package game;

import java.io.Serializable;

public class MatchRoom implements Serializable {
    private static final long serialVersionUID = 1L;
    private String playerOne;
    private String playerTwo;

    public MatchRoom() {
    }

    public MatchRoom(String playerOne, String playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public MatchRoom(String playerOne) {
        this.playerOne = playerOne;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }
}
