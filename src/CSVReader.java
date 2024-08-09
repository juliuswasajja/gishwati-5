
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVReader {

    String path = System.getProperty("../data-store/life-expectancy.csv");
    private Map<String, String> countryLifeExpectancyMap = new HashMap<>();

    // Method to read CSV and populate the map with life expectancy data
    public void readCSV() {
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 7) { // Ensure that the row has at least 7 columns
                    String country = values[0];
                    String lifeExpectancy = values[6];
                    countryLifeExpectancyMap.put(country, lifeExpectancy);
                }
            }
            br.close(); // Close the BufferedReader
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get life expectancy by country
    public String getLifeExpectancy(String country) {
        return countryLifeExpectancyMap.getOrDefault(country, "Country not found");
    }

    // Method to process a single line of data and calculate life span
    public void listData(String data) {
        String[] fields = data.split(",");

        if (fields.length >= 10) { // Ensure that there are enough fields
            String diagnosisDate = fields[9];
            String artStartDate = fields[11];
            String country = fields[0];
            String dateOfBirth = fields[6];

            // Extract the year from the diagnosisDate, artStartDate, dateOfBirth
            int yearOfDiagnosis = Integer.parseInt(diagnosisDate.split("/")[2]);
            int yearTreatmentStarted = Integer.parseInt(artStartDate.split("/")[2]);
            int yearOfBirth = Integer.parseInt(artStartDate.split("/")[2]);

            // Get life expectancy from the CSV data
            String lifeExpectancyStr = getLifeExpectancy(country);
            double lifeExpectancy = Double.parseDouble(lifeExpectancyStr);

            // patientAge
            int patientsAge = 2024 - yearOfBirth;

            // Create an instance of LifeExpectancyCalculator and calculate the life span
            LifeExpectancyCalculator calculator = new LifeExpectancyCalculator();
            int calculatedLifeSpan = calculator.calculateLifeSpan(yearOfDiagnosis, yearTreatmentStarted, patientsAge, lifeExpectancy);


            // Call this method
            
            CSVReader reader = new CSVReader();
            reader.readCSV();


        }
    }
}
