import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

public class Drama extends Theatre{
    //параметры, присущие наследнику - влияют на ticketPriceGenerator
    String typeOfLiterature;


    {
        typeName = "drama";
        typeNameForOutput = "драматический";
        priceRatio = 3;
    }

    public Drama() {
        super();
    }

    public Drama(String name, int rating, String artDir) {
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
        this.setTypeOfLiteratureRatio(info[0]);

        double typeOFLRatio, actorRatio, sceneSizeRatio; //возможно стоит вынести в поля класса

        //определение коэфициента от типа литературы
        if(this.typeOfLiterature.toLowerCase().equals("повесть")){
            typeOFLRatio = 0.4;
        }
        else if(this.typeOfLiterature.toLowerCase().equals("роман")){
            typeOFLRatio = 0.8;
        }
        else{
            typeOFLRatio = 0.6;
        }

        //определение коэфициента от количества актеров
        actorRatio = sqrt(Integer.parseInt(info[1])) * 0.2;

        //определение коэфициента от размера сцены (отопление нынче дорогое)
        sceneSizeRatio = pow(Integer.parseInt(info[2]), 2) * 0.0005;
        //System.out.println(priceRatio + " " + typeOFLRatio + " " + actorRatio + " " + sceneSizeRatio);
        return priceRatio + typeOFLRatio + actorRatio + sceneSizeRatio;
    }


    private void setTypeOfLiteratureRatio(String type){
        //try catch (корректные типы литературы)

        this.typeOfLiterature = type;
    }
}
