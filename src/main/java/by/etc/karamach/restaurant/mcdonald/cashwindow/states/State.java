package by.etc.karamach.restaurant.mcdonald.cashwindow.states;

import by.etc.karamach.restaurant.mcdonald.customer.RestaurantVisitor;

public interface State {
    void handleCustomer(RestaurantVisitor visitor);
}
