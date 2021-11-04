import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.Cleaner;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;


public class J {
//    interface IApple {
//        String getColor();
//        int getType();
//    }

    private static class Apple {
        private final String color = "red";
        private final int type = 1;

//        @Override
        public String getColor() {
            return color;
        }

//        @Override
        public int getType() {
            return type;
        }
    }

    public static void main() throws Throwable {
        System.out.println(Arrays.asList(Apple.class.getInterfaces()));
        Object proxyInstance = Proxy.newProxyInstance(
                J.class.getClassLoader(),
                Apple.class.getInterfaces(),
                (proxy, method, args1) -> {
                    System.out.println("Called getColor() method on Apple");
                    return method.invoke(new Apple(), args1);
                });

        Apple appleProxy = (Apple) proxyInstance;
        System.out.println(appleProxy.getColor());
        System.out.println(appleProxy.getType());
    }

    public static void main(String[] args) {
        try {
            main();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
