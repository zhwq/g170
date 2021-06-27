package homework;

import homework.week07.DbTypeContextHolder;
import homework.week07.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@RunWith(JUnit4.class)
public class Week07DynamicDsTest {

  private JdbcTemplate jdbcTemplate;

  @Before public void init() {
    ApplicationContext app = new ClassPathXmlApplicationContext("spring-context-week07.xml");
    jdbcTemplate = (JdbcTemplate)app.getBean("jdbcTemplate");
  }

  @Test public void testDynamicDs() {
    final String sql = "insert into product_t(barcode, product_name) values (?, ?)";
    jdbcTemplate.update(sql, "testDs01", "数据源1-ds");
  }
  @Test public void testDs2() {
    final String sql = "select barcode, product_name as productName from product_t limit 5";
    // 手动切换数据源
    DbTypeContextHolder.setDbType("readonly");
    List<Product> list = jdbcTemplate.query(sql, (resultSet, i) -> {
      Product entity = new Product();
      entity.setBarcode(resultSet.getString("barcode"));
      entity.setProductName(resultSet.getString("productName"));
      return entity;
    });
    System.out.println(jdbcTemplate.getDataSource());
    list.forEach(System.out::println);
  }
}
