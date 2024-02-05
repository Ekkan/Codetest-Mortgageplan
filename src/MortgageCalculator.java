import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class MortgageCalculator {

    public static double calculateMonthlyPayment(double loanAmount, double annualInterestRate, int termInYears) {
        double monthlyInterestRate = annualInterestRate / 12.0 / 100;
        int totalNumberOfPayments = termInYears * 12;
        return (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -totalNumberOfPayments));
    }

    public static void readAndCalculateMortgage(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (parts.length != 4) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }
                String customerName = parts[0].replace("\"", "");
                double loanAmount = parseDouble(parts[1]);
                double annualInterestRate = parseDouble(parts[2]);
                int termInYears = Integer.parseInt(parts[3].trim());

                double monthlyPayment = calculateMonthlyPayment(loanAmount, annualInterestRate, termInYears);
                System.out.printf("%s wants to borrow %.2f € for a period of %d years and pay %.2f € each month%n",
                        customerName, loanAmount, termInYears, monthlyPayment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double parseDouble(String value) {
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        try {
            Number number = format.parse(value.trim());
            return number.doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void main(String[] args) {
        String filePath = "/Users/erikmalmstrom/Desktop/Codetest-Mortageplan/prospects.txt";
        readAndCalculateMortgage(filePath);
    }
}
