package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed>  filteredMeal = getFilteredWithExceeded2(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        filteredMeal.stream()
                .forEach(meal -> System.out.println(meal.getDateTime() + " " + meal.getDescription() + " " + meal.getCalories() + " " + meal.isExceed()));

    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded2(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map <LocalDate, Integer> dateMap =
                mealList.stream()
                .collect(Collectors.groupingBy(UserMeal::getDate, Collectors.summingInt(UserMeal::getCalories)));

        return
                mealList.stream()
                //.filter(meal -> meal.getTime().isBefore(endTime))
                //.filter(meal -> meal.getTime().isAfter(startTime)
                .filter(meal -> TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal -> new UserMealWithExceed(meal, (dateMap.get(meal.getDate()) > caloriesPerDay)))
                .collect(Collectors.toList());
    }



    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> caloriesDateMap = new HashMap<>(); //ключ - дата, значение - сумма калорий за эту дату
        for (UserMeal userMealElement : mealList) {
            caloriesDateMap.merge(userMealElement.getDateTime().toLocalDate(), userMealElement.getCalories(), (oldVal, newVal) -> oldVal + newVal);
        }

        List<UserMealWithExceed> mealListWithExceed = new ArrayList<>(); //список всей еды с полем превышения дневной нормы калорий
        for (UserMeal userMealElement : mealList) {
            boolean isExceed = false; if (caloriesDateMap.get(userMealElement.getDateTime().toLocalDate()) > caloriesPerDay) isExceed = true;
            UserMealWithExceed userMealWithExceedElement = new UserMealWithExceed(userMealElement.getDateTime(), userMealElement.getDescription(), userMealElement.getCalories(), isExceed);
            mealListWithExceed.add(userMealWithExceedElement);
        }

        List<UserMealWithExceed> mealListWithExceedBetweenTime = new ArrayList<>();//список еды с полем превышения между startTime и endTime
        for (UserMealWithExceed userMealWithExceedElement : mealListWithExceed) {
            if (userMealWithExceedElement.getDateTime().toLocalTime().isBefore(endTime) & userMealWithExceedElement.getDateTime().toLocalTime().isAfter(startTime)){
                mealListWithExceedBetweenTime.add(userMealWithExceedElement);
            }
        }

        return mealListWithExceedBetweenTime;
    }
}
