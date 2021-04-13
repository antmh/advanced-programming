package lab7.players;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;

import lab7.Board;
import lab7.Token;

public abstract class Player implements Runnable {
    protected String name;
    protected List<Token> tokens;
    protected Board board;

    public Player(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("board cannot be null");
        }
        this.board = board;
        name = Faker.instance().name().fullName();
        tokens = new ArrayList<>();
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
