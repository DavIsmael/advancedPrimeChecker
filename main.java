import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int startingPoint = 0;
        int endingPoint = 0;
        boolean isPrime = false;
        ArrayList<Integer> rangeOfNumbers = new ArrayList<>();
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        ArrayList<Integer> compositeNumbers = new ArrayList<>();
        ArrayList<Integer> norPrimeNorComposite = new ArrayList<>();
        try{
            System.out.println("Select a range of numbers to check whether they are composite numbers or prime numbers. It can check up to 2.147.483.647, since that's the range of an Int.");
            System.out.print("Starting Point: ");
            startingPoint = scanner.nextInt();
            System.out.print("Ending Point: ");
            endingPoint = scanner.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Range of an Int exceeded :o " + e);
            System.exit(1);
        }
        if(endingPoint >= startingPoint){
            for(int i = startingPoint; i <= endingPoint; ++i){
                rangeOfNumbers.add(i);
            }
        }else{
            for(int i = startingPoint; i >= endingPoint; --i){
                rangeOfNumbers.add(i);
            }
        }
        outerLoop: for(int i = 0; i < rangeOfNumbers.size(); ++i){
            if(rangeOfNumbers.get(i) <= 1){
                norPrimeNorComposite.add(rangeOfNumbers.get(i));
            }
            if(rangeOfNumbers.get(i) == 2 || rangeOfNumbers.get(i) == 3){
                primeNumbers.add(rangeOfNumbers.get(i));
            }
            //Checks up to square root of 'number' to optimize 'for loop' by reducing the number of iterations
            innerLoop: for(long j = 2; j <= rangeOfNumbers.get(i) / j; ++j){
                isPrime = true;
                //Division remainder has to be 0 to be a composite number
                if(rangeOfNumbers.get(i) % j == 0){
                    compositeNumbers.add(rangeOfNumbers.get(i));
                    isPrime = false;
                    break innerLoop;
                }
            }
            if(isPrime){
                primeNumbers.add(rangeOfNumbers.get(i));
            }
        }
        //Getting last index from composite numbers
        int compositeLastIndex = compositeNumbers.size() - 1;
        System.out.print("Printing out composite numbers: ");
        for(int i = 0; i < compositeNumbers.size(); ++i){
            if(i == compositeLastIndex){
                System.out.println(compositeNumbers.get(i) + ".");
            }else{
                System.out.print(compositeNumbers.get(i) + ", ");
            }
        }
        //Getting last index from prime numbers
        int primeLastIndex = primeNumbers.size() - 1;
        System.out.print("Printing out prime numbers: ");
        for(int i = 0; i < primeNumbers.size(); ++i){
            if(i == primeLastIndex){
                System.out.println(primeNumbers.get(i) + ".");
            }else{
                System.out.print(primeNumbers.get(i) + ", ");
            }
        }
        //Getting last index from norPrimeNorComposite numbers
        int norPrimeNorCompositeLastIndex = norPrimeNorComposite.size() - 1;
        System.out.print("Printing out nor composite nor prime numbers: ");
        for(int i = 0; i < norPrimeNorComposite.size(); ++i){
            if(i == norPrimeNorCompositeLastIndex){
                System.out.println(norPrimeNorComposite.get(i) + ".");
            }else{
                System.out.print(norPrimeNorComposite.get(i) + ", ");
            }
        }
    }
}