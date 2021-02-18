import java.lang.reflect.Proxy;
import java.util.*;


public class J {
    interface IApple {
        String getColor();
    }

    private static class Apple implements IApple {
        private String color = "red";

        public String getColor() {
            return color;
        }
    }

    public static void main() throws Throwable {
        Object proxyInstance = Proxy.newProxyInstance(
                J.class.getClassLoader(),
                Apple.class.getInterfaces(),
                (proxy, method, args1) -> {
                    System.out.println("Called getColor() method on Apple");
                    return method.invoke(new Apple(), args1);
                });

        IApple appleProxy = (IApple) proxyInstance;
        System.out.println(appleProxy.getColor());
    }


}

