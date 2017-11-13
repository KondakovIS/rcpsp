package polygon;

import java.util.ArrayList;
import java.util.Comparator;

public class VectArray {
	
	ArrayList<Point> list= new ArrayList<Point>();
	static Point cleft;
	static Point clow;
	static double eps = 0.001;
	static int k;
	
	public int getSize() {
		return list.size();
	}
	
	public void add(Point point) {
		list.add(point);
	}
	
	public Point getSum() {
		Point point = new Point(0, 0);
		int size = this.getSize();
		for (int i = 0; i < size; i++) {
			point.inc(list.get(i));
		}
		return point;
	}
	
	public VectArray() {}
	
	public static boolean cIsRighter(Point c, VectArray vectors) {
		VectArray polyline = new VectArray(); 
		polyline = vectors;
		Point sum = new Point(0,0);
		//int k = 0;
		Point s;
		for (int i = 0; i < polyline.getSize(); i++) { 
			sum.inc(polyline.list.get(i));
			s = new Point(0,0);
			s.inc(sum);
			polyline.list.set(i, s);
			if (polyline.list.get(i).getY() - c.getY() > -eps) {
				k = i;
				break;
			}
		}
		double yvk = polyline.list.get(k).getY();
		double yvprev = polyline.list.get(k-1).getY();
		double yc = c.getY();
		double xvk = polyline.list.get(k).getX();
		double xvprev = polyline.list.get(k-1).getX();
		double xc = c.getX();
		cleft = new Point(0,0);
		cleft.inc(c);
		double newxc = (xvk-xvprev)*(yvk-yc)/(yvk-yvprev)+xc;
		Point black = new Point(xvk-newxc, 0);
		cleft.inc(black);
		return newxc - xvk > eps;
		}
	
	public static boolean cIsUpper(Point c, VectArray vectors) {
		VectArray polyline = vectors;
		Point sum = new Point(0,0);
		Point s;
		//int k = 0;
		for (int i = 0; i < polyline.getSize(); i++) { 
			sum.inc(polyline.list.get(i));
			s = new Point(0,0);
			s.inc(sum);
			polyline.list.set(i, s);
			
			if (polyline.list.get(i).getX() - c.getX() > -eps) {
				k = i;
				System.out.println(k + " numb " ); sum.print();
				break;
			}
		}
		double yvk = polyline.list.get(k).getY();
		double yvprev = polyline.list.get(k-1).getY();
		double yc = c.getY();
		double xvk = polyline.list.get(k).getX();
		double xvprev = polyline.list.get(k-1).getX();
		double xc = c.getX();
		clow = new Point(0,0);
		clow.inc(c);
		double newyc = (double) ((xvk-xc)*(yvk-yvprev))/(xvk-xvprev)+yc;
		Point black = new Point(0, yvk-newyc);
		clow.inc(black);
		return (newyc - yvk > eps);
		}
	
	public static int whereIsC(VectArray lower, VectArray upper, Point c) {
		Point v = lower.getSum();
		if (v.getX() - c.getX() < eps) {
			if (v.getY() - c.getY() < eps) { return 1;}
			else if (cIsRighter(c, lower)) {return 3;}
			}
		else if(cIsUpper(c,upper)) {return 2;};
		
		return 4;
	}
	
	public VectArray cloneIt() {
		VectArray vectArr = new VectArray();
		Point point;
		for (int i=0; i< this.list.size(); i++) {
			point = this.list.get(i).clone();
			vectArr.add(point);
		}
		return vectArr;
	}
	
	public static ArrayList<Double> alphas(VectArray vectors, Point c) {
		ArrayList<Double> alps = new ArrayList<Double>();
		VectArray lowerBorder = new VectArray();
		lowerBorder = vectors.cloneIt();
		int size = vectors.getSize();
		lowerBorder.list.sort(new Comparator<Point>() {
			@Override
			public int compare(Point m, Point n) {
				return m.compareTo(n);
			}
		});
		
		VectArray upperBorder = new VectArray();
		for (int i = 0; i < size; i++) {
			upperBorder.list.add(lowerBorder.list.get(size-i-1));
		}
		
		lowerBorder.list.forEach((n) -> n.print());
		upperBorder.list.forEach((n) -> n.print());
		
		int area = whereIsC(lowerBorder, upperBorder, c);
		System.out.println(area);
		switch (area) {
			case 1: for (int i=0; i < size; i++ ) {alps.add(1.0);}; break;
			case 2: alps = upperBorder.alphForSecond(); break;
			case 3: alps = lowerBorder.alphForThird(); break;
			case 4: alps = lowerBorder.alphForFourth(); break;
			default: break;
		}
		return alps;
	}
	
	public ArrayList<Double> alphForThird() {
		ArrayList<Double> alphs = new ArrayList<Double>();alphs.add(0.0); alphs.add(0.0); alphs.add(0.0); alphs.add(0.0);
		for (int i=0; i<this.getSize(); i++) {
			if (i<k) {
				alphs.set(this.list.get(i).getKey(), 1.0); 
			}
			else if (i > k) {
				alphs.set(this.list.get(i).getKey(), 0.0);
			}			
		}
		double cDelt = cleft.getX()-this.list.get(k-1).getX();
		double vkDelt = this.list.get(k).getX() - this.list.get(k-1).getX();
		alphs.set(this.list.get(k).getKey(), (double)cDelt/vkDelt);
		return alphs;
	}
 

	public ArrayList<Double> alphForSecond() {
		ArrayList<Double> alphs = new ArrayList<Double>();
		alphs.add(0.0); alphs.add(0.0); alphs.add(0.0); alphs.add(0.0); 
		for (int i=0; i<this.getSize(); i++) {
			if (i<k) {
				alphs.set(this.list.get(i).getKey(), 1.0);
			}
			else if (i > k) {
				alphs.set(this.list.get(i).getKey(), 0.0);
			}			
		}
		double cDelt = clow.getX()-this.list.get(k-1).getX();
		double vkDelt = this.list.get(k).getX() - this.list.get(k-1).getX();
		alphs.set(this.list.get(k).getKey(), (double)cDelt/vkDelt);
		return alphs;
	}
	
	public ArrayList<Double> alphForFourth() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int iByKey(int i) {
		for (int j = 0; j<this.getSize(); j++) {
			if (this.list.get(j).getKey() == i) return j;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		 
		VectArray vectors = new VectArray();
		int key = 0;
		Point a = new Point(1,2,key++);
		vectors.add(a);
		Point b = new Point(2,3,key++);
		vectors.add(b);
		Point c = new Point(1,1,key++);
		vectors.add(c);
		Point k = new Point(2,5,5);
		Point v = new Point();
		v = vectors.getSum();
		 ArrayList<Double> lfighno = alphas(vectors, k);
		System.out.println(lfighno);
		//Point pointC = new Point(2,2);
		//ArrayList<Points> 
		v.print();
		
		//return vectors;
		
	}
}
