package DynamicArray;

public class Runner {
    public static void main(String[] args) {
        Dynamic<Integer> array = new Dynamic<>();
        array.add(5);
        System.out.println(array.get(0));
    }
}
