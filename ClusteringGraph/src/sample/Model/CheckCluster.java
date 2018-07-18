package sample.Model;

import java.util.ArrayList;
import java.util.List;

public class CheckCluster {

    private double distance;
    private int x,y;
    private List<CheckCluster> list=new ArrayList<>();


    public CheckCluster(int x,int y,double distance){
        this.distance=distance;
        this.x=x;
        this.y=y;

    }

    public double getDistance() {
        return distance;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
