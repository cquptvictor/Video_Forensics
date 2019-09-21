import com.edu.victor.Dao.NewsDao;
import com.edu.victor.domain.News;
import com.edu.victor.domain.Page;
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
    @Test
   public void testGet(){
       System.out.println(newsDao.getSpecificNews(27));
   }
}
