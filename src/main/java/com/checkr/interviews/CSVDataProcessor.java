package com.checkr.interviews;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVDataProcessor implements DataProcessor {

  private final String filePath;
  private static final Map<String, Integer> COLUMN_INDEX_MAP;

  static {
    COLUMN_INDEX_MAP = new HashMap<>();
    COLUMN_INDEX_MAP.put("company_name", 1);
    COLUMN_INDEX_MAP.put("city", 4);
    COLUMN_INDEX_MAP.put("state", 5);
    COLUMN_INDEX_MAP.put("round", 9);
  }

  public CSVDataProcessor(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public List<String[]> readData() throws IOException {
    try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
      List<String[]> csvData = reader.readAll();
      if (!csvData.isEmpty()) {
        csvData.remove(0); // Remove header
      }
      return csvData;
    }
  }

  @Override
  public List<String[]> filterData(List<String[]> data, Map<String, String> options) {
    List<String[]> results = new ArrayList<>(data);

    for (Map.Entry<String, String> entry : options.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();

      Integer columnIndex = COLUMN_INDEX_MAP.get(key);
      if (columnIndex != null) {
        results.removeIf(row -> !row[columnIndex].equals(value));
      }
    }

    return results;
  }

  @Override
  public List<Map<String, String>> mapData(List<String[]> data) {
    List<Map<String, String>> mappedData = new ArrayList<>();
    for (String[] row : data) {
      Map<String, String> mapped = new HashMap<>();
      mapped.put("permalink", row[0]);
      mapped.put("company_name", row[1]);
      mapped.put("number_employees", row[2]);
      mapped.put("category", row[3]);
      mapped.put("city", row[4]);
      mapped.put("state", row[5]);
      mapped.put("funded_date", row[6]);
      mapped.put("raised_amount", row[7]);
      mapped.put("raised_currency", row[8]);
      mapped.put("round", row[9]);
      mappedData.add(mapped);
    }
    return mappedData;
  }
}
