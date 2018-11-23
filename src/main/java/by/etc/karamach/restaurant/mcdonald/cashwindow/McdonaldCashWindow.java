package by.etc.karamach.restaurant.mcdonald.cashwindow;

import by.etc.karamach.restaurant.mcdonald.cashwindow.states.State;
import by.etc.karamach.restaurant.mcdonald.cashwindow.states.StateName;
import by.etc.karamach.restaurant.mcdonald.cashwindow.states.concretestate.DeliveryState;
import by.etc.karamach.restaurant.mcdonald.cashwindow.states.concretestate.ProcessingState;
import by.etc.karamach.restaurant.mcdonald.cashwindow.states.concretestate.RestState;
import by.etc.karamach.restaurant.mcdonald.customer.Customer;
import by.etc.karamach.restaurant.mcdonald.customer.RegisteredVisitor;
import by.etc.karamach.restaurant.mcdonald.customer.RestaurantVisitor;

import java.util.Objects;
import java.util.StringJoiner;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", getClass().getSimpleName() + "[", "]")
                .add("restState=" + restState)
                .add("processingState=" + processingState)
                .add("deliveryState=" + deliveryState)
                .add("currentState=" + currentState.getClass().getSimpleName())
                .add("name='" + name + "'")
                .add("lockerAllVisitors=" + lockerAllVisitors)
                .add("lockerForDelivery=" + lockerForDelivery)
                .add("lockCondition=" + lockCondition)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        McdonaldCashWindow cashWindow = (McdonaldCashWindow) obj;
        return Objects.equals(restState, cashWindow.restState) &&
                Objects.equals(processingState, cashWindow.processingState) &&
                Objects.equals(deliveryState, cashWindow.deliveryState) &&
                Objects.equals(currentState, cashWindow.currentState) &&
                Objects.equals(name, cashWindow.name) &&
                Objects.equals(lockerAllVisitors, cashWindow.lockerAllVisitors) &&
                Objects.equals(lockerForDelivery, cashWindow.lockerForDelivery) &&
                Objects.equals(lockCondition, cashWindow.lockCondition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restState, processingState, deliveryState, currentState, name, lockerAllVisitors, lockerForDelivery, lockCondition);
    }
}
