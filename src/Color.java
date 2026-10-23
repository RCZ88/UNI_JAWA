public enum Color {
    RED("red"),
    ORANGE("orange"),
    WHITE("white"),
    YELLOW("yellow"),
    BLUE("blue"),
    GREEN("green");

    private final String color;

    Color(String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
