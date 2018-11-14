package by.etc.karamach.restaurant.mcdonald.cashwindow;

import by.etc.karamach.restaurant.mcdonald.cashwindow.states.*;
import by.etc.karamach.restaurant.mcdonald.customer.Customer;
import by.etc.karamach.restaurant.mcdonald.customer.RestaurantVisitor;

import java.util.concurrent.locks.ReentrantLock;

public class McdonaldCashWindow {
    private RestState restState;
    private ProcessingState processingState;
    private DeliveryState deliveryState;

    private State currentState;
    private ReentrantLock locker;
    private String name;

    public McdonaldCashWindow(ReentrantLock locker, String name) {
        this.locker = locker;
        this.name = name;

        restState = new RestState(this);
        processingState = new ProcessingState(this);
        deliveryState = new DeliveryState(this);

        currentState = restState;
    }

    public ReentrantLock getLocker() {
        return locker;
    }

    public String getName() {
        return name;
    }

    public void changeState(StateName nextState) {
        switch (nextState) {
            case REST:
                currentState = restState;
                break;
            case DELIVERY:
                currentState = deliveryState;
                break;
            case PROCESSING:
                currentState = processingState;
                break;
        }
    }

    public void handleCustomer(Customer visitor) {
        if (visitor.getClass().equals(RestaurantVisitor.class)) {
            currentState.handleCustomer((RestaurantVisitor) visitor);
        }
    }

}
