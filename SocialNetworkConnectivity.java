public class SocialNetworkConnectivity
{
  private int[] id;
  private int[] sz;
  private int count;
//count represents the number of connected components
  SocialNetworkConnectivity(int N)
  {
    count = N;
    id = new int[N];
    sz = new int[N];
    for(int i=0;i<N;i++)
    {
      id[i] = i;
      sz[i] = 1;
    }
  }

  private int root(int i)
  {
    // chase parent pointers until you reach the root

    while(id[i] != i)
    {
      id[i] = id[id[i]];
      i = id[i];
    }
    return i;
  }

  public int count()
  {
    return count;
  }
  public boolean connected(int p, int q)
  {
    //check if p and q have the same root
    return root(p) == root(q);
  }

  public void union(int p, int q)
  {

    int rootp = root(p);
    int rootq = root(q);

    if(rootp == rootq) return;

// if p's tree is smaller than q's tree, then make p's tree point to q's tree's root
// and increase q's trees' size by p's tree
// else make q's tree point to p's tree's root and increase p's tree's size by q's tree


// whenever the union happens, the number of connected components decreases by 1
    if(sz[rootp] <= sz[rootq])
    {
      id[rootp] = rootq;
      sz[rootq] = sz[rootq] + sz[rootp];
    }
    else
    {
      id[rootq] = rootp;
      sz[rootp] = sz[rootp] + sz[rootq];
    }
    // whenever a union happens, the number of connected components decreases by 1
    count--;
  }

  public static void main(String[] args) {

    int N = StdIn.readInt();
    SocialNetworkConnectivity wquufpc = new SocialNetworkConnectivity(N);
    while(!StdIn.isEmpty())
    {
      int time = StdIn.readInt();
      int p = StdIn.readInt();
      int q = StdIn.readInt();
      if(!wquufpc.connected(p, q))
      {
        wquufpc.union(p, q);
        StdOut.println(p + " and " + q + " are now friends.");
        StdOut.println(wquufpc.count());
        // if count = 1, meaning only 1 connected component is there, means every member just got connected
        if(wquufpc.count() == 1)
        {
          StdOut.println("All members are connected for the first time at: " + time + "hrs.");
          return;
        }

      }
    }
  }
}
