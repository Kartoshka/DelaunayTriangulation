class Point implements Comparable{
double x,y;

Point(double i,double j){
x=i;
y=j;
}
Point(Point t)
{
	this.x=t.x;
	this.y=t.y;
	}
public int compareTo(Object p)
{ 
  if (this.x == ((Point)p).x && this.y ==((Point)p).y)
    return 0;
  else if(this.x <((Point)p).x)
	  return-1;
  else 
	  return 1;
  
}

boolean equalTo(Point p)
{
  return this.x== p.x && this.y == p.y;
}
}