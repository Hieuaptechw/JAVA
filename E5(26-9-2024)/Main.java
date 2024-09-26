import Entity.Point;
import Entity.Triangle;

public class Main {
    public static void main(String[] args) {
            Point A = new Point(1,1);
            Point B = new Point(5,1);
            Point C = new Point(3,4);
            Triangle triangle = new Triangle();
                triangle.A = A;
                triangle.B = B;
                triangle.C = C;
            if(!triangle.isTriangle()){
                System.out.println("3 diem khong thang hang");
                return;
            }
            Point P = new Point(3,2);
            if(triangle.checkPoint(P)){
                System.out.println("p NAM TRONG TAM GIAC");
            }else{
                System.out.println("P khong nam TRONG tam giac");
            }
    }
}