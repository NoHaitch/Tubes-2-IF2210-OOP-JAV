public class tes {
    public void increment(Integer a) {
        a++;
    }

    public static void main(String[] args) {
        tes t = new tes();
        Integer a = 1;
        t.increment(a);
        System.out.println(a);
    }
}
