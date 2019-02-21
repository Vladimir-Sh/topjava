package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        if (repository.containsKey(id)&&repository.get(id).getUserId().equals(userId)){
            repository.remove(id);
            return true;
        }
        //throw new ru.javawebinar.topjava.util.exception.NotFoundException("Чужая еда!");
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        if (repository.get(id).getUserId().equals(userId))
        return repository.get(id);
        throw new ru.javawebinar.topjava.util.exception.NotFoundException("Meal not found.");
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        List<Meal> allMeal=
                repository.values().stream()
                //.filter(meal -> meal.getUserId().equals(userId))
                .sorted(Comparator.comparing(Meal::getDateTime)).collect(Collectors.toList());//.collect(toList());
        if (allMeal.equals(null))
            throw new ru.javawebinar.topjava.util.exception.NotFoundException("Meal(s) not found");
        return allMeal;
    }
}

