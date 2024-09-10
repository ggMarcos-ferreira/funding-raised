package com.checkr.interviews;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FundingRaised {

    public static List<Map<String, String>> where(Map<String, String> options) throws IOException {
        DataProcessor processor = new CSVDataProcessor("startup_funding.csv");
        return processor.processData(options);
    }

    public static Map<String, String> findBy(Map<String, String> options) throws IOException, NoSuchEntryException {
        DataProcessor processor = new CSVDataProcessor("startup_funding.csv");
        List<Map<String, String>> results = processor.processData(options);
        if (results.isEmpty()) {
            throw new NoSuchEntryException();
        }
        return results.get(0);
    }

    public static void main(String[] args) {
        try {
            Map<String, String> options = new HashMap<>();
            options.put("company_name", "Facebook");
            options.put("round", "a");
            System.out.print(FundingRaised.where(options).size());
        } catch (IOException e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        }
    }
}
