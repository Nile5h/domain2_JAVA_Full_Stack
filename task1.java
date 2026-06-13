import java.lang.reflect.Array;
import java.util.Arrays;

class task1 {

    public static void program1(){
        System.out.println("Hello World!! JDKsetupsuccessful.");
    }

    public static void  tempconv(){
        double fahrenheit = 98.6;
        System.out.println("temereature conversion of " + fahrenheit + " deg f into celsius.");
        double celsius = (fahrenheit - 32) * 5/9;
        System.out.printf("%.2f deg F = %.2f deg C \n", fahrenheit, celsius);
    }

    public static void EVENODD(){
        int num = 23;
        System.out.println(" Checking if the number os even or odd.");
        System.out.println( (num & 1) !=0 ? "odd" : "Even");
    }

    public static void multiplicationTable() {
        int num = 5;
        System.out.println("Multiplication table of " + num);
        for(int i=1; i<=10; i++){
            System.out.printf("%d X %d = %d\n",num, i, num*i);
        }
    }

    public static void digitcount(){
        int numb = 123456;
        int count = 0;
        System.out.println("Counting the number of digits in the given "+ numb);

        while (numb !=0) {
            numb = numb / 10;
            count ++;
        }
        System.out.println("the muber has " + count + " digits.");
    }

    public static void fibonacciSeries( ) {
        System.out.println("Creating a FibonacciSeries till 10");
        String fibnum = "";
        for(int i=1; i<=10; i++){
            int sum = (i-1) + i ;
            fibnum += sum + " ";
        }
        System.out.println("The fibonaciSeries is :" + fibnum);
    }

    public static void isPrime() {
        int num = 29;
        boolean prime = false;
        System.out.println("checking the numer is prime or not.");

        for( int i=2; i<=Math.sqrt(num); i++){
            if(num%i ==0 ){
                prime = true;
                break;
            }
        }
        System.out.println( prime ? "it is prime.": "It is not a Prime.");
    }

    public static void dayfinder(){
        int day = 3;
        System.out.println("Finding the day.");

        switch (day) {
            case 1 : System.out.println("Monday!");
                break;
            case 2: System.out.println("Tuesday");
                break;
            case 3: System.out.println("Wedday");
                break;
            case 4: System.out.println("Thusday");
                break;
            case 5: System.out.println("Friday");
                break;
            case 6: System.out.println("Satday");
                break;
        
            default: System.out.println("Invalis day");
                break;
        }
    }

    public static void ArrayOperations(){
        int[] numbers = {3,45,6,7,37};

        int sum = 0;
         for(int a : numbers){
            sum += a;
         }
         System.out.println("the sum of elements of arrray " + Arrays.toString(numbers) + " is :\n" + sum);
    }

    public static void CircleOperations(){
        double radius = 7.5;

        System.out.println("Circumference is : " + calcualteCircum(radius));
        System.out.println("Area is : " + calculateArea(radius));
    }

    public static double calcualteCircum(double radius) {
        return 2 * Math.PI * radius;
    }

    public static double calculateArea(double r){
        return Math.PI * r * r;
    }

    public static void main(String[] args) {
        program1();
        tempconv();
        EVENODD();
        multiplicationTable();
        digitcount();
        fibonacciSeries();
        isPrime();
        dayfinder();
        ArrayOperations();
        CircleOperations();
    }
}