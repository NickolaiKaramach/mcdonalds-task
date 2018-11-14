package by.etc.karamach.restaurant.mcdonald.cashwindow.states;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;

public class Delivery implements State {
    private final McdonaldCashWindow cashWindow;

    public Delivery(McdonaldCashWindow cashWindow) {
        this.cashWindow = cashWindow;
    }

    public void handleCustomer() {

    }
}
