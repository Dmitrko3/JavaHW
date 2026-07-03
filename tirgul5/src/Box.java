class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    // V = L * W * H
    public double getVolume() {
        return length * width * height;
    }

    // M = 2*L*W + 2*L*H + 2*W*H
    public double getSurfaceArea() {
        return (2 * length * width) + (2 * length * height) + (2 * width * height);
    }
}