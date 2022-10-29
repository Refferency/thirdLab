/*Логинов Медведев 21ВП2 Лабораторная №3*/


public class Main {
    public static void main(String[] args) {

        //создать театр с помощью конструктора
        Theatre th1 = new Drama("Зимний", 6, "Ленин");
        Theatre th2 = new Puppet("Санкт-Петербургский", 4, "Достоевский");
        Theatre th3 = new Puppet("Фиербахский", 7, "Фиербах");
        Theatre th4 = new Drama("Пензенский", 10, "Белинский");


        //Создать театр с нуля
        Theatre.createTheatre();

        //Создать театр на основе уже имеющихся данных
        String thInfo = "Драматический Грустно 10 Георгий";
        Theatre.createTheatre(thInfo);

        //упорядочить массив по названиям в обратном порядке
        Theatre.sortAll();

        //вывести театр с самым большим рейтингом
        Theatre.getWithHighestRating();

        //найти театр по фамилии худ. руководителя и отрeдактировать
        Theatre.findAndRedact("Ленин");
        Theatre.findAndRedact();

        //вывести информацию о театрах с более высоким рейтингом, чем рейтинг театра с названием, заданным пользователем.
        Theatre.getWithRatingHigherThan(th1);

        //сгенерировать цену для определенного театра (параметром или вводов в методе)
        Theatre.ticketPriceGenerator(th1);
        Theatre.ticketPriceGenerator();
    }
}
