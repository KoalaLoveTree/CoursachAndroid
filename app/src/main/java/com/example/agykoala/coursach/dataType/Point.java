package com.example.agykoala.coursach.dataType;



import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by AgyKoala on 31.05.2017.
 */

public class Point implements Serializable{

    private double pointX, pointY;

    public Point(double pointX, double pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public double getPointX() {
        return pointX;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    @Override
    public String toString() {
        return "Point{" +
                "point x = " + pointX
                + ", point y = "
                + pointY + '}';
    }

    public static final Comparator<Point> COMPARE_BY_X = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            return Double.compare(o1.getPointX(),o2.getPointX());
        }
    };
}
