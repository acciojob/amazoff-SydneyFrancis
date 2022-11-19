package com.driver.Repository;

import com.driver.Model.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class OrderRepository {
    HashMap<String,Order> orders = new HashMap<>();

    public HashMap<String, Order> getOrders() {
        return orders;
    }

    public void setOrders(HashMap<String, Order> orders) {
        this.orders = orders;
    }


//    start
    public void addOrder(Order order){
        String ID = order.getId();
        orders.put(ID,order);
    }



    public Order getOrderById(String id){
        return orders.get(id);
    }

    public void deleteOrder(String orderID){
        orders.remove(orderID);
    }
}