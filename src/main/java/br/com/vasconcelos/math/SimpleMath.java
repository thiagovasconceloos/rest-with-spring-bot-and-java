package br.com.vasconcelos.math;

import br.com.vasconcelos.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;




public class SimpleMath {



    public Double sum(Double numberOne, Double numberTwo
    ) {
        return numberOne + numberTwo;
    }


    public Double subtraction(Double numberOne, Double numberTwo
    ) {
        return numberOne - numberTwo;
    }


    public Double division(Double numberOne, Double numberTwo
    ) {
        return numberOne / numberTwo;
    }

    public Double multiplication(Double numberOne, Double numberTwo
    ) {
        return numberOne * numberTwo;
    }


    public Double median(Double numberOne, Double numberTwo
    ) {
        return (numberOne + numberTwo)/2;
    }

    public Double squareroot(Double number) {
        return Math.sqrt(number);
    }



}
