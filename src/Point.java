class Point implements Comparable{
double x,y;

Point(double i,double j){
x=i;
y=j;
}

public int compareTo(Object p)
{
  if(this.x >((Point)p).x)
    return 1;
  else if(this.x<((Point)p).x)
    return -1;
  else 
    return 0;
}

boolean equalTo(Point p)
{
  return this.x== p.x && this.y == p.y;
}
}