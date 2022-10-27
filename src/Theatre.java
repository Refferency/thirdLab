import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public abstract class Theatre {
    private String name;                //название театра
    private String artDirector;         //имя худ.руководителя
    private int rating;                 //рейтинг театра
    private int ticketPrice;            //цена билета
    private static final ArrayList<Theatre> listOfTheatre = new ArrayList<>();

    //доп параметры для ticketPriceGenerator (взаимодействие с наследниками)
    protected String typeName = null;   //тип театра
    protected String typeNameForOutput = null;
    protected int priceRatio;           //коэфициент цены от типа театра

    //скорее всего не будут использованы
    /*protected int potentialVisitors;  //количество желающих посетитетлей
    protected int payTheEmployee;     //зарплата работника
    final static int[] ratingLimits = { 0, 10 };   //диапазон рейтинга*/

    public Theatre() {
        listOfTheatre.add(this);
    }

    public Theatre(String name, int rating, String director) {
        this();
        this.name = name;
        this.rating = rating;
        this.artDirector = director;
    }

    protected abstract String getTypeName();

    protected abstract String getTypeNameForOutput();

    public static void allList() {
        for(Theatre th : listOfTheatre){
            System.out.print(th.getFancyInfo());
        }
    }


    static public void createTheatre() {

        System.out.print("""
                \u001B[32mСоздание нового театра:\u001B[0m
                Помните, что существует два типа театра:
                drama - драматический театр
                puppet - кукольный театр
                \u001B[32mВведите через пробел его тип, название, рейтинг и худ.руководителя:\040\u001B[0m""");
        Scanner in = new Scanner(System.in);

        String[] listOfArg = in.nextLine().split(" ");

        if (listOfArg.length != 4) {
            throw new IllegalArgumentException("Некорректное количество параметров театра");
        }

        //Добавить try catch для безопасного ввода

        String typeName = listOfArg[0];
        String name = listOfArg[1];
        int rating = Integer.parseInt(listOfArg[2]);
        String artDirector = listOfArg[3];


        //НАСЛЕДНИКИ
        Theatre newTh;
        if (typeName.equalsIgnoreCase("drama")) {         //создаем драматический театр
            newTh = new Drama(name, rating, artDirector);
        } else if (typeName.equalsIgnoreCase("puppet")) { //создаем кукольный театр
            newTh = new Puppet(name, rating, artDirector);
        } else {
            throw new IllegalArgumentException("Данного типа театра не существует");
        }

        System.out.println("\u001B[32m" + "Создан новый театр: \n" + "\u001B[0m" + newTh.getFancyInfo());

    }






    //Упорядочить массив театров по названиям в обратном порядке
    public static void sortAll() {
        System.out.println("\u001B[34m" + "Сортировка массива по названиям в обратном порядке\n" + "\u001B[0m" + "\u001B[32m" + "Исходный массив: " + "\u001B[0m");
        Theatre.allList();
        listOfTheatre.sort((Theatre th1, Theatre th2) -> th2.name.toLowerCase().compareTo(th1.name.toLowerCase()));
        System.out.println("\u001B[32m" + "Массив после сортировки: "  + "\u001B[0m");
        Theatre.allList();
        System.out.println();
    }




    //Вывести театр с самым большим рейтингом
    public static void getWithHighestRating() {
        System.out.println("\u001B[34m" + "Театр с самым большим рейтингом: " + "\u001B[0m");
        System.out.println(Collections.max(listOfTheatre, (Theatre th1, Theatre th2) -> th1.rating - th2.rating).getFancyInfo());
    }





    //Найти театр по фамилии худ. руководителя и отрeдактировать
    public static void findAndRedact(String artDir) {
        System.out.println("\u001B[34m" + "Поиск театра по фамили худ.руководителя и редактирование информации о нём" + "\u001B[0m");

        //Ищем театр по имени арт.директора
        Theatre tmpTheatre = null; //нужный театр
        for (Theatre th : listOfTheatre) {
            if (th.artDirector.equalsIgnoreCase(artDir)) {
                tmpTheatre =  th;
            }
        }
        if(tmpTheatre == null){ //не нашли
            throw new IllegalArgumentException("Театра с данным худ.руководителем не существует");
        }

        System.out.println("\u001B[32m" + "Театр по имени худ.руководиля: " + artDir + " - успешно найден" + "\u001B[0m"); //нашли
        System.out.print(tmpTheatre.getFancyInfo());

        System.out.println("\u001B[32m" + "Редактирование информации о театре: " + "\u001B[0m");//редактируем поле
        System.out.println("""
                Список полей для редактирования:\s
                name - название театра
                art-director - имя худ.руководителя
                rating - рейтинг театра""");
        System.out.print("\u001B[32m" + "Введите поле для редактирования и значение через пробел: " + "\u001B[0m");

        Scanner in = new Scanner(System.in);
        String[] info = in.nextLine().split(" ");

        if (info.length != 2) {
            throw new IllegalArgumentException("Некорректный формат ввода данных (поле значение)");
        }

            //Добавить try catch для безопасного ввода

            if (info[0].equalsIgnoreCase("name")) {
                System.out.println("Для редактирования выбрано поле - название театра");
                tmpTheatre.name = info[1];
            }
            else if (info[0].equalsIgnoreCase("art-director")) {
                System.out.println("Для редактирования выбрано поле - имя худ.руководителя");
                tmpTheatre.artDirector = info[1];
            }
            else if (info[0].equalsIgnoreCase("rating")) {
                System.out.println("Для редактирования выбрано поле - рейтинг");
                tmpTheatre.rating = Integer.parseInt(info[1]);
            }
            else {
                throw new IllegalArgumentException("Такого поля для редактирования нет в списке");
            }

        System.out.println("\u001B[32m" + "Информация о театре после редактирования: " + "\u001B[0m");//редактируем поле
        System.out.println(tmpTheatre.getFancyInfo());

    }




    //Вывести информацию о театрах с рейтингом больше введенного
    public static void getWithRatingHigherThan(Theatre theatre) {
        System.out.println("\u001B[34m" + "Вывод информации о театрах с более высоким рейтингом, чем заданный" + "\u001B[0m");

        Theatre withName = null;
        for (Theatre th : listOfTheatre) {
            if (th.name.equals(theatre.name)) {
                withName = th;
            }
        }
        if(withName == null){
            throw new IllegalArgumentException("Театр не найден");
        }
        System.out.print("\u001B[32m" + "Заданный театр: \n" + "\u001B[0m" + theatre.getFancyInfo());

        ArrayList<Theatre> result = new ArrayList<>();
        for (var th : listOfTheatre) {
            if (th.rating > withName.rating) {
                result.add(th);
            }
        }

        System.out.println("\u001B[32m" + "Искомые театры: " + "\u001B[0m");
        for(Theatre th : result){
            System.out.print(th.getFancyInfo());
        }
        System.out.println();
    }





    //Вывести информацию о театре
    private String getFancyInfo() {
        return "Информация о театре '" + this.name + "' (" + this.getTypeNameForOutput() + "); " +
                "Рейтинг: " + this.rating + "; " +
                "Худ. руководитель: " + this.artDirector + "\n";

    }



    //Сгенерировать цену билета исходя из некоторых параметров
    public static void ticketPriceGenerator(Theatre th){
            System.out.println("\u001B[34m" + "Генератор цены билета для театра: " + "\u001B[0m");
            System.out.print(th.getFancyInfo());

            Scanner in = new Scanner(System.in);
            String[] info;
            if(th.typeName.equals("drama")){

                //возможно здесь нужны доп пояснения + try catch
                System.out.print("""
                        \u001B[32mПараметры, которые могут повлиять на цену в драматическом театре:\u001B[0m
                        вид литературы - повесть, роман или фэнтези
                        количество актеров
                        размер сцены(метры квадратные)
                        \u001B[32mВведите параметры через пробел:\040\u001B[0m""");


                info = in.nextLine().split(" ");

            }
            else{
                System.out.print("""
                        \u001B[32mПараметры, которые могут повлиять на цену в кукольном театре:\u001B[0m
                        качество декораций(от 1 до 5)
                        количество кукол
                        размер сцены(метры квадратные)
                        \u001B[32mВведите параметры через пробел:\040\u001B[0m""");

                info = in.nextLine().split(" ");

            }


        double ratio = th.getAdditionalRatio(info);
        th.ticketPrice = (int)(ratio + th.rating) * 300;
        System.out.println("\u001B[32m" + "Цена, сгенерированная для данного театра: " + "\u001B[0m" + th.ticketPrice + "\n");
    }


    //сформировать коэф цены от некоторых параметров
    protected abstract double getAdditionalRatio(String[] info);
}