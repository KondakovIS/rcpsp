package polygon;

import java.util.ArrayList;
import java.util.Comparator;

public class VectArray {
	
	ArrayList<Point> list= new ArrayList<Point>();
	
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
	
	public VectArray() {
		
	}
	
	public static void main(String[] args) {
		 
		VectArray lowerBorder = new VectArray();
		Point a = new Point(6,8);
		lowerBorder.add(a);
		Point b = new Point(5,7);
		lowerBorder.add(b);
		Point c = new Point(1,1);
		lowerBorder.add(c);
		lowerBorder.list.sort(new Comparator<Point>() {
			@Override
			public int compare(Point m, Point n) {
				return m.compareTo(n);
			}
		});
		
		VectArray upperBorder = new VectArray();
		int size = lowerBorder.getSize();
		for (int i = 0; i < size; i++) {
			upperBorder.list.add(lowerBorder.list.get(size-i-1));
		}
		
		Point v = new Point();
		v = lowerBorder.getSum();
		lowerBorder.list.forEach((n) -> n.print());
		upperBorder.list.forEach((n) -> n.print());
		v.print();
		
	}
}
