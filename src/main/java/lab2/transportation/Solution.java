package lab2.transportation;

import java.util.ArrayList;
import lab2.transportation.model.Delivery;
import lab2.transportation.model.Destination;
import lab2.transportation.model.Source;

/**
 * @author Antonio Mihăeș
 */
public class Solution {
    private ArrayList<Delivery> deliveries = new ArrayList<Delivery>();

    public ArrayList<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(ArrayList<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Delivery delivery : deliveries) {
            result.append(delivery);
            result.append("\n");
        }

        return result.toString();
    }
    
    public void addDelivery(Source source, Destination destination, int units) {
        deliveries.add(new Delivery(source, destination, units));
    }
}
