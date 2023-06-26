package exercise.controllers;

import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;

import exercise.domain.User;
import exercise.domain.query.QUser;

import io.javalin.validation.BodyValidator;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();
        String json = DB.json().toJson(users);
        ctx.json(json);
        // END
    };

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
                .id.equalTo(Integer.parseInt(id))
                .findOne();
        String json = DB.json().toJson(user);
        ctx.json(json);
        // END
    };

    public void create(Context ctx) {

        // BEGIN
        User user = ctx.bodyValidator(User.class)
                .check(i -> i.getFirstName().length() > 0, "Имя не должно быть пустым")
                .check(i -> i.getLastName().length() > 0, "Имя не должно быть пустым")
                .check(i -> EmailValidator.getInstance().isValid(i.getEmail()), "Email не может быть пустым")
                .check(i -> StringUtils.isNumeric(i.getPassword()), "Пароль должен содержать только цифры")
                .check(i -> i.getPassword().length() > 4, "Пароль должен быть не короче 4 символов")
                .get();
        user.save();
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        String body = ctx.body();
        User user = DB.json().toBean(User.class, body);
        new QUser()
                .id.equalTo(Integer.parseInt(id))
                .asUpdate()
                .set("first_name", user.getFirstName())
                .set("last_name", user.getLastName())
                .set("email", user.getEmail())
                .set("password", user.getPassword())
                .update();
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        int user = new QUser()
                .id.equalTo(Integer.parseInt(id))
                .delete();
        String json = DB.json().toJson(user);
        ctx.json(json);
        // END
    };
}
