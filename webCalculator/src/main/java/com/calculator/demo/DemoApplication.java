package com.calculator.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @GetMapping("/add")
    public String Add(@RequestParam(value ="first") String a, @RequestParam(value ="second") String b) {
        Double x = Double.valueOf(a);
        Double y = Double.valueOf(b);
        return Double.toString(x+y);
    }

    @GetMapping("/subtract")
    public String Subtract(@RequestParam(value ="first") String a,@RequestParam(value ="second") String b) {
        Double x = Double.valueOf(a);
        Double y = Double.valueOf(b);
        return Double.toString(x-y);
    }
    @GetMapping("/multiply")
    public String Multiply(@RequestParam(value ="first") String a,@RequestParam(value ="second") String b) {
        Double x = Double.valueOf(a);
        Double y = Double.valueOf(b);
        return Double.toString(x*y);
    }
    @GetMapping("/divide")
    public String divide(@RequestParam(value ="first") String a,@RequestParam(value ="second") String b) {

        Double x = Double.valueOf(a);
        Double y = Double.valueOf(b);
        if(y != 0){
            return Double.toString(x/y);}
        else {return "E";}
    }
    @GetMapping("/power")
    public String Power(@RequestParam(value ="first") String a) {
        Double x = Double.valueOf(a);
        return Double.toString(Math.pow(x,2));
    }
    @GetMapping("/sqrt")
    public String Sqrt(@RequestParam(value ="first") String a) {
        Double x = Double.valueOf(a);
        if(x<0){
            return "E";
        }else{
            return Double.toString(Math.sqrt(x));}
    }
    @GetMapping("/inverse")
    public String inverse(@RequestParam(value ="first") String a) {
        Double x = Double.valueOf(a);
        if(x==0){
            return "E";
        }else{
            return Double.toString(1/x);}
    }
    @GetMapping("/percent")
    public String Percent(@RequestParam(value ="first") String a) {
        Double x = Double.valueOf(a);

        return Double.toString(x/100);
    }
    @GetMapping("/sign")
    public String Sign(@RequestParam(value ="first") String a) {
        Double x = Double.valueOf(a);

        return Double.toString(x*-1);
    }
}
