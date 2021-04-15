package lab7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lab7.scoring.Scoring;
import lab7.players.Player;

public class Game {
    private Board board;
    private List<Player> players;
    private int n;
    private int tokensNumber;
    private Scoring scoring;
    private long timeLimit;
    private TimeKeeper timeKeeper;

    public Game() {
        players = new ArrayList<>();
    }

    public void play() {
        board = new Board(tokensNumber, n, players.size());
        List<Thread> threads = new ArrayList<>(players.size());
        for (int i = 0; i < players.size(); ++i) {
            players.get(i).setBoard(board);
            threads.add(new Thread(players.get(i), Integer.toString(i)));
            threads.get(i).start();
        }
        timeKeeper = new TimeKeeper(board, timeLimit);
        var timeKeeperThread = new Thread(timeKeeper, "timekeeper");
        timeKeeperThread.start();
        try {
            for (int i = 0; i < players.size(); ++i) {
                threads.get(i).join();
            }
            timeKeeperThread.join();
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    public void printResult() {
        System.out.println("Result:");
        for (int i = 0; i < players.size(); ++i) {
            System.out.print(players.get(i) + ": ");
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        if (players == null) {
            throw new IllegalArgumentException("players cannot be null");
        }
        this.players = players;
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

    public Scoring getScoring() {
        return scoring;
    }

    public void setScoring(Scoring scoring) {
        this.scoring = scoring;
    }

    public Set<Player> getWinners() {
        int maxScore = 0;
        Set<Player> winners = new HashSet<>();
        for (int i = 0; i < players.size(); ++i) {
            int score = scoring.calculate(board.getTakenTokens().get(i));
            if (score > maxScore) {
                maxScore = score;
                winners.clear();
            }
            if (score >= maxScore) {
                winners.add(players.get(i));
            }
        }
        return winners;
    }

    public Board getBoard() {
        return board;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public long getSecondsElapsed() {
        return timeKeeper.getSecondsElapsed();
    }
}
