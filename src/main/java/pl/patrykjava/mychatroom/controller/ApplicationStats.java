package pl.patrykjava.mychatroom.controller;

public class ApplicationStats {

    private static int userCount;
    private static final Object lock = new Object();

    private ApplicationStats() {
        throw new RuntimeException("No instance of this class");
    }

    public static void incrementUserCount() {
        synchronized (lock) {
            userCount++;
        }
    }

    public static void decrementUserCount() {
        synchronized (lock) {
            userCount--;
        }
    }

    public static int getUserCount() {
        synchronized (lock) {
            return userCount;
        }
    }
}