package br.com.welton.dozeroaaws.controller;

import br.com.welton.dozeroaaws.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.DoubleToLongFunction;

@RequestMapping(value = "/math")
@RestController
public class MathController {

    private final AtomicLong counter = new AtomicLong();
    @GetMapping("sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable (value = "numberOne") String numberOne,
                      @PathVariable (value = "numberTwo") String numberTwo
                      ) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
                    return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }
    @GetMapping("sub/{subNumberOne}/{subNumberTwo}")
    public Double subtraction(@PathVariable (value = "subNumberOne") String subNumberOne,
    @PathVariable (value = "subNumberTwo") String subNumberTwo
                      ) throws Exception {

        if (!isNumeric(subNumberOne) || !isNumeric(subNumberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return convertToDouble(subNumberOne) - convertToDouble(subNumberTwo);
    }
    @GetMapping("mult/{multiplicationNumberOne}/{multiplicationNumberTwo}")
    public Double multiplication(
            @PathVariable(value = "multiplicationNumberOne") String multiplicationNumberOne,
            @PathVariable(value = "multiplicationNumberTwo") String multiplicationNumberTwo
            ) throws Exception {
        if(!isNumeric(multiplicationNumberOne) || !isNumeric(multiplicationNumberTwo)){
            throw  new UnsupportedMathOperationException("Please set a numeric value");
        }
        return convertToDouble(multiplicationNumberOne) * convertToDouble(multiplicationNumberTwo);
    }

    @GetMapping("division/{divisionNumberOne}/{divisionNumberTwo}")
    public Double division(
            @PathVariable(value = "divisionNumberOne") String divisionNumberOne,
            @PathVariable(value = "divisionNumberTwo") String divisionNumberTwo
    ) throws Exception {
        if(!isNumeric(divisionNumberOne) || !isNumeric(divisionNumberTwo)){
            throw  new UnsupportedMathOperationException("Please set a numeric value");
        }
        return convertToDouble(divisionNumberOne) / convertToDouble(divisionNumberTwo);
    }
    @GetMapping("mean/{meanNumberOne}/{meanNumberTwo}")
    public Double mean(
            @PathVariable(value = "meanNumberOne") String meanNumberOne,
            @PathVariable(value = "meanNumberTwo") String meanNumberTwo
    ) throws Exception {
        if(!isNumeric(meanNumberOne) || !isNumeric(meanNumberTwo)){
            throw  new UnsupportedMathOperationException("Please set a numeric value");
        }
        Double media = (convertToDouble(meanNumberOne) + convertToDouble(meanNumberTwo)) / 2;
        return media;
    }

    @GetMapping("square/{squareNumberOne}")
    public Double square(@PathVariable(value = "squareNumberOne") String squareNumberOne)
            throws Exception {
        if (!isNumeric(squareNumberOne)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return Math.sqrt(convertToDouble(squareNumberOne));
    }

        private Double convertToDouble (String strNnumber){
            if (strNnumber == null) return 0D;
            String number = strNnumber.replaceAll(",", ".");
            if (isNumeric(number)) return Double.parseDouble(number);
            return 0D;
        }

        private boolean isNumeric (String strNnumber){
            if (strNnumber == null) return false;
            String number = strNnumber.replaceAll(",", ".");
            return number.matches("[-+]?[0-9]*\\.?[0-9]+");
        }
}
