public class Player {
    private String name;
    private int age;
    private double weight;
    private String position;

    public Player(String name, int age, double weight, String position) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.position = position;
    }

    public String toString() {
        return name + " | " + age + " yrs | " + weight + " kg | " + position;
    }
}
