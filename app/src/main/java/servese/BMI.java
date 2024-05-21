package servese;

public class BMI {

    private Integer height;
    private Integer weghit;

    public BMI(Integer height, Integer weghit) {
        this.height = height;
        this.weghit = weghit;
    }

    public Double calculate(){

        return (double) (this.height/(this.weghit*this.weghit));
    }
}
