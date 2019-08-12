public class User {

    private  Integer a;
    private  String b;
    private String  c;
    private Integer d;

    public User() {
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

    public User(Integer a, String b, String c, Integer d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    public User(Integer a, String b, String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public User(Integer a, String c) {
        this.a = a;
        this.c = c;
    }
}
