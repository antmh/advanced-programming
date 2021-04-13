package lab7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lab7.players.SmartPlayer;
import lab7.players.ManualPlayer;
import lab7.players.Player;
import lab7.players.RandomPlayer;

public class Game {
    private Board board;
    private Player[] players;
    private int n;
    private int tokensNumber;
    private int smartPlayers;
    private int randomPlayers;
    private boolean manualPlayer;

    public void play() {
        board = new Board(tokensNumber, n, getPlayersNumber());
        players = new Player[getPlayersNumber()];
        for (int i = 0; i < smartPlayers; ++i) {
            players[i] = new SmartPlayer(board);
        }
        for (int i = smartPlayers; i < smartPlayers + randomPlayers; ++i) {
            players[i] = new RandomPlayer(board);
        }
        if (manualPlayer) {
            players[getPlayersNumber() - 1] = new ManualPlayer(board);
        }
        List<Thread> threads = new ArrayList<>(getPlayersNumber());
        for (int i = 0; i < getPlayersNumber(); ++i) {
            threads.add(new Thread(players[i], Integer.toString(i)));
            threads.get(i).start();
        }
        var timeKeeperThread = new Thread(new TimeKeeper(board), "timekeeper");
        timeKeeperThread.start();
        try {
            for (int i = 0; i < getPlayersNumber(); ++i) {
                threads.get(i).join();
            }
            timeKeeperThread.join();
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

    public int getSmartPlayers() {
        return smartPlayers;
    }

    public void setSmartPlayers(int automatedPlayers) {
        this.smartPlayers = automatedPlayers;
    }

    public int getRandomPlayers() {
        return randomPlayers;
    }

    public void setRandomPlayers(int randomPlayers) {
        this.randomPlayers = randomPlayers;
    }

    public boolean isManualPlayer() {
        return manualPlayer;
    }

    public void setManualPlayer(boolean manualPlayer) {
        this.manualPlayer = manualPlayer;
    }

    public int getPlayersNumber() {
        return smartPlayers + randomPlayers + (manualPlayer ? 1 : 0);
    }

    public void printResult() {
        System.out.println("Result:");
        for (int i = 0; i < getPlayersNumber(); ++i) {
            System.out.print(players[i] + ": ");
            for (var token : board.getTakenTokens().get(i)) {
                System.out.print(token + " ");
            }
            System.out.println();
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
        for (int i = 0; i < getPlayersNumber(); ++i) {
            int score = new Score(board.getTakenTokens().get(i)).getValue();
            if (score > maxScore) {
                maxScore = score;
                winners.clear();
            }
            if (score >= maxScore) {
                winners.add(players[i]);
            }
        }
        return winners;
    }
}
