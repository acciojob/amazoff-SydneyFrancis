package com.driver.Service;

import com.driver.Model.DeliveryPartner;
import com.driver.Model.Order;
import com.driver.Repository.DeliveryPartnerRepository;
import com.driver.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {

    HashMap<Order,DeliveryPartner> pair = new HashMap<>();


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DeliveryPartnerRepository deliveryPartnerRepository;





//    addorder
    public void addOrders(Order order){
        orderRepository.addOrder(order);
    }

//    addPartner
    public void addPartner(String ID){
        deliveryPartnerRepository.addPartner(ID);
    }

    public void setPair(String orderid, String deliveryid){
        HashMap<String,Order> orders = orderRepository.getOrders();
        HashMap<String,DeliveryPartner> partners = deliveryPartnerRepository.getPartners();
        Order order = orders.get(orderid);
        DeliveryPartner deliveryPartner = partners.get(deliveryid);
        Integer n = deliveryPartner.getNumberOfOrders();
        n = n + 1;
        deliveryPartner.setNumberOfOrders(n);
        pair.put(order,deliveryPartner);
    }

    public Order getOrderById(String id){
        return orderRepository.getOrderById(id);
    }

    public DeliveryPartner getPartnerById(String id){
        return deliveryPartnerRepository.getPartners().get(id);
    }

    public int getOrdersCountByPartner(String id){
        for(Order order : pair.keySet()){
            if(pair.get(order).equals(id)){
                return pair.get(order).getNumberOfOrders();
            }
        }
        return 0;
    }


    public List<String> getOrdersByPartnerId(String id){
        List<String> O = new ArrayList<>();

        for(Order order : pair.keySet()){
            if(pair.get(order).equals(id)){
                O.add(pair.get(order).getId());
            }
        }
        return O;
    }


    public List<String> getAllOrders(){
        HashMap<String,Order> orders = orderRepository.getOrders();
        List<String> allorders = new ArrayList<>(orders.keySet());
        return  allorders;
    }


    public int getCountOfUnassignedOrders(){
        HashMap<String,Order> orders = orderRepository.getOrders();
        int ans = orders.size() - pair.size();
        return ans;
    }


    public int getOrdersLeftAfterGivenTimeByPartnerId(String deliveryTime , String partnerId){
        int time = 0, count = 0;
        String s1 = deliveryTime.substring(0,2);
        time = Integer.parseInt(s1)*60;
        String s2 = deliveryTime.substring(3,5);
        time = time + Integer.parseInt(s2);

        for(Order order : pair.keySet()){
            if(pair.get(order).getId().equals(partnerId)){
                if(order.getDeliveryTime()>time){
                    count++;
                }
            }
        }
        return count;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        int max = 0;
        for(Order order : pair.keySet()){
            if(pair.get(order).getId().equals(partnerId)){
                max = Math.max(order.getDeliveryTime(),max);
            }
        }
        int min = max % 60;
        int hr = max / 60;
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(hr)+":"+String.valueOf(min));
        return  sb.toString();
    }

    public void deletepartner(String partnerID){
        for(Order order : pair.keySet()){
            if(pair.get(order).equals(partnerID)){
                pair.remove(order);
            }
        }
        deliveryPartnerRepository.deletePartnerById(partnerID);
    }

    public void deleteOrder(String orderID){
        for(Order order : pair.keySet()){
            if(order.getId().equals(orderID)){
                pair.remove(order);
            }
        }
        orderRepository.deleteOrder(orderID);
    }

}
