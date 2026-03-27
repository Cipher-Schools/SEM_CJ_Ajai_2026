@FunctionalInterface
interface Demo 
{
	void dis(int i, int j);
}

// class Demo2 implements Demo{
//   @Override
//   public void dis(){
//     System.out.println("hiii");
//   }
// }
public class Main {
    public static void main(String[] args)
    {
     Demo o = (w,r)->{
      System.out.println("hi"+w+r);
     };
     o.dis(1,4);
    }
}






















// // Anonymous Inner class
// @FunctionalInterface
// interface Demo 
// {
// 	void dis();
// }

// class Main {
//     public static void main(String[] args)
//     {
//       Demo o=new Demo()
//     		  {
//     	  		public void dis()
//     	  		{
//     	  			System.out.println("I Love Java");
//     	  		}
//     		  };
//       o.dis();
//     }
// }



// // @FunctionalInterface
// interface Demo 
// {
// 	void dis();
// }

// class Main {
//     public static void main(String[] args)
//     {
//       Demo o=() ->{System.out.println("I Love Java"); };
//       o.dis();
//     }
// }
