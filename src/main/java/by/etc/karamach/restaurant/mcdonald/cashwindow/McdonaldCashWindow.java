package by.etc.karamach.restaurant.mcdonald.cashwindow;

import by.etc.karamach.restaurant.mcdonald.cashwindow.states.*;
import by.etc.karamach.restaurant.mcdonald.customer.Customer;
import by.etc.karamach.restaurant.mcdonald.customer.RegisteredVisitor;
import by.etc.karamach.restaurant.mcdonald.customer.RestaurantVisitor;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class McdonaldCashWindow {
    private RestState restState;
    private ProcessingState processingState;
    private DeliveryState deliveryState;

    private State currentState;
    private String name;

    private ReentrantLock lockerAllVisitors;
    private ReentrantLock lockerForDelivery;
    private Condition lockCondition;
    private ConcurrentLinkedQueue<RestaurantVisitor> restaurantVisitors =
            new ConcurrentLinkedQueue<RestaurantVisitor>();

    public McdonaldCashWindow(String name) {
        this.lockerAllVisitors = new ReentrantLock();
        this.lockerForDelivery = new ReentrantLock();
        this.lockCondition = this.lockerAllVisitors.newCondition();

        this.name = name;

        restState = new RestState(this);
        processingState = new ProcessingState(this);
        deliveryState = new DeliveryState(this);

        currentState = restState;
    }

    public ReentrantLock getLockerForDelivery() {
        return lockerForDelivery;
    }

    public Condition getLockForAllCondition() {
        return lockCondition;
    }

    public ReentrantLock getLockerAllVisitors() {
        return lockerAllVisitors;
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

    public void addVisitor(RestaurantVisitor visitor) {
        restaurantVisitors.add(visitor);
    }

    public RestaurantVisitor peekVisitor() {
        return restaurantVisitors.peek();
    }

    public void removeVisitor() {
        restaurantVisitors.poll();
    }

    public void handleCustomer(Customer visitor) {
        if (visitor.getClass().equals(RestaurantVisitor.class)) {
            currentState.handleCustomer((RestaurantVisitor) visitor);
        } else {
            if (visitor.getClass().equals(RegisteredVisitor.class)) {
                changeState(StateName.DELIVERY);
                currentState.handleCustomer((RegisteredVisitor) visitor);
            }
        }
    }

}
