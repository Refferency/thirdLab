import java.util.Arrays;
import java.util.Objects;
import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

public class Drama extends Theatre{
    //параметры, влияющие на генератор цены билета
    private String typeOfLiterature;     //тип литературы

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

        double typeOFLRatio;  //определение коэфициента от типа литературы
        if(this.typeOfLiterature.equalsIgnoreCase("повесть") || this.typeOfLiterature.equalsIgnoreCase("story")){
            typeOFLRatio = 0.4;
        }
        else if(this.typeOfLiterature.equalsIgnoreCase("роман") || this.typeOfLiterature.equalsIgnoreCase("novel")){
            typeOFLRatio = 0.8;
        }
        else{
            typeOFLRatio = 0.6;
        }

        double actorRatio = sqrt(Integer.parseInt(info[1])) * 0.2;   //определение коэфициента от количества актеров
        sceneSizeRatio = pow(Integer.parseInt(info[2]), 2) * 0.0005;  //определение коэфициента от размера сцены

        return priceRatio + typeOFLRatio + actorRatio + sceneSizeRatio;
    }


    private void setTypeOfLiteratureRatio(String type) {
        String[] typeVariable = {"повесть", "story", "роман", "novel", "фэнтези", "фентези", "fantasy"};

        if (Arrays.asList(typeVariable).contains(type.toLowerCase())) {
            this.typeOfLiterature = type;
        } else {
            throw new IllegalArgumentException("Некорректный вид литературы");
        }
    }
}
