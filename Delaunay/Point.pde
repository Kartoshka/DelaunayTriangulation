class Point implements Comparable{
int x,y;
ArrayList<Point> edges = new ArrayList<Point>();

Point(int i,int j){
x=i;
y=j;
}
int compareTo(Object p)
{
  if(this.x >((Point)p).x)
    return 1;
  else if(this.x<((Point)p).x)
    return -1;
  else 
    return 0;
}
}