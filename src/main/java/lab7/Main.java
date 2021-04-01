package lab7;

public class Main {
    public static void main(String[] args) {
        var game = new Game();
        game.setN(10);
        game.setPlayersNumber(5);
        game.setTokensNumber(20);
        game.play();
        System.out.println(game);
    }
}
