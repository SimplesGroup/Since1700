package since.since1700.Filter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();

        List<String> menu3 = new ArrayList<String>();
        List<String> menu4 = new ArrayList<String>();
        List<String> menu5 = new ArrayList<String>();
        List<String> menu6 = new ArrayList<String>();
        List<String> menu7 = new ArrayList<String>();
        List<String> menu8 = new ArrayList<String>();
        List<String> menu9 = new ArrayList<String>();
        List<String> menu10 = new ArrayList<String>();
        List<String> menu11 = new ArrayList<String>();
        List<String> menu12 = new ArrayList<String>();
        List<String> menu13 = new ArrayList<String>();
        List<String> menu14 = new ArrayList<String>();
        List<String> menu15 = new ArrayList<String>();
        List<String> menu16 = new ArrayList<String>();
        List<String> menu17 = new ArrayList<String>();
        List<String> menu18 = new ArrayList<String>();


        List<String> list1 = new ArrayList<String>();
        list1.add("Submenu 1");
        list1.add("Submenu 2");
        list1.add("Submenu 3");
        list1.add("Submenu 4");


        List<String> list2 = new ArrayList<String>();
        list2.add("Audi" );
        list2.add("BMW");
        list2.add("Mercedes-Benz");
        list2.add("Rolls-Royce");
        list2.add("Skoda");
        list2.add("Polaris");
        list2.add("Jeep");
        list2.add("Jaguar");


        expandableListDetail.put("Accesories", list1);
        expandableListDetail.put("Apparel", list2);
        expandableListDetail.put("Art", menu3);
        expandableListDetail.put("Auctions", menu4);
        expandableListDetail.put("Beauty", menu5);
        expandableListDetail.put("Bikes", menu6);
        expandableListDetail.put("Cars", menu7);
        expandableListDetail.put("Consumer electronics", menu8);
        expandableListDetail.put("Footwear", menu9);
        expandableListDetail.put("Fragrances", menu10);
        expandableListDetail.put("Gastronomy", menu11);
        expandableListDetail.put("Home Decor", menu12);
        expandableListDetail.put("Homes", menu13);
        expandableListDetail.put("Jets & Yatches", menu14);
        expandableListDetail.put("Jewellery", menu15);
        expandableListDetail.put("Premium events", menu16);
        expandableListDetail.put("Travels", menu17);
        expandableListDetail.put("Watches", menu18);


        return expandableListDetail;
    }
}
