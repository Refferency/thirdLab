import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

public class Puppet extends Theatre{
    //параметры, присущие наследнику - влияют на ticketPriceGenerator

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
        double decorQuality, puppetCountRatio, sceneSizeRatio; //возможно стоит вывести в поля класса

        //определение коэфициента качества декораций
        decorQuality = Integer.parseInt(info[0]) * 0.5;

        //определение коэфициента количества кукол
        puppetCountRatio = sqrt(Integer.parseInt(info[1])) * 0.1;

        //определение коэфициента от размера сцены (отопление нынче дорогое)
        sceneSizeRatio = pow(Integer.parseInt(info[2]), 2) * 0.0005;

        return priceRatio + decorQuality + puppetCountRatio + sceneSizeRatio;
    }
}
