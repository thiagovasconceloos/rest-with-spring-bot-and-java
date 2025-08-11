package br.com.vasconcelos.controllers;


import br.com.vasconcelos.exceptions.UnsupportedMathOperationException;
import br.com.vasconcelos.math.SimpleMath;
import br.com.vasconcelos.request.converters.NumberConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/math")
public class MathController {

    private SimpleMath math = new SimpleMath();

   @RequestMapping("sum/{numberOne}/{numberTwo}")
   public Double sum(
     @PathVariable("numberOne")String numberOne,
     @PathVariable("numberTwo")     String numberTwo
   ) throws Exception {

     if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))  throw new UnsupportedMathOperationException("Please set a numeric value");
   return math.sum(NumberConverter.convertToDouble(numberOne),NumberConverter.convertToDouble(numberTwo));

   }






    @RequestMapping("subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(
            @PathVariable("numberOne")String numberOne,
            @PathVariable("numberTwo")     String numberTwo
    ) throws Exception {

        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))  throw new UnsupportedMathOperationException("Please set a numeric value");
        return math.subtraction(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));

    }


    @RequestMapping("division/{numberOne}/{numberTwo}")
    public Double division(
            @PathVariable("numberOne")String numberOne,
            @PathVariable("numberTwo")     String numberTwo
    ) throws Exception {

        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))  throw new UnsupportedMathOperationException("Please set a numeric value");
        return math.division(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));

    }

    @RequestMapping("multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable("numberOne")String numberOne,
            @PathVariable("numberTwo")     String numberTwo
    ) throws Exception {

        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))  throw new UnsupportedMathOperationException("Please set a numeric value");
        return math.multiplication(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));

    }

    @RequestMapping("median/{numberOne}/{numberTwo}")
    public Double median(
            @PathVariable("numberOne")String numberOne,
            @PathVariable("numberTwo")     String numberTwo
    ) throws Exception {

        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))  throw new UnsupportedMathOperationException("Please set a numeric value");
        return math.median(NumberConverter.convertToDouble(numberOne) , NumberConverter.convertToDouble(numberTwo));

    }


    @RequestMapping("squareroot/{number}")
    public Double squareRoot(
            @PathVariable("number")String number

    ) throws Exception {

        if(!NumberConverter.isNumeric(number) )  throw new UnsupportedMathOperationException("Please set a numeric value");
        return math.squareroot(NumberConverter.convertToDouble(number));

    }
}
