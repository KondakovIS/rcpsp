package polygon;

public class Point implements Comparable<Point>{
	
	double x;
	double y;
	double tangence;
	
	public double getX() {
		return x;
	}
	
	public void setX(double newX) {
		x = newX;
	}

	public double getY() {
		return y;
	}
	
	public void setY(double newY) {
		y = newY;
	}
	
	public Point(double newX, double newY) {
		x = newX;
		y = newY;
		tangence = y/x;
	}
	
	public double getTan() {
		return tangence;
	}
	
	public int compareTo(Point point) {  
        return (this.tangence < point.tangence ) ? -1: (this.tangence > point.tangence ) ? 1:0 ;  
       }
	
	public Point() {
	}
	
	public void print() {
		System.out.println(x + " υσι " + y);
	}

	public void inc(Point p) {
		x = x+p.getX();
		y = y+p.getY();
	}
	
	public void mult(double alph) {
		x = x*alph;
		y = y*alph;
	}
}
