import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {

        User user = new User(1,"2","3",4);
        try {
            formatSQL<User> formatSQL = new formatSQL<>(user);
            System.out.println(formatSQL.sql);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
