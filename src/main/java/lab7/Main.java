package lab7;

public class Main {
    public static void main(String[] args) {
        var game = new Game();
        game.setN(100);
        game.setSmartPlayers(10);
        game.setRandomPlayers(10);
        game.setManualPlayer(false);
        game.setTokensNumber(1000);
        game.play();
        game.printResult();
        game.printWinners();
    }
}
