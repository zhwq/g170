package homework.week05.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class Student implements Serializable {
  /**
   * 编号
   */
  private Integer sno;
  /**
   * 姓名
   */
  private String name;

  public Student() {}

  public Student(Integer sno, String name) {
    this.sno = sno;
    this.name = name;
  }

}
