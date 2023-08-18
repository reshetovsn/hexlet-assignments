package exercise.repository;

import exercise.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
// Зависимости для дополнительного задания
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import com.querydsl.core.types.dsl.StringPath;


import org.springframework.stereotype.Repository;

@Repository
// Репозиторий для основной задачи
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
//public interface UserRepository extends
//        JpaRepository<User, Long>,
//        QuerydslPredicateExecutor<User>,
//        QuerydslBinderCustomizer<QUser> {
//

// Дополнительная задача
// Если решите сделать дополнительную задачу, измените существующий репозиторий для работы с Querydsl предикатами
// BEGIN

//    @Override
//    default void customize(QuerydslBindings bindings, QUser user) {
//
//        bindings.bind(user.firstName, user.lastName, user.email, user.profession).first(
//                (StringPath path, String value) -> path.containsIgnoreCase(value)
//        );
//        bindings.bind(user.gender).first((StringPath path, String value) -> path.eq(value));
//    }
//
//}
// END
