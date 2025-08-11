package br.com.vasconcelos.controllers;


import br.com.vasconcelos.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/math")
public class MathController {


   @RequestMapping("sum/{numberOne}/{numberTwo}")
   public Double sum(
     @PathVariable("numberOne")String numberOne,
     @PathVariable("numberTwo")     String numberTwo
   ) throws Exception {

     if(!isNumeric(numberOne) || !isNumeric(numberTwo))  throw new UnsupportedMathOperationException("Please set a numeric value");
   return convertToDouble(numberOne) + convertToDouble(numberTwo);

   }

    private Double convertToDouble(String strNumber) throws IllegalArgumentException {
        if(strNumber == null || strNumber.isEmpty()) throw new UnsupportedMathOperationException("Please set a numeric value");
        String number = strNumber.replace(",",".");
        return Double.parseDouble(number);
   }


    private boolean isNumeric(String strNumber){

       if(strNumber == null || strNumber.isEmpty()) return false;
       String number = strNumber.replace(",",".");

       return number.matches("[-+]?[0-9]*\\.?[0-9]+");

   }




    @RequestMapping("subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(
            @PathVariable("numberOne")String numberOne,
            @PathVariable("numberTwo")     String numberTwo
    ) throws Exception {

        if(!isNumeric(numberOne) || !isNumeric(numberTwo))  throw new UnsupportedMathOperationException("Please set a numeric value");
        return convertToDouble(numberOne) - convertToDouble(numberTwo);

    }


    @RequestMapping("division/{numberOne}/{numberTwo}")
    public Double division(
            @PathVariable("numberOne")String numberOne,
            @PathVariable("numberTwo")     String numberTwo
    ) throws Exception {

        if(!isNumeric(numberOne) || !isNumeric(numberTwo))  throw new UnsupportedMathOperationException("Please set a numeric value");
        return convertToDouble(numberOne) / convertToDouble(numberTwo);

    }

    @RequestMapping("multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable("numberOne")String numberOne,
            @PathVariable("numberTwo")     String numberTwo
    ) throws Exception {

        if(!isNumeric(numberOne) || !isNumeric(numberTwo))  throw new UnsupportedMathOperationException("Please set a numeric value");
        return convertToDouble(numberOne) / convertToDouble(numberTwo);

    }

    @RequestMapping("median/{numberOne}/{numberTwo}")
    public Double median(
            @PathVariable("numberOne")String numberOne,
            @PathVariable("numberTwo")     String numberTwo
    ) throws Exception {

        if(!isNumeric(numberOne) || !isNumeric(numberTwo))  throw new UnsupportedMathOperationException("Please set a numeric value");
        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;

    }


    @RequestMapping("squareroot/{number}")
    public Double squareRoot(
            @PathVariable("number")String number

    ) throws Exception {

        if(!isNumeric(number) )  throw new UnsupportedMathOperationException("Please set a numeric value");
        return Math.sqrt(convertToDouble(number));

    }
}
