package polygon;

public class Point implements Comparable<Point>{
	
	double x;
	double y;
	double tangence;
	int key;
	double alpha = 1;
	
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
	
	public int getKey() {
		return key;
	}
	
	public void setKey(int k) {
		key = k;
	}
	
	public void setAlpha(double alph) {
		alpha = alph;
	}
	
	public Point(double newX, double newY) {
		x = newX;
		y = newY;
		tangence = y/x;
	}
	
	public Point(double newX, double newY, int k) {
		x = newX;
		y = newY;
		tangence = y/x;
		key = k;
	}
	
	public double getTan() {
		return tangence;
	}
	
	public int compareTo(Point point) {  
        return (this.tangence < point.tangence ) ? -1: (this.tangence > point.tangence ) ? 1:0 ;  
       }
	
	public Point() {
	}
	
	public Point clone() {
		Point p = new Point(this.getX(), this.getY(), this.getKey());
		return p;
	}
	public void print() {
		System.out.println(x + " хуй " + y + " два хуя " + key);
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
