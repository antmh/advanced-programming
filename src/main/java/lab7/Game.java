package lab7;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Game {
    private Board board;
    private Player[] players;
    private int n;
    private int tokensNumber;
    private int playersNumber;

    public void play() {
        board = new Board(tokensNumber, n);
        players = new Player[playersNumber];
        for (int i = 0; i < playersNumber; ++i) {
            players[i] = new Player(board);
        }
        ExecutorService executor = Executors.newFixedThreadPool(playersNumber);
        for (var player : players) {
            executor.execute(player);
        }
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be positive");
        }
        this.n = n;
    }

    public int getTokensNumber() {
        return tokensNumber;
    }

    public void setTokensNumber(int tokensNumber) {
        if (tokensNumber < 1) {
            throw new IllegalArgumentException("tokensNumber must be positive");
        }
        this.tokensNumber = tokensNumber;
    }

    public int getPlayersNumber() {
        return playersNumber;
    }

    public void setPlayersNumber(int playersNumber) {
        if (playersNumber < 1) {
            throw new IllegalArgumentException("playersNumber must be positive");
        }
        this.playersNumber = playersNumber;
    }

    public Board getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Game " + Arrays.toString(players);
    }
}
