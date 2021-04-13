package lab7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private Player[] players;
    private int n;
    private int tokensNumber;
    private int playersNumber;

    public void play() {
        board = new Board(tokensNumber, n, playersNumber);
        players = new Player[playersNumber];
        for (int i = 0; i < playersNumber; ++i) {
            players[i] = new Player(board);
        }
        List<Thread> threads = new ArrayList<>(playersNumber);
        for (int i = 0; i < playersNumber; ++i) {
            threads.add(new Thread(players[i], Integer.toString(i)));
            threads.get(i).start();
        }
        for (int i = 0; i < playersNumber; ++i) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
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

    public void printResult() {
        System.out.println("Result:");
        for (var player : players) {
            System.out.println(player);
        }
    }

    public void printWinners() {
        System.out.println("Winners:");
        for (var winner : getWinners()) {
            System.out.println(winner.getName());
        }
    }

    private Set<Player> getWinners() {
        int maxScore = 0;
        Set<Player> winners = new HashSet<>();
        for (var player : players) {
            int score = new Score(player.getTokens()).calculate();
            if (score > maxScore) {
                maxScore = score;
                winners.clear();
                winners.add(player);
            } else if (score == maxScore) {
                winners.add(player);
            }
        }
        return winners;
    }
}
