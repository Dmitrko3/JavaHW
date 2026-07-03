public class Rational {
    int numerator;
    int denominator;

    public Rational(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Rational minus(Rational rat){
        //makes the rational number to be subtracted from the given rational number
        Rational ret=new Rational(this.numerator*rat.denominator-rat.numerator*this.denominator,this.denominator*rat.denominator);
        ret.reduction();
        return ret;
    }
    public Rational plus(Rational rat){
        //makes the rational number to be added to the given rational number
        Rational ret=new Rational(this.numerator*rat.denominator+rat.numerator*this.denominator,this.denominator*rat.denominator);
        ret.reduction();
        return ret;
    }
    public Rational multiply(Rational rat){
        //makes the rational number to be multiplied by the given rational number
        Rational ret=new Rational(this.numerator*rat.numerator,this.denominator*rat.denominator);
        ret.reduction();
        return ret;
    }
    public Rational divide(Rational rat){
        //makes the rational number to be divided by the given rational number
        Rational ret=new Rational(this.numerator*rat.denominator,this.denominator*rat.numerator);
        ret.reduction();
        return ret;



    }
    public Boolean equals(Rational rat){
        //checks if the rational number is equal to the given rational number
        return (this.numerator==rat.numerator && this.denominator==rat.denominator);
    }
    public String toString(){
        //prints the rational number in the form of "numerator/denominator"
        return numerator+"/"+denominator;
    }
    private void  reduction(){
        /*
        * the function is used to reduce the rational number.
        * also its eliminates the minus from the denominator if needed.
        * */
        for (int i = 2; i < Math.min(numerator, denominator); i++) {
            if (numerator % i == 0 && denominator % i == 0) {
                numerator /= i;
                denominator /= i;
            }
        }
        if (denominator < 0) {
        }
    }
}


