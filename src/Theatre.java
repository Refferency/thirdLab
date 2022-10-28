import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public abstract class Theatre {
    private String name;                //название театра
    private String artDirector;         //имя худ.руководителя
    private int rating;                 //рейтинг театра (от 1 до 10)
    private int ticketPrice;            //цена билета
    private static final ArrayList<Theatre> listOfTheatre = new ArrayList<>();

    //доп. параметры для генератора цены билета (коэфициенты)
    protected String typeName = null;   //тип театра
    protected String typeNameForOutput = null;
    protected int priceRatio;           //коэф. от типа театра
    protected double sceneSizeRatio;    //коэф. от размера сцены

    public Theatre() {
        createTheatre();
    }

    public Theatre(String name, int rating, String director) {

        for (Theatre th : listOfTheatre) {
            if (th.name.equalsIgnoreCase(name)) {
                throw new IllegalArgumentException("Театр с имененм " + name + " уже существует!");
            }
        }

        listOfTheatre.add(this);
        this.name = name;
        this.rating = rating;
        this.artDirector = director;
    }

    protected abstract String getTypeName();

    protected abstract String getTypeNameForOutput();

    public static void allList() {
        for (Theatre th : listOfTheatre) {
            System.out.print(th.getFancyInfo());
        }
    }


    //команда для создания театр с нуля !
    public static void createTheatre() {
        System.out.print("""
                \u001B[32mСоздание нового театра:\u001B[0m
                Помните, что существует два типа театра:
                drama - драматический
                puppet - кукольный
                \u001B[32mВведите через пробел его тип, название, рейтинг(от 1 до 10) и худ.руководителя:\040\u001B[0m""");
        Scanner in = new Scanner(System.in);
        String[] listOfArg = in.nextLine().split(" ");

        Theatre newTh = createTheatrePattern(listOfArg);

        System.out.println("\u001B[32m" + "Создан новый театр: \n" + "\u001B[0m" + newTh.getFancyInfo());
    }
    //команда для создания театра на основе уже имеющихся данных
    public static void createTheatre(String thInfo) {
        System.out.println("\u001B[32mСоздание нового театра на основе уже имеющихся данных\u001B[0m");
        System.out.println("Проверка данных....");
        String[] listOfArg = thInfo.split(" ");

        Theatre newTh = createTheatrePattern(listOfArg);

        System.out.println("Данные успешно прошли проверку!");
        System.out.println("\u001B[32m" + "Создан новый театр: \n" + "\u001B[0m" + newTh.getFancyInfo());
    }
    //процесс создания театра
    private static Theatre createTheatrePattern(String[] listOfArg) {
        if (listOfArg.length != 4) {
            throw new IllegalArgumentException("Некорректное количество параметров театра или они неправильно разделены пробелом");
        }

        String typeName = listOfArg[0];
        String name = listOfArg[1];
        nameIsEmpty(name);
        int rating = Integer.parseInt(listOfArg[2]);
        checkingRating(rating);
        String artDirector = listOfArg[3];
        nameIsEmpty(name);

        Theatre newTh;
        if (typeName.equalsIgnoreCase("drama") ||
                typeName.equalsIgnoreCase("драматический")) {//создаем драматический театр
            newTh = new Drama(name, rating, artDirector);
        } else if (typeName.equalsIgnoreCase("puppet") ||
                typeName.equalsIgnoreCase("кукольный")) { //создаем кукольный театр
            newTh = new Puppet(name, rating, artDirector);
        } else {
            throw new IllegalArgumentException("Данного типа театра не существует");
        }
        return newTh;
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




    //Команда поиска театра по фамилии худ. руководителя и редактирование (фамилия передается как параметр) !
    public static void findAndRedact(String artDir) {
        System.out.println("\u001B[34m" + "Поиск театра по фамилии худ.руководителя и редактирование информации о нём" + "\u001B[0m");
        findAndRedactPattern(artDir);
    }
    //Команда поиска театра по фамилии худ. руководителя и редактированиеь (метод узнает фамилию у пользователя) !
    public static void findAndRedact() {
        System.out.println("\u001B[34m" + "Поиск театра по фамилии худ.руководителя и редактирование информации о нём" + "\u001B[0m");
        System.out.print("Введите фамилию худ. руководителя: ");
        Scanner in = new Scanner(System.in);
        String artDir = in.nextLine();
        findAndRedactPattern(artDir);
    }
    //непосредственно поиск и редактирование
    private static void findAndRedactPattern(String artDir){
        //Ищем театр по имени арт.директора
        Theatre tmpTheatre = null; //потенциально нужный театр
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

        System.out.println("\u001B[32m" + "Редактирование информации о театре: " + "\u001B[0m"); //редактируем поле
        System.out.println("""
                    Список полей для редактирования:\s
                    name - название театра
                    art-director - фамилия худ.руководителя
                    rating - рейтинг театра (от 1 до 10)""");
        for(;;) {
            System.out.print("\u001B[32m" + "Введите поле для редактирования и значение через пробел: " + "\u001B[0m");

            Scanner on = new Scanner(System.in);
            String[] info = on.nextLine().split(" ");

            if (info.length != 2) {
                throw new IllegalArgumentException("Некорректный формат ввода данных (поле значение) или они неправильно разделены пробелом");
            }

            if (info[0].equalsIgnoreCase("name") || info[0].equalsIgnoreCase("название")) {
                System.out.println("Для редактирования выбрано поле - название театра");
                tmpTheatre.name = info[1];
                nameIsEmpty(tmpTheatre.name);
            } else if (info[0].equalsIgnoreCase("art-director")
                    || info[0].equalsIgnoreCase("худ.руководитель")
                    || info[0].equalsIgnoreCase("фамилия")) {
                System.out.println("Для редактирования выбрано поле - фамилия худ.руководителя");
                tmpTheatre.artDirector = info[1];
                nameIsEmpty(tmpTheatre.name);
            } else if (info[0].equalsIgnoreCase("rating") || info[0].equalsIgnoreCase("рейтинг")) {
                System.out.println("Для редактирования выбрано поле - рейтинг");
                checkingRating(Integer.parseInt(info[1])); //проверка рейтинга
                tmpTheatre.rating = Integer.parseInt(info[1]);
            } else {
                throw new IllegalArgumentException("Такого поля для редактирования нет в списке");
            }

            System.out.print(tmpTheatre.getFancyInfo());

            System.out.print("\u001B[32m" + "Хотите ли вы продолжить редактирование данного театра (да/нет): " + "\u001B[0m");
            String vote = on.nextLine();
            if("да".equalsIgnoreCase(vote)){
                continue;
            }
            else if("нет".equalsIgnoreCase(vote)){
                System.out.println("Редактирование успешно завершено!\n");
                break;
            }
            else{
                throw new IllegalArgumentException("Ответ только да или нет!");
            }
        }
    }




    //Вывести информацию о театрах с рейтингом больше введенного !
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


    //Вывести информацию о театре !
    private String getFancyInfo() {
        return "\u001B[36m" + "Информация о театре: " + "\u001B[0m'" + this.name + "' (" + this.getTypeNameForOutput() + "); " +
                "Рейтинг: " + this.rating + "; " +
                "Худ. руководитель: " + this.artDirector + "\n";

    }


    //Сгенерировать цену билета исходя из некоторых параметров !
    public static void ticketPriceGenerator(Theatre th){
            System.out.println("\u001B[34m" + "Генератор цены билета для театра: " + "\u001B[0m");
            ticketPriceGeneratorPattern(th);
    }


    //Сгенерировать цену билета исходя из некоторых параметров !
    public static void ticketPriceGenerator(){
        System.out.println("\u001B[34m" + "Генератор цены билета для театра: " + "\u001B[0m");
        System.out.print("Прежде чем сгенерировать цену, выберите театр по его названию: ");
        Scanner in = new Scanner(System.in);
        String tmpName = in.nextLine();

        Theatre th = null; //потенциально нужный театр
        for (Theatre tmpTheatre : listOfTheatre) {
            if (tmpTheatre.name.equalsIgnoreCase(tmpName)) {
                 th =  tmpTheatre;
            }
        }
        if(th == null){ //не нашли
            throw new IllegalArgumentException("Театра с данным названием не существует");
        }

        ticketPriceGeneratorPattern(th);
    }


    private static void ticketPriceGeneratorPattern(Theatre th){
        System.out.print(th.getFancyInfo());
        Scanner in = new Scanner(System.in);
        String[] info;

        if(th.typeName.equals("drama")){
            System.out.print("""
                        \u001B[32mПараметры, которые могут повлиять на цену в драматическом театре:\u001B[0m
                        вид литературы - повесть, роман или фэнтези
                        количество актеров
                        размер сцены (в кв. метрах)
                        \u001B[32mВведите параметры через пробел:\040\u001B[0m""");
            info = in.nextLine().split(" ");
            if (info.length != 3) {
                throw new IllegalArgumentException("Некорректное количество параметров или они неправильно разделены пробелом");
            }

            if(Integer.parseInt(info[1]) <= 0 || Integer.parseInt(info[2]) <= 0){
                throw new IllegalArgumentException("Значения количества актеров и размера сцены - числа положительные");
            }
        }
        else{
            System.out.print("""
                        \u001B[32mПараметры, которые могут повлиять на цену в кукольном театре:\u001B[0m
                        качество декораций(от 1 до 5)
                        количество кукол
                        размер сцены (в кв. метрах)
                        \u001B[32mВведите параметры через пробел:\040\u001B[0m""");
            info = in.nextLine().split(" ");
            if (info.length != 3) {
                throw new IllegalArgumentException("Некорректное количество параметров или они неправильно разделены пробелом");
            }

            if(Integer.parseInt(info[1]) <= 0 || Integer.parseInt(info[2]) <= 0){
                throw new IllegalArgumentException("Значения количества кукол и размера сцены - числа положительные");
            }
            if(Integer.parseInt(info[0]) < 1 || Integer.parseInt(info[0]) > 5){
                throw new IllegalArgumentException("Качество декораций варьируется от 1 до 5 (включительно)");
            }
        }



        double ratio = th.getAdditionalRatio(info);
        th.ticketPrice = (int)(ratio + th.rating) * 300;
        System.out.println("\u001B[32m" + "Цена, сгенерированная для данного театра: " + "\u001B[0m" + th.ticketPrice + "\n");
    }




    //сформировать коэф цены от некоторых параметров !
    protected abstract double getAdditionalRatio(String[] info);

    //проверка рейтинга !
    private static void checkingRating(int rating){
        final int[] ratingLimits = { 1, 10 };   //диапазон рейтинга
        if ((rating < ratingLimits[0]) || (rating > ratingLimits[1])) {
            throw new IllegalArgumentException("Рейтинг театра должен находится в диапазоне от " + ratingLimits[0] + " до " + ratingLimits[1]);
        }
    }

    //проверка имени !
    private static void nameIsEmpty(String name){
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Имя/название не может быть пустым!");
        }
    }
}