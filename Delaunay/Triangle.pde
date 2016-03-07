public class Triangle{
  Point p1;
  Point p2;
  Point p3;
  
public Triangle(Point p1, Point p2, Point p3)
{
  this.p1=p1;
  this.p2=p2;
  this.p3=p3;
}

public Triangle(int w, int h) //Super triangle
{
  double angleA = Math.atan(w/h);
  double angleB = Math.atan(h/w);
  
  
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
  double[] coords = getCircumCircle();
  return Math.pow(target.x-coords[0],2)+Math.pow(target.y-coords[1],2)<=Math.pow(coords[2],2);
}

}