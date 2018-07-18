package sample.Model;

public class Cluster {

    private double sum;
    private int k;


    public Cluster(double sum){
        this.sum=sum;

    }

    public int getK() {
        return k;
    }

    public double getNum() {
        return sum;
    }
}
