package by.etc.karamach.restaurant.mcdonald.customer;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public class RegisteredVisitor implements Customer, Runnable {
    private static final Logger logger = LogManager.getLogger("default");
    private McdonaldCashWindow cashWindow;
    private String name;

    public RegisteredVisitor(McdonaldCashWindow cashWindow, String visitorName) {
        this.cashWindow = cashWindow;
        this.name = visitorName;
    }

    public String getName() {
        return name;
    }

    public void run() {

        ReentrantLock locker = cashWindow.getLockerForDelivery();
        ReentrantLock lockerForAll = cashWindow.getLockerAllVisitors();

        locker.lock();
        lockerForAll.lock();
        logger.info(name + " come to " + cashWindow.getName());

        cashWindow.handleCustomer(this);
        cashWindow.getLockForAllCondition().signalAll();
        locker.unlock();
        lockerForAll.unlock();

    }
}
