package com.zeemyself.pangyacalculator;

/**
 * Created by Swas on 8/31/2015.
 */
public class CCBs {
    private final double oneW100force = 265.25;
    private final double oneW100hwi = 1.297;
    private final double oneW90force = 231.5;
    private final double oneW90hwi = 0.9045;
    private final double oneW80force = 199;
    private final double oneW80hwi = 0.64;
    private final double Hfront = 0.8;
    private final double Hback = 0.65;
    private final double Variancefront = 1.01277;
    private final double Varianceback = 1.0155;
    private final double[] ToCalibate = {196.0,196.8,197.6,198.3,199.1,199.9,200.7,201.4,202.2,203.0,203.8,204.6,205.3,206.1,206.9,207.7,208.4,209.2,210.0,210.8,211.6,212.3,213.1,213.9,214.7,215.4,216.2,217.0,217.8,218.6,219.3,220.1,220.9,221.7,222.4,223.2,224.0,224.8,225.6,226.3,227.1,227.9,228.7,229.4,230.2,231.0,231.8,232.6,233.3,234.1,234.9,235.7,236.4,237.2,238.0,238.8,239.6,240.3,241.1,241.9,242.7,243.4,244.2,245.0,245.8,246.6,247.3,248.1,248.9,249.7,250.4,251.2,252.0,252.8,253.6,254.3,255.1,255.9,256.7,257.4,258.2,259.0,259.8,260.6,261.3,262.1,262.9,263.7,264.4,265.2,266.0,266.8,267.6,268.3,269.1,269.9,270.7,271.4,272.2,273.0,273.8,274.6,275.3,276.1,276.9,277.7,278.4,279.2,280.0};
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
    private double NF1,NF2,NF3;
    private double NB1,NB2,NB3;
    private double SF1,SF2;
    private double SB1,SB2;

    CCBs(String distance, String height, String wind, String angle, String terrain, String slope){
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

        NF1 = calcNF1();
        NF2 = calcNF2();
        NF3 = calcNF3();
        NB1 = calcNB1();
        NB2 = calcNB2();
        NB3 = calcNB3();

        SF2 = calcSF2();
        SF1 = calcSF1();
        SB2 = calcSB2();
        SB1 = calcSB1();

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
            return distance+3.75;
        else if(terrain == 97)
            return distance+4.5;
        else if(terrain == 95)
            return distance+4;
        else if(terrain == 85)
            return distance+12;
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
        //Excel shown 9 No idea  :(
        return 9;
    }
    public double calcInfBack(){
        return 1.02*(calcDistance-oneW90force)*(calcDistance-oneW80force)/((oneW100force-oneW90force)*(oneW100force-oneW80force))+1.45*(calcDistance-oneW100force)*(calcDistance-oneW80force)/((oneW90force-oneW100force)*(oneW90force-oneW80force))+2.15*(calcDistance-oneW100force)*(calcDistance-oneW90force)/((oneW80force-oneW100force)*(oneW80force-oneW90force));
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
        return Math.cos(Math.toRadians(angle)) * wind * pureHWI * 1 * (1-(actualDistance*0.016));
    }
    public double calcWindMinusInfH(){
        return Math.cos(Math.toRadians(angle)) * wind * pureHWI * 1.3 * (1-(actualDistance*0.016));
    }
    public double calcWindPercentPlus(){
        return (100 + (windPlusInfH * 2.75)/-4) /100.00;
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
        if(NF3 == 0)
            return (int) Math.floor(((forceFrontPer - 70) / (20.0 / 72.0)) + 1);
        else
            return (int) Math.ceil(((forceFrontPer - 70) / (20.0/72.0)) + 1);
    }
    public int calcBack(){
        if(NB3 == 0)
            return (int) Math.floor(((forceBackPer - 70) / (20.0/72.0)) + 1);
        else
            return (int) Math.ceil(((forceBackPer - 70) / (20.0/72.0)) + 1);
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
    public double calcNF1(){
        return forceFrontPer%(20.0/72.0);
    }
    private double calcNF2(){
        return NF1/(20.0/72.0);
    }
    private double calcNF3(){
        if(NF2 < 0.288)
            return Math.floor(NF2);
        else
            return Math.ceil(NF2);

    }
    private double calcNB1(){
        return forceBackPer%(20.0/72.0);
    }
    private double calcNB2(){
        return NB1/(20.0/72.0);
    }
    private double calcNB3(){
        if(NB2 < 0.288)
            return Math.floor(NB2);
        else
            return Math.ceil(NB2);
    }
    private double calcSF1(){
        if(NF1 < 0.08)
            return 9;
        else
            return SF2;
    }
    private double calcSF2(){
        if(NF1>((20.0/72.0)-0.08))
            return 9;
        else
            return 8;
    }
    private double calcSB1(){
        if(NB1 < 0.08)
            return 9;
        else
            return SB2;
    }
    private double calcSB2(){
        if(NB1 > (20.0/72.0)-0.08)
            return 9;
        else
            return 8;
    }
    public int getSpinFront(){
        return (int)SF1;
    }
    public int getSpinBack(){
        return (int)SB1;
    }


}
