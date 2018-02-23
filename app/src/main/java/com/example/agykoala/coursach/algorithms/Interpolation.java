package com.example.agykoala.coursach.algorithms;

import com.example.agykoala.coursach.dataType.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AgyKoala on 31.05.2017.
 */

public class Interpolation {

    public double InterpolateLagrangePolynomial(double x, ArrayList<Point> values) {
        double lagrangePol = 0;
        for (int i = 0; i < values.size(); i++) {
            double basicsPol = 1;
            for (int j = 0; j < values.size(); j++) {
                if (j != i) {
                    basicsPol *= (x - values.get(j).getPointX()) / (values.get(i).getPointX() -
                            values.get(j).getPointX());
                }
            }
            lagrangePol += basicsPol * values.get(i).getPointY();
        }
        return lagrangePol;
//        double res = 0, s = 0, s1 = 1, s2 = 1;
//        for (int i = 0; i < (values.size()/2); i++){
//            for(int j = 0; j < (values.size()/2); j++){
//                if(i != j){
//                    s1 = s1 * (x - values.get(j).getPointX());
//                    s2 = s2 * (values.get(i).getPointX() - values.get(j).getPointX());
//                }
//            }
//            s = values.get(i).getPointY()*(s1/s2);
//            res = res + s;
//        }
//        return res;
    }

}
