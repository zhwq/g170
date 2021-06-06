package homework.week05;

import cn.hutool.core.util.XmlUtil;
import homework.week05.model.Student;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * spring bean 装配
 * <p/>
 *  1. 通过 ApplicationContext
 *    1.1 beans.xml ClassPathXmlApplicationContext
 *    1.2 包自动扫描路径
 *  2. spring boot 自动装配
 *  3. META-INF/spring.factorires
 */
public class BeanLoadMain {
  @SneakyThrows
  public static void main(String[] args) {
    final String beanXml = "spring-beans-week05.xml";
    final String configXml = "spring-context-week05.xml";
//    loadXml(beanXml);
    loadByXmlWithAnnotation(configXml);
  }

  private static void loadByXmlWithAnnotation(final String configXml) {
    // xml入口+注解
    ApplicationContext app = new ClassPathXmlApplicationContext(configXml);
    Student student = app.getBean(Student.class);
    student.setSno(2);
    student.setName("anno");
    System.out.println(student);
  }

  /*
  ① 通过ClassPathXmlApplicationContext装配beans
   */
  private void loadByXml(final String beanXml) {
    ApplicationContext app = new ClassPathXmlApplicationContext(beanXml);
    Student student = app.getBean("student", Student.class);
    System.out.println(student);
  }



  /*
  @FIXME: xml 解析 hutool/dom4j
  beans/bean[class]
   */
  private static void loadXml(String beanXml) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
    InputStream is = null;
    try {
      is = BeanLoadMain.class.getClassLoader().getResourceAsStream(beanXml);
      Document document = XmlUtil.readXML(is);
      Element root = document.getDocumentElement();
      NodeList beanList = root.getElementsByTagName("bean");
      for (int i = 0, len = beanList.getLength(); i < len; i++) {
        Node beanNode = beanList.item(i);
        NamedNodeMap attrs = beanNode.getAttributes();
        Node attrNode = attrs.getNamedItem("class");
        final String klass = attrNode.getNodeValue();
        Class c = Class.forName(klass);
        Object obj = c.newInstance();
        // Student(sno=null, name=null)
        System.out.println(obj);
      }
    } finally {
      if (is != null) {
        is.close();
      }
    }
  }
}
