package com.zeemyself.pangyacalculator;

/**
 * Created by Swas on 8/31/2015.
 */
public class CCTo {
    private double distance;
    private double height;
    private int wind;
    private int angle;
    private int terrain;
    private double realDistance,actualDistance,calcDistance;
    private double pureHWI;
    private double HPlus,HMinus;
    private double InfHplus,InfHminus;
    private double InfFront,InfBack;
    private double windForce;
    private double windPlusInfH,windMinusInfH;
    private double windPercentPlus,windPercentMinus;
    CCTo (String distance,String height,String wind,String angle,String terrain){
        this.distance = Double.parseDouble(distance);
        this.height = Double.parseDouble(height);
        this.wind = Integer.parseInt(wind);
        this.angle = Integer.parseInt(angle);
        this.terrain = Integer.parseInt(terrain);
        realDistance = calcRealDistance();
        pureHWI = calcPureHWI();
        HPlus = calcHPlus();
        HMinus = calcHMinus();
        actualDistance = calcActualDistance();
        calcDistance = calcDistance();
        InfFront = calcInfFront();
        InfBack = calcInfBack();
        InfHplus = calcInfHPlus();
        InfHminus = calcInfHMinus();
        windForce = calcWindForce();
        windPlusInfH = calcWindPlusInfH();
        windMinusInfH = calcWindMinusInfH();
        windPercentPlus = calcWindPercentPlus();
        windPercentMinus = calcWindPercentMinus();
    }
    private final double oneW100force = 301.5;
    private final double oneW100hwi = 1.08;
    private final double oneW90force = 268;
    private final double oneW90hwi = 0.772;
    private final double oneW80force = 235.75;
    private final double oneW80hwi = 0.545;

    private final double Hfront = 0.675;
    private final double Hback = 0.615;
    private final double Variancefront = 1.0112;
    private final double Varianceback = 1.011;
        public double calcPureHWI(){
            double firsttemp = 0;
            double secondtemp =0;
            double thirdtemp=0;
             firsttemp = ((oneW100hwi*(realDistance - oneW90force)) * (realDistance-oneW80force)) / ((oneW100force - oneW90force) * (oneW100force - oneW80force));
             secondtemp = ((oneW90hwi*(realDistance - oneW100force)) * (realDistance-oneW80force)) / ((oneW90force - oneW100force) * (oneW90force - oneW80force));
             thirdtemp = ((oneW80hwi*(realDistance - oneW100force)) * (realDistance-oneW90force)) / ((oneW80force - oneW100force) * (oneW80force - oneW90force));
            return firsttemp + secondtemp+thirdtemp;
        }
        public double calcRealDistance(){
            if(terrain == 100)
                return distance;
            else if(terrain == 98)
                return distance+2;
            else if(terrain == 97)
                return distance+2.35;
            else if(terrain == 95)
                return distance+4;
            else if(terrain == 85)
                return distance+12.75;
            else
                return distance;
        }
        public double calcHPlus() {
            double temp = Hfront * (Math.pow(Variancefront,(oneW100force-realDistance)));
            return temp;
        }
        public double calcHMinus() {
            double temp = Hback * (Math.pow(Variancefront,(oneW100force-realDistance)));
            return temp;
        }
        public double calcInfHPlus(){
            return (100+(actualDistance/-InfFront))/100;
        }
        public double calcInfHMinus(){
          return (100+(actualDistance/-InfBack))/100;
        }
        public double getHWI(){
            return pureHWI;
        }
        public double calcInfFront(){
            return ((((2.8*(calcDistance-oneW90force))*(calcDistance-oneW80force))/((oneW100force-oneW90force)*(oneW100force-oneW80force)))+(((3*(calcDistance-oneW100force))*(calcDistance-oneW80force))/((oneW90force-oneW100force)*(oneW90force-oneW80force))))+(((3*(calcDistance-oneW100force))*(calcDistance-oneW90force))/((oneW80force-oneW100force)*(oneW80force-oneW90force)));
        }
        public double calcInfBack(){
            return ((((4*(calcDistance-oneW90force))*(calcDistance-oneW80force))/((oneW100force-oneW90force)*(oneW100force-oneW80force)))+(((4*(calcDistance-oneW100force))*(calcDistance-oneW80force))/((oneW90force-oneW100force)*(oneW90force-oneW80force))))+(((3*(calcDistance-oneW100force))*(calcDistance-oneW90force))/((oneW80force-oneW100force)*(oneW80force-oneW90force)));
        }
        public double calcActualDistance(){
            if(height < 0)
                return height*HMinus;
            else if(distance == 0)
                return height;
            else
                return height*HPlus;
        }
        public double calcDistance(){
            return realDistance+actualDistance;
        }
        public double calcWindForce() {
            return pureHWI * wind * Math.cos(Math.toRadians(angle));
        }
        public double calcWindPlusInfH(){
            return Math.cos(Math.toRadians(angle)) * wind * pureHWI * 0.95 * (1-(actualDistance*0.016));
        }
        public double calcWindMinusInfH(){
            return Math.cos(Math.toRadians(angle)) * wind * pureHWI * 1.25 * (1-(actualDistance*0.016));
        }
        public double calcWindPercentPlus(){
//            Actually it might not be 1 excel error?
            return 1;
        }
        public double calcWindPercentMinus(){
            return (100 + (windMinusInfH * 4)/-6.25) /100;
        }


}
