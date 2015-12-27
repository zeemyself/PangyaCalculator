package com.zeemyself.pangyacalculator;

/**
 * Created by Swas on 8/31/2015.
 */
public class CCTo {
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
    private final double[] ToCalibate = {236.8,237.6,238.4,239.3,240.1,240.9,241.7,242.6,243.4,244.2,245.0,245.8,246.7,247.5,248.3,249.1,250.0,250.8,251.6,252.4,253.2,254.1,254.9,255.7,256.5,257.4,258.2,259.0,259.8,260.6,261.5,262.3,263.1,263.9,264.8,265.6,266.4,267.2,268.0,268.9,269.7,270.5,271.3,272.2,273.0,273.8,274.6,275.4,276.3,277.1,277.9,278.7,279.6,280.4,281.2,282.0,282.8,283.7,284.5,285.3,286.1,287.0,287.8,288.6,289.4,290.2,291.1,291.9,292.7,293.5,294.4,295.2,296.0};
    private double distance;
    private double height;
    private int wind;
    private int angle;
    private int terrain;
    private double QBall;
    private double QRBall;
    private double realDistance,actualDistance,calcDistance;
    private double pureHWI;
    private double HPlus,HMinus;
    private double InfHplus,InfHminus;
    private double InfFront,InfBack;
    private double windForce;
    private double windPlusInfH,windMinusInfH;
    private double windPercentPlus,windPercentMinus;
    private double HWICosWind;
    private double InfActual;
    private double HWIWindPlus,HWIWindMinus;
    private double devFront,devBack;
    private double forceVPlus,forceVMinus;
    private double forceFrontPer,forceBackPer;
    private double calibateFront,calibateBack;
    private double calibateDisFront,calibateDisBack;
    CCTo (String distance,String height,String wind,String angle,String terrain,String slope){
        this.distance = Double.parseDouble(distance);
        this.height = Double.parseDouble(height);
        this.wind = Integer.parseInt(wind);
        this.angle = Integer.parseInt(angle);
        this.terrain = Integer.parseInt(terrain);
        this.QBall = Double.parseDouble(slope);
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
        QRBall =calcQRBall();
        windForce = calcWindForce();
        windPlusInfH = calcWindPlusInfH();
        windMinusInfH = calcWindMinusInfH();
        windPercentPlus = calcWindPercentPlus();
        windPercentMinus = calcWindPercentMinus();

        HWICosWind = calcHWICosWind();
        InfActual = calcInfActual();
        HWIWindPlus = calcHWIWindPlus();
        HWIWindMinus = calcHWIWindMinus();

        devBack = calcDevBack();
        devFront = calcDevFront();
        forceVMinus = calcForceVMinus();
        forceVPlus = calcForceVPlus();
        forceFrontPer = calcForceFrontPer();
        forceBackPer = calcForceBackPer();

        calibateFront = calcCalibate(calcFront());
        calibateBack = calcCalibate(calcBack());

        calibateDisFront = calcDisFront();
        calibateDisBack = calcDisBack();
    }

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
            double temp = Hback * (Math.pow(Varianceback,(oneW100force-realDistance)));
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
        public double calcQRBall(){
            return pureHWI/4*QBall;
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
            return (100 + (windMinusInfH * 4)/-6.25) /100.00;
        }
        public double calcHWICosWind(){
            return pureHWI * Math.sin(Math.toRadians(angle)) * wind;
        }
        public double calcInfActual(){
            if (actualDistance > 0)
                return HWICosWind * InfHplus;
            else
                return HWICosWind * InfHminus;
        }
        public double calcHWIWindPlus(){
            return InfActual * windPercentPlus;
        }
        public double calcHWIWindMinus(){
            return InfActual / windPercentMinus;
        }
        public double calcDevFront(){
            return QRBall + HWIWindPlus;
        }
        public double calcDevBack(){
            return QRBall + HWIWindMinus;
        }
        public double calcForceVPlus(){
            return realDistance + actualDistance - windPlusInfH;
        }
        public double calcForceVMinus(){
            return realDistance + actualDistance + windMinusInfH;
        }
        public double calcForceFrontPer(){
            return 100*(forceVPlus-oneW90force)*(forceVPlus-oneW80force)/((oneW100force-oneW90force)*(oneW100force-oneW80force))+90*(forceVPlus-oneW100force)*(forceVPlus-oneW80force)/((oneW90force-oneW100force)*(oneW90force-oneW80force))+80*(forceVPlus-oneW100force)*(forceVPlus-oneW90force)/((oneW80force-oneW100force)*(oneW80force-oneW90force));
        }
        public double calcForceBackPer(){
            return 100*(forceVMinus-oneW90force)*(forceVMinus-oneW80force)/((oneW100force-oneW90force)*(oneW100force-oneW80force))+90*(forceVMinus-oneW100force)*(forceVMinus-oneW80force)/((oneW90force-oneW100force)*(oneW90force-oneW80force))+80*(forceVMinus-oneW100force)*(forceVMinus-oneW90force)/((oneW80force-oneW100force)*(oneW80force-oneW90force));
        }
        public int calcFront(){
            return (int) Math.ceil(((forceFrontPer - 80) / (20.0/72.0)) + 1);
        }
        public int calcBack(){
            return (int) Math.ceil(((forceBackPer - 80) / (20.0/72.0)) + 1);
        }
        public double calcCalibate(int num){
            try {
                return ToCalibate[num-1];
            }
            catch (Exception e){
                return 0;
            }
        }
        public double calcDisFront(){
            return devFront / 0.2165;
        }
        public double calcDisBack(){
            return devBack / 0.2165;
        }
        public double getForceFront(){
            return calibateFront;
        }
        public double getForceBack(){
            return calibateBack;
        }
        public double getResultFront(){
            double temp = Math.round(calibateDisFront*100);
            temp = temp/100;
            return temp;
        }
        public double getResultBack(){
            double temp = Math.round(calibateDisBack*100);
            temp = temp/100;
            return temp;
        }
        public double getPercentFront(){
            double temp = Math.round(forceFrontPer*100);
            temp = temp/100;
            return temp;
        }
        public double getPercentBack(){
            double temp = Math.round(forceBackPer*100);
            temp = temp/100;
            return temp;
        }



}
