package sample.Model;

public class Performance {

    private int attendance;
    private int marks;

    public Performance(int attendance,int marks){
        this.attendance=attendance;
        this.marks=marks;
    }

    public int getMarks() {
        return marks;
    }

    public int getAttendance() {
        return attendance;
    }
}

