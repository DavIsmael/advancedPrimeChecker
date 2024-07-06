import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        int startingPoint = 0;
        int endingPoint = 0;
        int numbers = 0;
        long totalSum = 0;
        long singleNumber = 0;
        boolean isPrime = false;
        boolean isSinglePrime = true;
        boolean isSelectedPrime = false;
        String checkSingleNumber = null;
        String checkRangeOfNumbers = null;
        String checkSelectedNumbers = null;
        String folderPath = "outputDir";
        String primeNumbersFilePath = folderPath + File.separator + "primeNumbers.json";
        String compositeNumbersFilePath = folderPath + File.separator + "compositeNumbers.json";
        String norPrimeNorCompositeFilePath = folderPath + File.separator + "norPrimeNorComposite.json";
        String selectedPrimeNumbersFilePath = folderPath + File.separator + "selectedPrimeNumbers.json";
        String selectedCompositeNumbersFilePath = folderPath + File.separator + "selectedCompositeNumbers.json";
        String selectedNorPrimeNorCompositeFilePath = folderPath + File.separator + "selectedNorPrimeNorComposite.json";
        ArrayList<Integer> rangeOfNumbers = new ArrayList<>();
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        ArrayList<Integer> compositeNumbers = new ArrayList<>();
        ArrayList<Integer> norPrimeNorComposite = new ArrayList<>();
        ArrayList<Integer> selectedNumbers = new ArrayList<>();
        ArrayList<Integer> selectedCompositeNumbers = new ArrayList<>();
        ArrayList<Integer> selectedPrimeNumbers = new ArrayList<>();
        ArrayList<Integer> selectedNorPrimeNorComposite = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        File folder = new File(folderPath);
        File primeNumbersJson = new File(primeNumbersFilePath);
        File compositeNumbersJson = new File(compositeNumbersFilePath);
        File norPrimeNorCompositeNumbersJson = new File(norPrimeNorCompositeFilePath);
        File selectedPrimeNumbersJson = new File(selectedPrimeNumbersFilePath);
        File selectedCompositeNumbersJson = new File(selectedCompositeNumbersFilePath);
        File selectedNorPrimeNorCompositeNumbersJson = new File(selectedNorPrimeNorCompositeFilePath);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
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
                System.out.println(singleNumber + " isn't composite nor prime!");
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
                totalSum += compositeNumbers.get(i);
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
                totalSum += primeNumbers.get(i);
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
                totalSum += norPrimeNorComposite.get(i);
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
                totalSum += selectedCompositeNumbers.get(i);
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
                totalSum += selectedPrimeNumbers.get(i);
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
                totalSum += selectedNorPrimeNorComposite.get(i);
            }
        }
        System.out.print("Total sum of the numbers you have provided: " + totalSum);
        scanner.close();
        if(!folder.exists()){
            folder.mkdirs();
        }
        try{
            primeNumbersJson.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            compositeNumbersJson.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            norPrimeNorCompositeNumbersJson.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            selectedPrimeNumbersJson.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            selectedCompositeNumbersJson.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            selectedNorPrimeNorCompositeNumbersJson.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
        try(FileWriter writer = new FileWriter(primeNumbersFilePath)){
            gson.toJson(primeNumbers, writer);
        }catch(IOException e){
            e.printStackTrace();
        }
        try(FileWriter writer = new FileWriter(compositeNumbersFilePath)){
            gson.toJson(compositeNumbers, writer);
        }catch(IOException e){
            e.printStackTrace();
        }
        try(FileWriter writer = new FileWriter(norPrimeNorCompositeFilePath)){
            gson.toJson(norPrimeNorComposite, writer);
        }catch(IOException e){
            e.printStackTrace();
        }
        try(FileWriter writer = new FileWriter(selectedPrimeNumbersFilePath)){
            gson.toJson(selectedPrimeNumbers, writer);
        }catch(IOException e){
            e.printStackTrace();
        }
        try(FileWriter writer = new FileWriter(selectedCompositeNumbersFilePath)){
            gson.toJson(selectedCompositeNumbers, writer);
        }catch(IOException e){
            e.printStackTrace();
        }
        try(FileWriter writer = new FileWriter(selectedNorPrimeNorCompositeFilePath)){
            gson.toJson(selectedNorPrimeNorComposite, writer);
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println();
        try {
            String fileContent = Files.readString(Paths.get(primeNumbersFilePath));
            if(!fileContent.trim().equals("[]")){
                System.out.println("Prime Numbers Array was written to corresponding JSON file: " + primeNumbersFilePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String fileContent = Files.readString(Paths.get(compositeNumbersFilePath));
            if(!fileContent.trim().equals("[]")){
                System.out.println("Composite Numbers Array was written to corresponding JSON file: " + compositeNumbersFilePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String fileContent = Files.readString(Paths.get(norPrimeNorCompositeFilePath));
            if(!fileContent.trim().equals("[]")){
                System.out.println("Nor Prime Nor Composite Array of Numbers was written to corresponding JSON file: " + norPrimeNorCompositeFilePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String fileContent = Files.readString(Paths.get(selectedPrimeNumbersFilePath));
            if(!fileContent.trim().equals("[]")){
                System.out.println("Selected Prime Numbers Array was written to corresponding JSON file: " + selectedPrimeNumbersFilePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String fileContent = Files.readString(Paths.get(selectedCompositeNumbersFilePath));
            if(!fileContent.trim().equals("[]")){
                System.out.println("Selected Composite Numbers Array was written to corresponding JSON file: " + selectedCompositeNumbersFilePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            String fileContent = Files.readString(Paths.get(selectedNorPrimeNorCompositeFilePath));
            if(!fileContent.trim().equals("[]")){
                System.out.println("Selected Nor Prime Nor Composite Array of Numbers was written to corresponding JSON file: " + selectedNorPrimeNorCompositeFilePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
