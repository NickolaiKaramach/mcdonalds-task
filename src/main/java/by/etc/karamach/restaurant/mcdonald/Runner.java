package by.etc.karamach.restaurant.mcdonald;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;
import by.etc.karamach.restaurant.mcdonald.customer.RestaurantVisitor;

import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        McdonaldCashWindow cashWindow1 = new McdonaldCashWindow(lock);

        RestaurantVisitor visitor1 = new RestaurantVisitor(cashWindow1, "Customer1");
        RestaurantVisitor visitor2 = new RestaurantVisitor(cashWindow1, "Customer2");
        RestaurantVisitor visitor3 = new RestaurantVisitor(cashWindow1, "Customer3");

        Thread thread1 = new Thread(visitor1);
        Thread thread2 = new Thread(visitor2);
        Thread thread3 = new Thread(visitor3);

        thread1.start();
        thread2.start();
        thread3.start();
        

    }
}
