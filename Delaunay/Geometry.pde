class Geometry{
double angleBetween(Point p1, Point p2, Point p3)
  {
  //Using dot product formula a.b = |a||b|sin(theta)
  //First vector
  Point v1 = new Point((p2.x-p1.x),(p2.y-p1.y));
  //Second vector
  Point v2 = new Point((p3.x-p2.x),(p3.y-p2.y));
  
  int dP = v1.x *v2.x +v1.y*v2.y;
  double v1Norm= Math.sqrt(v1.x*v1.y +v1.x*v1.x);
  double v2Norm = Math.sqrt(v2.x*v2.y +v2.x*v2.x);
  
  return Math.asin(dP/(v1Norm*v2Norm)); 
  }
boolean isInCircumCircle(Point p1, Point p2, Point p3, Point target)
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
 radius = Math.sqrt(bx*bx+by*by-4*a*c)/(2*Math.abs(a));
  //Locate 
  return Math.pow((target.x-xo),2) +Math.pow((target.y-yo),2)<Math.pow(radius,2)?true:false;
}

double[] circumcircleCoords(Point p1, Point p2, Point p3)
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
}