package demo.repository;

import demo.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by <a href="mailto:javaworld@qq.com">Woody</a>
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
