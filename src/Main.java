public class Main {
    public static void main(String[] args) {

        Theatre th1 = new Drama("Зимний", 6, "Ленин");
        Theatre th2 = new Puppet("Санкт-Петербургский", 4, "Сталин");
        Theatre th3 = new Puppet("Фиербахский", 7, "Фиербах");
        Theatre th4 = new Drama("Сочельник", 10, "Сочи");

        //Создать театр с нуля
        Theatre.createTheatre();

        //Создать театр с уже имеющимися параметрами(нереализованно)
        //String thInfo = "Летний 21 Георгий";
        //Theatre.createTheatre(thInfo);

        //упорядочить массив по названиям в обратном порядке
        Theatre.sortAll();

        //вывести театр с самым большим рейтингом
        Theatre.getWithHighestRating();

        //найти театр по фамилии худ. руководителя и отрeдактировать
        Theatre.findAndRedact("Ленин");

        //вывести информацию о театрах с более высоким рейтингом, чем рейтинг театра с названием, заданным пользователем.
        Theatre.getWithRatingHigherThan(th1);

        Theatre.ticketPriceGenerator(th1);
        Theatre.ticketPriceGenerator(th2);

    }
}
