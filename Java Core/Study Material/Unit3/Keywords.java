class Animal{
    
    static int count = 0; //shared by all the objects
    final String planet = "Earth";//cannot change 
    Animal(){
        count++;
    }
    void sound(){
        System.out.print("Animal makes sound");
    }
    
}
class Dog extends Animal{
    
    String name;
    
    Dog(){
        super();//calls parent constructor
        // this.name= name;
    }
    @Override
    void sound(){
       super.sound(); // calls method of parent
       System.out.println("dog barks");
    }
    
}

public class Keywords
{
	public static void main(String[] args) {
		Dog d1 = new Dog();
		d1.sound();
		System.out.println("total animal:"+Animal.count); //static usage
		
		Object obj = d1;
		System.out.println(obj.toString());
	    System.out.println("total animal:"+Animal.count); //static usage
		
	}
}