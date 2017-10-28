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


        List<String> list1 = new ArrayList<String>();
        list1.add("Submenu 1");
        list1.add("Submenu 2");
        list1.add("Submenu 3");
        list1.add("Submenu 4");


        List<String> list2 = new ArrayList<String>();
        list2.add("Fennel seed" );
        list2.add("Asafoetida");
        list2.add("Red Chilli");
        list2.add("Black cardamon");
        list2.add("White Pepper");
        list2.add("Peppercorns");
        list2.add("Blackcumin");
        list2.add("Capers");


        expandableListDetail.put("Accesories", list1);
        expandableListDetail.put("Apparel", list2);
        expandableListDetail.put("Art & Collectibles", menu3);
        expandableListDetail.put("Beauty", menu4);
        expandableListDetail.put("Bikes", menu5);
        expandableListDetail.put("Cars", menu6);
        expandableListDetail.put("Decores", menu7);
        expandableListDetail.put("Footwear", menu8);
        expandableListDetail.put("Gadgets", menu9);
        expandableListDetail.put("Gourment", menu10);
        expandableListDetail.put("Homes", menu11);
        expandableListDetail.put("Jets & Yachts", menu12);
        expandableListDetail.put("Jewellery", menu13);
        expandableListDetail.put("Travel", menu14);
        expandableListDetail.put("Watches", menu15);


        return expandableListDetail;
    }
}
