package homework.week07;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * <p/>
 * javax.sql.DataSource
 * <p/>
 * AbstractRoutingDataSource
 * 测试读 写 使用2个数据源
 */
public class DynamicDatasource extends AbstractRoutingDataSource {
  @Override
  protected Object determineCurrentLookupKey() {
    return DbTypeContextHolder.getDbType();
  }
}
