package com.checkr.interviews;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DataProcessor {

  List<String[]> readData() throws IOException;

  List<String[]> filterData(List<String[]> data, Map<String, String> options);

  List<Map<String, String>> mapData(List<String[]> data);

  // Método de conveniência para combinar leitura, filtragem e mapeamento
  default List<Map<String, String>> processData(Map<String, String> options) throws IOException {
    List<String[]> data = readData();
    List<String[]> filteredData = filterData(data, options);
    return mapData(filteredData);
  }
}
