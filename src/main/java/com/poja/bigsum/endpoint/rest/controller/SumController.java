package com.poja.bigsum.endpoint.rest.controller;

import com.poja.bigsum.PojaGenerated;
import com.poja.bigsum.repository.DummyRepository;
import com.poja.bigsum.repository.DummyUuidRepository;
import java.math.BigInteger;
import javax.ws.rs.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@PojaGenerated
@RestController
@AllArgsConstructor
public class SumController {

  DummyRepository dummyRepository;
  DummyUuidRepository dummyUuidRepository;

  public static final ResponseEntity<String> OK = new ResponseEntity<>("OK", HttpStatus.OK);
  public static final ResponseEntity<String> KO =
      new ResponseEntity<>("KO", HttpStatus.INTERNAL_SERVER_ERROR);

  @GetMapping("/sum")
  public String sum(
      @RequestParam(required = false) String a, @RequestParam(required = false) String b) {
    nonNullValidation(a, "a");
    nonNullValidation(b, "b");
    numberValidation(a);
    numberValidation(b);
    BigInteger n1 = new BigInteger(a);
    BigInteger n2 = new BigInteger(b);
    return n1.add(n2).toString();
  }

  public static boolean isValidNumber(String num) {
    // Regular expression for a valid number (integer or decimal)
    // String regex = "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?";

    // Regular expression for a valid integer
    String regex = "[-+]?\\d+";

    return num.matches(regex);
  }

  public void numberValidation(String num) {
    if (!isValidNumber(num)) {
      throw new BadRequestException(num + " is not a valid number");
    }
  }

  public void nonNullValidation(String value, String variableName) {
    if (value == null) {
      throw new BadRequestException(variableName + " is mandatory");
    }
  }
}
