package homework.week07;

public class DbTypeContextHolder {
  static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

  private static final String DEFAULT_DS = "master";

  public static String getDbType() {
    String dbType = contextHolder.get();
    if (dbType == null) dbType = DEFAULT_DS;
    return dbType;
  }

  public static void setDbType(String dbType) {
    contextHolder.set(dbType);
  }

  public static void clearDbType() {
    contextHolder.remove();
  }
  /**
   * 设定主库
   */
  public static void setMaster() {
    clearDbType();
  }
  /**
   * 设定只读库
   */
  public static void setReadonly() {
    setDbType("readonly");
  }
}
