package lab7.players;

import com.github.javafaker.Faker;

import lab7.Board;

public abstract class Player implements Runnable {
    protected String name;
    protected Board board;

    public Player(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("board cannot be null");
        }
        this.board = board;
        name = Faker.instance().name().fullName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player [name=" + name + "]";
    }
}
