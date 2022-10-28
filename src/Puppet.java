import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

public class Puppet extends Theatre{

    {
        typeName = "puppet";
        typeNameForOutput = "кукольный";
        priceRatio = 2;
    }

    public Puppet() {
        super();
    }

    public Puppet(String name, int rating, String artDir) {
        super(name, rating, artDir);
    }

    @Override
    public String getTypeName() {
        return typeName;
    }

    @Override
    public String getTypeNameForOutput() {
        return typeNameForOutput;
    }

    @Override
    public double getAdditionalRatio(String[] info){


        double decorQuality = Integer.parseInt(info[0]) * 0.5;  //определение коэфициента качества декораций
        double puppetCountRatio = sqrt(Integer.parseInt(info[1])) * 0.1; //определение коэфициента количества кукол
        sceneSizeRatio = pow(Integer.parseInt(info[2]), 2) * 0.0005; //определение коэфициента от размера сцены

        return priceRatio + decorQuality + puppetCountRatio + sceneSizeRatio;
    }
}
