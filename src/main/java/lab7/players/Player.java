package lab7.players;

import com.github.javafaker.Faker;

import lab7.Board;

public abstract class Player implements Runnable {
    protected String name;
    protected Board board;

    public Player() {
        name = Faker.instance().name().fullName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "Player [name=" + name + "]";
    }
}
