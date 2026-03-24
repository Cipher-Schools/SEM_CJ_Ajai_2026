// q1
// class vehicle{
//     int speed=100;

// }
// class  car extends vehicle{
//     int speed=150;
//     void show(){
//         System.out.println(speed);
//         System.out.println(super.speed);
//     }

// }
// public class q1{
//     public static void main(String[] args){
//         car obj=new car();
//         obj.show();
//     }
// }

// q2
// class person{
//     void display(){
//         System.out.println("Person Details");
//     }
// }
// class student extends person{
//     void display(){
//         super.display();
//         System.out.println("Student Details");
//     }
// }
// public class q1{
//     public static void main(String[] args){
//         student obj=new student();
//         obj.display();
//     }
// }

// q3
// class shape{
//     shape(){
//         System.out.println("Shape created");
//     }

// }
// class circle extends shape{
//     circle(){
//         super();
//         System.out.println("Circle Created");
//     }
// }
// public class q1{
//     public static void main(String[] args){
//         circle obj=new circle();
       
//     }
// }


// Q4.
// What will be the output of the following program?
// 20 and 10
// class A {
// int x = 10;
// }

// class B extends A {
// int x = 20;


// void show() {
//     System.out.println(x);
//     System.out.println(super.x);
// }

// }

// public class q1 {
// public static void main(String[] args) {
//     B obj = new B();
//     obj.show();
// }
// }



// q5
// class Animal{
//     void eat(){
//         System.out.println("eat");
//     }
// }
// class Dog extends Animal{
//     void eat(){
//         super.eat();
//     }
// }
// public class q1{
//     public static void main(String[] args){
//         Dog obj = new Dog();
//         obj.eat();
//     }
// }

// ## PART B – INTERFACE

// q6
// interface Animal{
//     void makeSound();
// }
// class Dog implements Animal{
//     public void makeSound(){
//         System.out.println("Dog Barking");

//     }
// }
// public class q1{
//     public static void main(String[] args){
//         Dog obj=new Dog();
//         obj.makeSound();
//     }
// }

// q7

// import java.util.Scanner;
// interface calculator{
//     int add(int a,int b);
//     int subtract(int a,int b);
// }
// class simplecalculator implements calculator{
//     public int add(int a,int b){
//         return(a+b);
//     }
//     public int subtract(int a,int b){
//         return (a-b);
//     }
// }
// public class q1{
//     public static void main(String[] args){
//         Scanner sc =new Scanner(System.in);
//         int a =sc.nextInt();
//         int b=sc.nextInt();



//         simplecalculator obj=new simplecalculator();
//        System.out.println(obj.add(a,b)); 
//         System.out.println(obj.subtract(a,b));
//     }
// }

// q8
// interface playaable{
//     void play();
// }
// class football implements playaable{
//     public void play(){
//         System.out.println("Football");
//     }
// }
// class cricket implements playaable{
//     public void play(){
//         System.out.println("Cricket");
//     }
// }

// public class q1{
//     public static void main(String[] args){
//         cricket obj1 =new cricket();
//         football obj2=new football();

//         obj1.play();
//         obj2.play();
//     }
// }


// Q9.

// interface camera{
//     void takephoto();
// }
// interface musicplayer{
//     void playmusic();
// }
// class smartphone implements camera,musicplayer{
//     public void takephoto(){
//         System.out.println("take photo");
//     }
//     public void playmusic(){
//         System.out.println("play music");
//     }
// }
// public class q1{
//     public static void main(String[] args){
//         smartphone obj=new smartphone();
//         obj.takephoto();
//         obj.playmusic();
        
//     }
// }

// q10
// import java.util.Scanner;

// interface shape{
//     int area();
// }

// class circle implements shape{
//     int r;

//     circle(int r){
//         this.r = r;
//     }

//     public int area(){
//         return (int)(3.14 * r * r);
//     }
// }

// class rectangle implements shape{
//     int l,b;

//     rectangle(int l,int b){
//         this.l = l;
//         this.b = b;
//     }

//     public int area(){
//         return l * b;
//     }
// }

// public class q1{
//     public static void main(String[] args){

//         Scanner sc = new Scanner(System.in);

//         int r = sc.nextInt();
//         int l = sc.nextInt();
//         int b = sc.nextInt();

//         circle obj = new circle(r);
//         rectangle obj1 = new rectangle(l,b);

//         System.out.println(obj.area());
//         System.out.println(obj1.area());

//         sc.close();
//     }
// }

// q11


interface payment{
    void pay();
}
class creditcardpayment implements payment{
    public void pay(){
        System.out.println("Credit card payment");
    }
}
class upipayment implements payment{
    public void pay(){
        System.out.println("upi payment");
    }
}

class cashpayment implements payment{
    public void pay(){
        System.out.println("Cash payment");
    }
}
public class q1{
    public static void main(){
        creditcardpayment obj1=new creditcardpayment();
        upipayment obj2=new upipayment();
        cashpayment obj3=new cashpayment();
        obj1.pay();
        obj2.pay();
        obj3.pay();
    }
}