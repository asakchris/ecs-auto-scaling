package org.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "memory")
@Slf4j
public class MemoryController {
  @GetMapping
  public ResponseEntity<Map<String, Long>> memory() {
    Map<String, Long> result = getMemory();
    return ResponseEntity.ok(result);
  }

  private Map<String, Long> getMemory() {
    List<Byte[]> bytes = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      Byte[] bytes1 = new Byte[1048576];
      bytes.add(bytes1);
    }
    Runtime runtime = Runtime.getRuntime();
    final long freeMemory = runtime.freeMemory() / (1024 * 1024);
    log.info("Free Memory: {}", freeMemory);
    Map<String, Long> result = new HashMap<>(1);
    result.put("freeMemoryInMB", freeMemory);
    return result;
  }
}
