package com.driver;

import com.driver.DeliveryPartner;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class DeliveryPartnerRepository {
    HashMap<String,DeliveryPartner> partners = new HashMap<>();

    public HashMap<String, DeliveryPartner> getPartners() {
        return partners;
    }

    public void setPartners(HashMap<String, DeliveryPartner> partners) {
        this.partners = partners;
    }

    //    start
    public void addPartner(String ID){
        partners.put(ID,new DeliveryPartner(ID));
    }

    public void deletePartnerById(String partnerID){
        partners.remove(partnerID);
    }
}
