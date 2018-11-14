package by.etc.karamach.restaurant.mcdonald.cashwindow;

import by.etc.karamach.restaurant.mcdonald.cashwindow.states.RestState;
import by.etc.karamach.restaurant.mcdonald.cashwindow.states.State;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class McdonaldCashWindow {
    private State currentState;
    private ReentrantLock locker;
    private AtomicInteger queLength = new AtomicInteger();

    public AtomicInteger getQueLength() {
        return queLength;
    }

    public McdonaldCashWindow(ReentrantLock locker) {
        this.locker = locker;
        currentState = new RestState(this);
    }

    public ReentrantLock getLocker() {
        return locker;
    }
}
