class payment{
    String paymentId;
    double amount;

void makePayment(double amount){
    System.out.println("This is 1st method");

    System.out.println("Amount: "+amount);
    
}
void makePayment(double amount,String paymentId){
    System.out.println("This is 2nd method");
    System.out.println("Amount: "+amount);
    System.out.println("Payment Id: "+paymentId);
  

}
void makePayment(String cardnumber,double amount){
    System.out.println("This is 3rd method");
    System.out.println("Card Number: "+cardnumber);
    System.out.println("Amount: "+amount);
 

}

}

public class q2 {
    public static void main(String args[]){
        payment p =new payment();
   
        p.makePayment(1000);
        p.makePayment(10000,"1234");
        p.makePayment("123456789",10000);

       

    }
    
}
