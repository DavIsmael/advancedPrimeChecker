import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int startingPoint = 0;
        int endingPoint = 0;
        int numbers = 0;
        long singleNumber = 0;
        boolean isPrime = false;
        boolean isSinglePrime = true;
        boolean isSelectedPrime = false;
        String checkSingleNumber = null;
        String checkRangeOfNumbers = null;
        String checkSelectedNumbers = null;
        ArrayList<Integer> rangeOfNumbers = new ArrayList<>();
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        ArrayList<Integer> compositeNumbers = new ArrayList<>();
        ArrayList<Integer> norPrimeNorComposite = new ArrayList<>();
        ArrayList<Integer> selectedNumbers = new ArrayList<>();
        ArrayList<Integer> selectedCompositeNumbers = new ArrayList<>();
        ArrayList<Integer> selectedPrimeNumbers = new ArrayList<>();
        ArrayList<Integer> selectedNorPrimeNorComposite = new ArrayList<>();
        try{
            System.out.println("This program allows you to check if a number is prime or not by either going into the range of numbers approach, selected numbers approach or the single number approach");
            System.out.print("Do you want to check selected numbers? Type either yes or no ");
            checkSelectedNumbers = scanner.next();
            if(checkSelectedNumbers.equalsIgnoreCase("yes")){
                System.out.print("How many numbers do you wanna check? ");
                numbers = scanner.nextInt();
                for(int i = 1; i <= numbers; ++i){
                    System.out.print("Choose selected numbers up to 2.147.483.647 ");
                    selectedNumbers.add(scanner.nextInt());
                }
                System.out.print("Do you wanna check more numbers? Type either yes or no ");
                String checkMoreNumbers = scanner.next();
                if(checkMoreNumbers.equalsIgnoreCase("yes")){
                    System.out.print("How many more numbers do you wanna check? ");
                    numbers = scanner.nextInt();
                    for(int i = 1; i <= numbers; ++i){
                        System.out.print("Choose selected numbers up to 2.147.483.647 ");
                        selectedNumbers.add(scanner.nextInt());
                    }
                }
            }
            System.out.print("Do you want to also check for range of numbers? Type either yes or no ");
            checkRangeOfNumbers = scanner.next();
            if(checkRangeOfNumbers.equalsIgnoreCase("yes")){
                System.out.println("Select a range of numbers to check whether they are composite numbers or prime numbers. It can check up to 2.147.483.647, since that's the range of an Int.");
                System.out.print("Starting Point: ");
                startingPoint = scanner.nextInt();
                System.out.print("Ending Point: ");
                endingPoint = scanner.nextInt();
            }
            System.out.print("Do you want to also check for a single value? Type either yes or no ");
            checkSingleNumber = scanner.next();
            if(checkSingleNumber.equalsIgnoreCase("yes")){
                System.out.print("Select a single number. It can check up to 9.223.372.036.854.775.807: ");
                singleNumber = scanner.nextInt();
            }
        }catch(InputMismatchException e){
            System.out.println("Range of an Int or Long exceeded :o " + e);
            System.exit(1);
        }
        if(checkSingleNumber.equalsIgnoreCase("yes")){
            if(singleNumber < 2) isSinglePrime = false;
            else isSinglePrime = true;
            for(int i = 2; i <= singleNumber / i; ++i){
                if(singleNumber % i == 0){
                    isSinglePrime = false;
                    break;
                }
            }
            if(isSinglePrime){
                System.out.println(singleNumber + " is prime!");
            }else if(singleNumber <= 1){
                System.out.println(singleNumber + " isnt composite nor prime!");
            }else{
                System.out.println(singleNumber + " is a composite number!");
            }
        }
        outerLoop: for(int i = 0; i < selectedNumbers.size(); ++i){
            if(selectedNumbers.get(i) <= 1){
                selectedNorPrimeNorComposite.add(selectedNumbers.get(i));
            }
            if(selectedNumbers.get(i) == 2 || selectedNumbers.get(i) == 3){
                selectedPrimeNumbers.add(selectedNumbers.get(i));
            }
            //Checks up to square root of 'number' to optimize 'for loop' by reducing the number of iterations
            innerLoop: for(int j = 2; j <= selectedNumbers.get(i) / j; ++j){
                isSelectedPrime = true;
                //Division remainder has to be 0 to be a composite number
                if(selectedNumbers.get(i) % j == 0){
                    selectedCompositeNumbers.add(selectedNumbers.get(i));
                    isSelectedPrime = false;
                    break innerLoop;
                }
            }
            if(isSelectedPrime){
                selectedPrimeNumbers.add(selectedNumbers.get(i));
            }
            isSelectedPrime = false;
        }
        if(checkRangeOfNumbers.equalsIgnoreCase("yes")) {
            if (endingPoint >= startingPoint) {
                for (int i = startingPoint; i <= endingPoint; ++i) {
                    rangeOfNumbers.add(i);
                }
            } else {
                for (int i = startingPoint; i >= endingPoint; --i) {
                    rangeOfNumbers.add(i);
                }
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
        if(compositeNumbers.size() != 0){
            //Getting last index from composite numbers
            int compositeLastIndex = compositeNumbers.size() - 1;
            System.out.print("Printing out composite numbers: ");
            for(int i = 0; i < compositeNumbers.size(); ++i) {
                if(i == compositeLastIndex){
                    System.out.println(compositeNumbers.get(i) + ".");
                }else{
                    System.out.print(compositeNumbers.get(i) + ", ");
                }
            }
        }
        if(primeNumbers.size() != 0) {
            //Getting last index from prime numbers
            int primeLastIndex = primeNumbers.size() - 1;
            System.out.print("Printing out prime numbers: ");
            for (int i = 0; i < primeNumbers.size(); ++i) {
                if (i == primeLastIndex) {
                    System.out.println(primeNumbers.get(i) + ".");
                } else {
                    System.out.print(primeNumbers.get(i) + ", ");
                }
            }
        }
        if(norPrimeNorComposite.size() != 0) {
            //Getting last index from norPrimeNorComposite numbers
            int norPrimeNorCompositeLastIndex = norPrimeNorComposite.size() - 1;
            System.out.print("Printing out nor composite nor prime numbers: ");
            for (int i = 0; i < norPrimeNorComposite.size(); ++i) {
                if (i == norPrimeNorCompositeLastIndex) {
                    System.out.println(norPrimeNorComposite.get(i) + ".");
                } else {
                    System.out.print(norPrimeNorComposite.get(i) + ", ");
                }
            }
        }
        if(selectedCompositeNumbers.size() != 0) {
            //Getting last index from selected composite numbers
            int selectedCompositeLastIndex = selectedCompositeNumbers.size() - 1;
            System.out.print("Printing out selected composite numbers: ");
            for (int i = 0; i < selectedCompositeNumbers.size(); ++i) {
                if (i == selectedCompositeLastIndex) {
                    System.out.println(selectedCompositeNumbers.get(i) + ".");
                } else {
                    System.out.print(selectedCompositeNumbers.get(i) + ", ");
                }
            }
        }
        if(selectedPrimeNumbers.size() != 0) {
            //Getting last index from selected prime numbers
            int selectedPrimeLastIndex = selectedPrimeNumbers.size() - 1;
            System.out.print("Printing out selected prime numbers: ");
            for (int i = 0; i < selectedPrimeNumbers.size(); ++i) {
                if (i == selectedPrimeLastIndex) {
                    System.out.println(selectedPrimeNumbers.get(i) + ".");
                } else {
                    System.out.print(selectedPrimeNumbers.get(i) + ", ");
                }
            }
        }
        if(selectedNorPrimeNorComposite.size() != 0) {
            //Getting last index from selected nor prime nor composite numbers
            int selectedNorPrimeNorCompositeLastIndex = selectedNorPrimeNorComposite.size() - 1;
            System.out.print("Printing out selected nor prime nor composite numbers: ");
            for (int i = 0; i < selectedNorPrimeNorComposite.size(); ++i) {
                if (i == selectedNorPrimeNorCompositeLastIndex) {
                    System.out.println(selectedNorPrimeNorComposite.get(i) + ".");
                } else {
                    System.out.print(selectedNorPrimeNorComposite.get(i) + ", ");
                }
            }
        }
        scanner.close();
    }
}
//soma
//throw some stuff
//would i then have to do for all of em?