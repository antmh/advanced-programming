package lab7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;

public class Player implements Runnable {
    private String name;
    private List<Token> tokens;
    private Board board;

    public Player(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("board cannot be null");
        }
        this.board = board;
        name = Faker.instance().name().fullName();
        tokens = new ArrayList<>();
    }

    @Override
    public void run() {
        var random = new Random();
        while (!board.isEmpty()) {
            var token = board.take(1 + random.nextInt(board.size()));
            if (token.isPresent()) {
                tokens.add(token.get());
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final List<Token> getTokens() {
        return tokens;
    }

    public final void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    @Override
    public String toString() {
        return "Player [name=" + name + ", tokens=" + tokens + "]";
    }
}
