public class Triangle{
	  
Point p1;
Point p2;
Point p3;
  
public static double EPSILON = 0.000001;
  
public Triangle(Point p1, Point p2, Point p3)
{
  this.p1=p1;
  this.p2=p2;
  this.p3=p3;
}

public Triangle()
{
	p1=null;
	p2=null;
	p3=null;
}
boolean CircumCircle(Point target)
  {
    Triangle t = this;
    double x1 =t.p1.x; double y1 =t.p1.y;
    double x2 =t.p2.x; double y2=t.p2.y;
    double x3=t.p3.x; double y3=t.p3.y;
    double xp = target.x; double yp=target.y;
    double m1,m2,mx1,mx2,my1,my2;
    double dx,dy,rsqr,drsqr;
    double xc, yc, r;
    
    /* Check for coincident points */
    if ( Math.abs(y1-y2) < EPSILON && Math.abs(y2-y3) < EPSILON )
    {
      System.out.println("CircumCircle: Points are coincident.");
      return false;
    }
    
    if ( Math.abs(y2-y1) < EPSILON )
    {
      m2 = - (x3-x2) / (y3-y2);
      mx2 = (x2 + x3) / 2.0;
      my2 = (y2 + y3) / 2.0;
      xc = (x2 + x1) / 2.0;
      yc = m2 * (xc - mx2) + my2;
    }
    else if ( Math.abs(y3-y2) < EPSILON )
    {
      m1 = - (x2-x1) / (y2-y1);
      mx1 = (x1 + x2) / 2.0;
      my1 = (y1 + y2) / 2.0;
      xc = (x3 + x2) / 2.0;
      yc = m1 * (xc - mx1) + my1;  
    }
    else
    {
      m1 = - (x2-x1) / (y2-y1);
      m2 = - (x3-x2) / (y3-y2);
      mx1 = (x1 + x2) / 2.0;
      mx2 = (x2 + x3) / 2.0;
      my1 = (y1 + y2) / 2.0;
      my2 = (y2 + y3) / 2.0;
      xc = (m1 * mx1 - m2 * mx2 + my2 - my1) / (m1 - m2);
      yc = m1 * (xc - mx1) + my1;
    }
    
    dx = x2 - xc;
    dy = y2 - yc;
    rsqr = dx*dx + dy*dy;
    r = Math.sqrt(rsqr);
    
    dx = xp - xc;
    dy = yp - yc;
    drsqr = dx*dx + dy*dy;

    return drsqr <= rsqr;
  }

boolean sharesVertex(Triangle t)
{
  return this.p1.equalTo(t.p1) || this.p1.equalTo(t.p2) || this.p1.equalTo(t.p3) || 
		 this.p2.equalTo(t.p1) || this.p2.equalTo(t.p2) || this.p2.equalTo(t.p3) || 
		 this.p3.equalTo(t.p1) || this.p3.equalTo(t.p2) || this.p3.equalTo(t.p3);
}

}