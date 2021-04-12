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
            if (tokens.isEmpty()) {
                var first = board.takeFirstToken();
                if (first.isPresent()) {
                    tokens.add(first.get());
                }
            } else {
                var randomPosition = random.nextInt(tokens.size());
                var token = board.takeTokenWithFirst(tokens.get(randomPosition).getSecond());
                if (token.isPresent()) {
                    tokens.add(token.get());
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    @Override
    public String toString() {
        return "Player [name=" + name + ", tokens=" + tokens + "]";
    }
}
