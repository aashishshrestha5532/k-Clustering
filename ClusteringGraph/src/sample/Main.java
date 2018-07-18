package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import sample.Model.CheckCluster;
import sample.Model.Cluster;
import sample.Model.Performance;
import sample.Model.RandomP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Main extends Application {
    Random random = new Random();
    int k = 1;

    private List<Performance> performanceList = new ArrayList<>();
    private List<RandomP> randomList = new ArrayList<>();
    private List<Cluster> clusterList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Clustering example");
        // final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("No of clusters");

        final LineChart<Number, Number> lineChart =
                new LineChart<>(xAxis, yAxis);//X-axis and Y -axis separation for distinction

        lineChart.setTitle("k- means clustering");
        XYChart.Series series = new XYChart.Series();
        series.setName("wcss at respective points");
        Performance performance = new Performance(12, 20);
        performanceList.add(performance);
        performanceList.add(new Performance(15, 20));
        performanceList.add(new Performance(15, 20));
        performanceList.add(new Performance(25, 21));
        performanceList.add(new Performance(25, 50));
        performanceList.add(new Performance(25, 50));
        performanceList.add(new Performance(16, 30));
        performanceList.add(new Performance(25, 40));
        performanceList.add(new Performance(15, 30));
        performanceList.add(new Performance(16, 22));
        performanceList.add(new Performance(17, 30));

        while (k < 10) {

            // here we choose random k points for centroid of the cluster
            for (int i = 0; i < k; i++) {
                int x = random.nextInt(7) + 1;
                int y = random.nextInt(200) + 1;
                System.out.println("x1" + (i + 1) + " " + x + " y1" + (i + 1) + " " + y);
                RandomP randomP = new RandomP(x, y);
                randomList.add(randomP);
            }


            k++;
        }
        System.out.println("******************************************");
        getDistance();


        for (int k = 0; k < clusterList.size(); k++)
            series.getData().add(new XYChart.Data(k + 1, clusterList.get(k).getNum()));


        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(series);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void getDistance() {
//System.out.println("Size "+randomList.size());
        int count = 0;
        List<RandomP> randomPS = new ArrayList<>();
        for (int p = 0; p < 7; p++) {
            for (int q = 0; q < p + 1; q++) {
                 System.out.println("count "+count + " "+"x- "+randomList.get(count).getX() + " y -"+randomList.get(count).getY());
                //System.out.println("count"+count);
                //compare the distance between the centroids and the  dataset to calculate the wcss
                randomPS.add(new RandomP(randomList.get(count).getX(), randomList.get(count).getY()));


                count++;
            }
            calculateWcss(randomPS, performanceList);
            randomPS.clear();//this clear is used for clearance of array once its used
            //System.out.println("");

            System.out.println("x1" + (p + 1) + " " + randomList.get(p).getX() + " y1" + (p + 1) + " " + randomList.get(p).getY());

        }
    }

    public void calculateWcss(List<RandomP> rList, List<Performance> pList) {
        int rsize = rList.size();
        int psize = pList.size();
        double sum = 0;
        List<CheckCluster> clusters = new ArrayList<>();
        System.out.println("Random size :" + rsize);

        for (int j = 0; j < rsize; j++) {
            for (int i = 0; i < psize; i++) {
                clusters.add(new CheckCluster(pList.get(i).getAttendance(), pList.get(i).getMarks(), Math.sqrt(calculateSummation(rList.get(j).getX(), rList.get(j).getY(), pList.get(i).getAttendance(), pList.get(i).getMarks()))));
                //System.out.println("xRand["+j+"]"+"["+i+"]"+rList.get(j).getX() +" y Rand "+rList.get(j).getY());
                sum = sum + calculateSummation(rList.get(j).getX(), rList.get(j).getY(), pList.get(i).getAttendance(), pList.get(i).getMarks());
            }
        }
        clusterList.add(new Cluster(sum));
        System.out.println("The total wcss " + sum);
        System.out.println("No of k ->" + rsize);
        System.out.println("Cluster size " + clusters.size());


        int count = 0;

        for (int i = 0; i < clusters.size(); i++) {

            for (int j = i + 1; j < clusters.size();) {
                //compare list j and list i
                if (compare(clusters.get(i).getX(), clusters.get(i).getY(), clusters.get(j).getX(), clusters.get(j).getY())) {
                    count++;
                }

                double value = calculateSummation(clusters.get(i).getX(), clusters.get(i).getY(), clusters.get(j).getX(), clusters.get(j).getY());
                j=j+psize-1;
            }
            System.out.println("Count is " + count);

            count = 0;
        //selecting the common data from list that is present at 1 , 12 , 23 , 34 position
        System.out.println("The distance with points ->  (" + clusters.get(i).getX() + "," + clusters.get(i).getY() + ") ->" + clusters.get(i).getDistance());

    }

}

    public double calculateSummation(int x1, int y1, int x2, int y2) {

        double value = ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        return value;
    }

    public boolean compare(int x1, int y1, int x2, int y2) {
        boolean flag = false;
        if (x1 == x2 && y1 == y2)
            flag = true;
        return flag;
    }








        public static void main(String[] args){


            launch(args);
        }
    }

