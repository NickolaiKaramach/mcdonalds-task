package by.etc.karamach.restaurant.mcdonald.cashwindow.states;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;

public class Processing implements State {
    private final McdonaldCashWindow cashWindow;

    public Processing(McdonaldCashWindow cashWindow) {
        this.cashWindow = cashWindow;
    }

    public void handleCustomer() {

    }
}
