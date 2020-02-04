/**
 * Copyright (C), 2020-2020, code_fusheng
 * FileName: Student
 * Author:   25610
 * Date:     2020/2/4 22:16
 * Description: student 实体类
 * History:
 * <author>        <time>      <version>       <desc>
 * 作者姓名       修改时间       版本号         描述
 */
package xyz.fusheng.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Double score;
    @Column
    private Date birthday;
}
