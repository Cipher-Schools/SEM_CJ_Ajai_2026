public class Main
{
    public static void check(int a){
        if(a<18){
            throw new ArithmeticException("this is 1st");
        }else{
            throw new ArrayIndexOutOfBoundsException("this is 2nd");
        }
    }
	public static void main(String[] args) {
// 		System.out.println("Hello World");
      
      try{
          check(12);
      }
      catch(Exception e){
          System.out.println(e);
      }
      finally{
          System.out.println("finally");
      }

	}
}





















//  class Demo implements AutoCloseable{
    
//     public void open(){
//     System.out.println("open");
//     }

//     public void close(){ //override
//         System.out.println("Closed");
//     }
// }
// public class Main
// {
// 	public static void main(String[] args) {
// 	 try(Demo o = new Demo()){
//        o.open();
//      }
//      finally{
//         System.out.println("name");
//      }
//     //  o.close();
// 	}
// }


















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
