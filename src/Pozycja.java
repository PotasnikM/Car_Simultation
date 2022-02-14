public class Pozycja {
    private double x;
    private double y;

    public Pozycja() {
    }

    public Pozycja(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public void przemiesc(double v, double dt, Pozycja cel) {
        if (x != cel.getX() || y != cel.getY()) {
            double dx = (v * (dt / 1000000) * (cel.getX() - x) / (Math.sqrt(Math.pow(cel.getX() - x, 2) + Math.pow(cel.getY() - y, 2))));
            double dy = (v * (dt / 1000000) * (cel.getY() - y) / (Math.sqrt(Math.pow(cel.getX() - x, 2) + Math.pow(cel.getY() - y, 2))));
            if (dx > Math.abs(cel.getX() - x)) {
                setX(cel.getX());
            } else {
                setX(x + dx);
            }
            if (dy > Math.abs(cel.getY() - y)) {
                setY(cel.getY());
            } else {
                setY(y + dy);
            }
        }
    }
}
