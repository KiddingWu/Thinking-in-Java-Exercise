package containers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import cn.org.kidding.util.Countries;

import static cn.org.kidding.util.Print.*;

/**
 * Produce a Map and a Set containing all the countries that begin
 * with 'A'.
 */
public class Ex2 {

    public static void main(String[] args) {

        Map<String, String> hm = new HashMap<>();
        Set<String> hs = hm.keySet();
        Pattern p = Pattern.compile("A[a-zA-Z]*");
        for (int i = 0; i < Countries.DATA.length; i++) {
            if (p.matcher(Countries.DATA[i][0]).matches())
                hm.put(Countries.DATA[i][0], Countries.DATA[i][1]);
        }
        println(hm);
        println(hs);
    }
}
