package by.etc.karamach.restaurant.mcdonald.cashwindow.states;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;

public class RestState implements State {

    private final McdonaldCashWindow cashWindow;

    public RestState(McdonaldCashWindow cashWindow) {
        this.cashWindow = cashWindow;
    }

    public void handleCustomer() {
        System.out.println("Handle customer on " + cashWindow.toString());
    }
}
