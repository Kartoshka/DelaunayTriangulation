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
  //Locate 
  return Math.pow((target.x-xo),2) +Math.pow((target.y-yo),2)<Math.pow(radius,2)?true:false;
}
}