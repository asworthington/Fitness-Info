import java.util.Scanner;

public class FitnessInfo {
    public static void main(String[] args) 
    {
        // welcome message
        System.out.println("-----------------------------------");
        System.out.println("Welcome to FitnessInfo.org");
        System.out.println("---------------------------------");

        Scanner scan = new Scanner(System.in);

        // user input
        int gender = getGender(scan);
        int age = getAge(scan);
        int activity = getActivityLevel(scan);
        double heightInInches = getHeight(scan);
        double weightInLbs = getWeight(scan);

        // calculations and conversions
        double activityFactor = getActivityFactor(activity);
        double tdee = calculateAndDisplayInfo(age, gender, weightInLbs, heightInInches, activityFactor);

        // user choice for weight loss/gain
        int choice = getUserChoice(scan);
        displayCalorieInfo(tdee, choice);

        // goodbye message
        System.out.println("-----------------------------------");
        System.out.println("Thank you for using FitnessInfo.org");
        System.out.println("-----------------------------------");

        scan.close();
    }

    public static int getGender(Scanner scan) 
    {
        int gender;
        do {
            System.out.print("Please enter your gender. Type 1 for men, 2 for women: ");
            gender = scan.nextInt();
        } while (gender < 1 || gender > 2);
        return gender;
    }

    public static int getAge(Scanner scan) 
    {
        int age;
        do {
            System.out.print("Please enter your age: ");
            age = scan.nextInt();
        } while (age <= 0);
        return age;
    }

    public static int getActivityLevel(Scanner scan) 
    {
        int activity;
        do {
            System.out.print("Please enter your activity level. Type 1 for sedentary, 2 for slightly active,\n");
            System.out.print("3 for moderately active, 4 for very active, and 5 for extremely active: ");
            activity = scan.nextInt();
        } while (activity < 1 || activity > 5);
        return activity;
    }

    public static double getHeight(Scanner scan) 
    {
        double heightInInches;
        do {
            System.out.print("Please enter your height in inches: ");
            heightInInches = scan.nextDouble();
        } while (heightInInches <= 0);
        return heightInInches;
    }

    public static double getWeight(Scanner scan) 
    {
        double weightInLbs;
        do {
            System.out.print("Please enter your weight in lbs: ");
            weightInLbs = scan.nextDouble();
        } while (weightInLbs <= 0);
        return weightInLbs;
    }

    public static double getActivityFactor(int activity)
    {
        switch (activity) 
        {
            case 1: 
                return 1.2;
            case 2: 
                return 1.375;
            case 3: 
                return 1.55;
            case 4: 
                return 1.725;
            case 5: 
                return 1.9;
            default: 
                return 1.0;
        }
    }

    public static double calculateAndDisplayInfo(int age, int gender, double weightInLbs, double heightInInches, double activityFactor) 
    {
        // unit conversions
        double weightInKg = weightInLbs / 2.2;
        double heightInCm = heightInInches * 2.54;
        double heightInM = heightInCm / 100;

        // body mass index calculation
        double bmi = weightInKg / Math.pow(heightInM, 2); 

        double bmr;
        if (gender == 1) // bmr formula for men
        {
            bmr = (10 * weightInKg) + (6.25 * heightInCm) - (5 * age) + 5;
        } 
        else // bmr formula for women
        { 
            bmr = (10 * weightInKg) + (6.25 * heightInCm) - (5 * age) - 161;
        }

        // tdee formula
        double tdee = bmr * activityFactor; 

        System.out.println("-----------------------------------"); // info screen
        System.out.println("User Info: ");
        System.out.printf("Body Mass Index (BMI): %.1f", bmi);
        if (bmi < 18.5) {
            System.out.println(" (Underweight range)");
        } else if (bmi >= 18.5 && bmi < 25) {
            System.out.println(" (Normal weight range)");
        } else if (bmi >= 25 && bmi < 30) {
            System.out.println(" (Overweight range)");
        } else {
            System.out.println(" (Obesity range)");
        }
        System.out.println("Basal Metabolic Rate (BMR): " + Math.round(bmr) + " calories");
        System.out.println("Total Daily Energy Expenditure (TDEE): " + Math.round(tdee) + " calories");
        System.out.println("-----------------------------------");

        return tdee;
    }

    public static int getUserChoice(Scanner scan) 
    {
        int choice;
        do {
            System.out.print("Enter 1 for weight loss info, 2 for weight gain info: ");
            choice = scan.nextInt();
        } while (choice < 1 || choice > 2);
        return choice;
    }

    public static void displayCalorieInfo(double tdee, int choice) 
    {
        System.out.println("-----------------------------------"); // results screen
        System.out.println("Results:");
        System.out.println("Maintain weight: " + Math.round(tdee) + " calories/day");

        if (choice == 1) { // weight loss
            displayWeightLossInfo(tdee);
        } else {
            displayWeightGainInfo(tdee);
        }
    }

    public static void displayWeightLossInfo(double tdee) 
    {
        double mildWeightLoss = tdee - 250;
        double weightLoss = tdee - 500;
        double extremeWeightLoss = tdee - 1000;
        System.out.println("Mild weight loss (0.5 lb/week): " + Math.round(mildWeightLoss) + " calories/day");
        System.out.println("Weight loss (1 lb/week): " + Math.round(weightLoss) + " calories/day");
        System.out.println("Extreme weight loss (2 lb/week): " + Math.round(extremeWeightLoss) + " calories/day");
    }

    public static void displayWeightGainInfo(double tdee) 
    {
        double mildWeightGain = tdee + 250;
        double weightGain = tdee + 500;
        double extremeWeightGain = tdee + 1000;
        System.out.println("Mild weight gain (0.5 lb/week): " + Math.round(mildWeightGain) + " calories/day");
        System.out.println("Weight gain (1 lb/week): " + Math.round(weightGain) + " calories/day");
        System.out.println("Extreme weight gain (2 lb/week): " + Math.round(extremeWeightGain) + " calories/day");
    }
}