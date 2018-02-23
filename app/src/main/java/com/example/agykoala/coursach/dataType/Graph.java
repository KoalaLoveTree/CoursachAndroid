package com.example.agykoala.coursach.dataType;

import com.example.agykoala.coursach.algorithms.Interpolation;
import com.example.agykoala.coursach.interfaces.DeleteDublicates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Created by AgyKoala on 31.05.2017.
 */

public class Graph extends Interpolation implements DeleteDublicates<Point>{

    private ArrayList<Point> enterData = new ArrayList<>();
    private ArrayList<Point> outputData = new ArrayList<>();

    private double step = 0.01;

    public Graph(ArrayList<Point> enterData, double step) {
        this.enterData = enterData;
        this.step = step;
    }

    public Graph() {
    }

    public ArrayList<Point> getEnterData() {
        return enterData;
    }

    public void setEnterData(Point point) {
        this.enterData.add(point);
    }
    public void setEnterData(ArrayList<Point> enterData) {
        this.enterData = enterData;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public ArrayList<Point> getOutputData() {
        return outputData;
    }

    public void setOutputData(ArrayList<Point> outputData) {
        this.outputData = outputData;
    }

    public void createOutputData(){
        outputData.addAll(enterData);
        for (Point point : enterData)
            for (double i = enterData.get(0).getPointX();
                 i < enterData.get(enterData.size()-1).getPointX(); i+= step){
                if (i == point.getPointX())
                    break;
                else
                    outputData.add(new Point(i,InterpolateLagrangePolynomial(i, enterData)));
            }
    }


    @Override
    public ArrayList<Point> DelDubl(ArrayList<Point> arrayList) {
        ArrayList<Point> result = new ArrayList<Point>(new HashSet<Point>(arrayList));
        Collections.sort(result,Point.COMPARE_BY_X);
        return result;
    }
}
