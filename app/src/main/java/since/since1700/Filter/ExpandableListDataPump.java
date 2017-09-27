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


        expandableListDetail.put("GRAINS", list1);
        expandableListDetail.put("HERBES & SPICES", list2);
        expandableListDetail.put("RICES", menu3);
        expandableListDetail.put("DHAL", menu4);


        return expandableListDetail;
    }
}
