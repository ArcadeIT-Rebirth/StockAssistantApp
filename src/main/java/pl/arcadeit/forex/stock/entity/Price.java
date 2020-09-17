package pl.arcadeit.forex.stock.entity;

// API is getting back only price levels, so I created Price class

public class Price {
    private double o;
    private double c;
    private double h;
    private double l;
    private double pc;
    private long t;

    public Price() {
    }

    @Override
    public String toString() {
        return "Price{" +
                "open=" + o +
                ", close=" + c +
                ", high=" + h +
                ", low=" + l +
                ", previous close=" + pc +
                ", timestamp=" + t +
                '}';
    }

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public double getO() {
        return o;
    }

    public void setO(double o) {
        this.o = o;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getL() {
        return l;
    }

    public void setL(double l) {
        this.l = l;
    }

    public double getPc() {
        return pc;
    }

    public void setPc(double pc) {
        this.pc = pc;
    }
}
