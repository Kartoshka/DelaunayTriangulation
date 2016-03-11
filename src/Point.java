class Point implements Comparable<Point>{
double x,y;
int RGB;

Point(double i,double j, int color){
x=i;
y=j;
RGB=color;
}
Point(Point t)
{
	this.x=t.x;
	this.y=t.y;
	}
public int compareTo(Point p)
{ 
  if(this.x<p.x)
	  return -1;
  if(this.x>p.x)
	  return 1;
  return 0;
  
}

boolean equalTo(Point p)
{
  return this.x== p.x && this.y == p.y;
}
}