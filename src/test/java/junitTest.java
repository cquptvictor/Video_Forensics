import com.edu.victor.Dao.NewsDao;
import com.edu.victor.Dao.UserDao;
import com.edu.victor.Service.UserService;
import com.edu.victor.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/ApplicationContext.xml"})
public class junitTest
{
    @Autowired
    NewsDao newsDao;
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;
    @Test
   public void testGet(){
        User user = new User();
        user.setIsTeacher("0");
        user.setUsername("123");
        user.setPassword("123");
        ResponseData responseData = userService.login(user);
        System.out.println(responseData);
   }
}
