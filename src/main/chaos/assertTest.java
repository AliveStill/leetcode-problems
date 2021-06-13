public class assertTest {

    // note, vm flag -ea enables assertion
    public static void main(String[] args) {
        assert false;
        System.out.println("error");
    }
}
