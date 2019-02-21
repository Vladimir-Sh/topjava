package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

public class AbstractMealController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<Meal> getAll() {
        log.info("getAll");
        return service.getAll(authUserId());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, authUserId());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        //checkNew(meal);
        return service.create(meal);
    }

    public void delete(int id, int userId) {
        log.info("delete {} by {}", id, userId);
        if (this.get(id).getUserId().equals(userId));
        service.delete(id, authUserId());
    }

    public void update(Meal meal, int id, int userId) {
        log.info("update {} with id={} by {}" , meal, id, userId);
        if (meal.getUserId().equals(userId));
        service.update(meal);
    }
}
