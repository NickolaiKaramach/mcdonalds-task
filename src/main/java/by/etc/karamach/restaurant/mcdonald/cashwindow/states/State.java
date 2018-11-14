package by.etc.karamach.restaurant.mcdonald.cashwindow.states;

import by.etc.karamach.restaurant.mcdonald.customer.RegisteredVisitor;
import by.etc.karamach.restaurant.mcdonald.customer.RestaurantVisitor;

public interface State {
    void handleCustomer(RestaurantVisitor visitor);

    void handleCustomer(RegisteredVisitor visitor);
}
