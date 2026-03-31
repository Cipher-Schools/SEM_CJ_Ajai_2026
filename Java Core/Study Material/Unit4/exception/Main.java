package exception;

public class Main {

	public static void main(String[] args) {
	
	try{
	String s ="abc";
	int b= Integer.parseInt(s);
// 	int a=10/0;
	}
	catch(Exception e){
	    System.out.println(e);
	    System.out.println("excuted 1");
	}
	finally{
	    System.out.print("hello");
	}

	
	}
}
