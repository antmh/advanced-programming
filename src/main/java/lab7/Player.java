package lab7;

import java.util.ArrayList;
import java.util.List;

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
        try {
            OUTER_LOOP: while (!board.isOver()) {
                if (!tokens.isEmpty()) {
                    for (var token : tokens) {
                        var taken = board.takeTokenWithFirst(token.getSecond());
                        if (taken.isPresent()) {
                            tokens.add(taken.get());
                            continue OUTER_LOOP;
                        }
                    }
                }
                var first = board.takeFirstToken();
                if (first.isPresent()) {
                    tokens.add(first.get());
                    continue;
                }
            }
        } catch (InterruptedException e) {
            System.err.println(e);
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
