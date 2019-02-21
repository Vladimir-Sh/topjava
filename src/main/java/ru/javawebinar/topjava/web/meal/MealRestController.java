package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

public class MealRestController extends AbstractMealController{
    @Override
    public List<Meal> getAll() {
        return super.getAll();
    }

    @Override
    public Meal get(int id) {
        return super.get(id);
    }

    @Override
    public Meal create(Meal meal) {
        return super.create(meal);
    }

    @Override
    public void delete(int id, int userId) {
        super.delete(id, userId);
    }

    @Override
    public void update(Meal meal, int id, int userId) {
        super.update(meal, id, userId);
    }

}