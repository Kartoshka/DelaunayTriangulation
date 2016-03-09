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

public Triangle(double w, double h) //Super triangle
{
  double angleA = Math.atan(w/h);
  double angleB = Math.atan(h/w);  
  double H = Math.sqrt(w*w+h*h);
  double A = H/Math.sin(angleA);
  double B= H/Math.sin(angleB);
  
  p1 = new Point(0,0);
  p2 = new Point(B,0);
  p3 = new Point(0,A);
  
}
double[] getCircumCircle()
{
  //equation of a circle of the form (x-xo)^2 +(y-y0)^2 =radius^2
  double xo, yo, radius;
  //See wolframalpha article on circumcircle
  xo=0;
  yo=0;
  radius=0;
  //Calculate circumcircle
  double a,bx,by,c;
  a= p1.x*(p2.y-p3.y)-p1.y*(p2.x-p3.x)+p2.x*p3.y-p3.x*p2.y;
  double sxy1s, sxy2s, sxy3s;
  sxy1s = p1.x*p1.x +p1.y*p1.y;
  sxy2s =p2.x*p2.x +p2.y*p2.y;
  sxy3s= p3.x*p3.x +p3.y*p3.y;
  bx =-sxy1s*(p2.y-p3.y)+p1.y*(sxy2s-sxy3s)-(sxy2s*p3.y-sxy3s*p2.y);
  by=sxy1s*(p2.x-p3.x)-p1.x*(sxy2s-sxy3s)+(sxy2s*p3.x-sxy3s*p2.x);
  c=-(sxy1s*(p2.x*p3.y-p2.y*p3.x)-p1.x*(sxy2s*p3.y-sxy3s*p2.y)+p1.y*(sxy2s*p3.x-sxy3s*p2.x));
 
 xo=-1*bx/(2*a);
 yo=-1*by/(2*a);
 radius = Math.sqrt(bx*bx+by*by-4*a*c)/(Math.abs(a));
 double[] coords = new double[3];
 coords[0]=xo;
 coords[1]=yo;
 coords[2]=radius;
 return coords;
}

boolean isInCircumCircle(Point target)
{
  double[] coords = this.getCircumCircle();
  boolean condition= Math.pow(target.x-coords[0],2)+Math.pow(target.y-coords[1],2)<=Math.pow(coords[2],2);

  return condition;
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

    return ( drsqr <= rsqr ? true : false );
  }
boolean sharesVertex(Triangle t)
{
  return this.p1.equalTo(t.p1) || this.p1.equalTo(t.p2) || this.p1.equalTo(t.p3) || this.p2.equalTo(t.p1)|| this.p2.equalTo(t.p2)||this.p2.equalTo(t.p3) || this.p3.equalTo(t.p1)|| this.p3.equalTo(t.p2)||this.p3.equalTo(t.p3);
}

}