package lab7;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeKeeper implements Runnable {
    private Board board;
    private long timeLimit;
    private int secondsElapsed;

    public TimeKeeper(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("board cannot be null");
        }
        this.board = board;
        timeLimit = 10000;
        secondsElapsed = 0;
    }

    @Override
    public void run() {
        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
        ScheduledFuture<?> printTask = executor.scheduleAtFixedRate(this::print, 1, 1, TimeUnit.SECONDS);
        long startTime = System.currentTimeMillis();
        try {
            synchronized (board) {
                while (!board.isOver()) {
                    long millisElapsed = System.currentTimeMillis() - startTime;
                    board.wait(Math.max(timeLimit - millisElapsed, 0));
                    millisElapsed = System.currentTimeMillis() - startTime;
                    if (millisElapsed >= timeLimit) {
                        break;
                    }
                }
            }
            printTask.cancel(false);
            executor.shutdown();
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        board.end();
    }

    private void print() {
        ++secondsElapsed;
        System.out.println(secondsElapsed + " seconds elapsed");
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(long timeLimit) {
        if (timeLimit < 0) {
            throw new IllegalArgumentException("timeLimit cannot be negative");
        }
        this.timeLimit = timeLimit;
    }
}
