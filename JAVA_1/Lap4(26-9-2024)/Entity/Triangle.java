package Entity;
import java.text.DecimalFormat;


public class Triangle extends Point{
    public Point A;
    public Point B;
    public Point C;


    public boolean isTriangle() {
        double a =A.disance(B);
        double b =B.disance(C);
        double c =A.disance(C);
        return (a<b+c)&&(b<c+a)&&(c<a+b);

    }


    public double Dientich(Point p1, Point p2,Point p3){
        double a =p1.disance(p2);
        double b =p2.disance(p3);
        double c =p1.disance(p3);
        double p = (a+b+c)/2;
        double S = Math.abs(Math.sqrt(p * (p - a) * (p - b) * (p - c)));
        long roundedS = Math.round(S);
        System.out.println(roundedS);
        return roundedS;
    }
    public boolean checkPoint(Point p){
        double ABC = Dientich(A,B,C);
        double pAC = Dientich(p,A,C);
        double pAB = Dientich(p,A,B);
        double pBC = Dientich(p,B,C);
        if(ABC == (pAB+pBC+pBC)){
            return true;
        }else{
            return false;
        }

    }


}
