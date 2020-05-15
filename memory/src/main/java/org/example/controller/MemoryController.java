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
    return getMemoryInfo();
  }

  private Map<String, Long> getMemoryInfo() {
    Runtime runtime = Runtime.getRuntime();
    final long maxMemory = runtime.maxMemory() / (1024 * 1024);
    final long allocatedMemory = runtime.totalMemory() / (1024 * 1024);
    final long freeMemory = runtime.freeMemory() / (1024 * 1024);
    final long totalFreeMemory = maxMemory - allocatedMemory + freeMemory;
    Map<String, Long> result = new HashMap<>(3);
    result.put("maxMemoryInMB", maxMemory);
    result.put("allocatedMemoryInMB", allocatedMemory);
    result.put("freeMemoryInMB", freeMemory);
    result.put("totalFreeMemoryInMB", totalFreeMemory);
    log.info("Memory Info: {}", result);
    return result;
  }
}
