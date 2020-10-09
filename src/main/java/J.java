import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class J {

    public static void main() throws Exception {
        var m = new HashMap<String, Integer>();
        m.put("1", 1);
        m.put("2", 2);
        m.put("3", 3);
        m.put("4", 4);
        m.keySet().remove("1");
        System.out.println(m.toString());
    }

}

