package br.com.welton.dozeroaaws.controller;

import br.com.welton.dozeroaaws.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RequestMapping(value = "/sum")
@RestController
public class MathController {

    private final AtomicLong counter = new AtomicLong();
    @GetMapping("/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable (value = "numberOne") String numberOne,
                      @PathVariable (value = "numberTwo") String numberTwo
                      ) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
                    return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    private Double convertToDouble(String strNnumber) {
        if (strNnumber == null) return 0D;
        String number = strNnumber.replaceAll(",",".");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumeric(String strNnumber) {
        if (strNnumber == null) return false;
        String number = strNnumber.replaceAll(",",".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
