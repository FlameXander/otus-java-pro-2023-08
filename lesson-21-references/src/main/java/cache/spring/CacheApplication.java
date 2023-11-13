package cache.spring;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Id;

@SpringBootApplication
@EnableCaching  // подключение Spring Cache
public class CacheApplication {

    public static void main(String... args) {
        SpringApplication.run(CacheApplication.class, args);
    }

    @Slf4j
    @Service
    @RequiredArgsConstructor
    public static class UserService {

        private final UserRepository repository;

        public User create(User user) {
            return repository.save(user);
        }

        @Cacheable("users")
        public User get(Long id) {
            return repository.findById(id)
                             .orElseThrow(EntityNotFoundException::new);
        }

        @Cacheable(value = "users", key = "#name")
        public User create(String name, String email) {
            return repository.save(new User(name, email));
        }
    }
}

@Getter
@Setter
@Entity
@NoArgsConstructor
class User {

    @Id
    private Long id;
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

}

@Repository
interface UserRepository extends CrudRepository<User, Long> {

}
