/**
 * Copyright (C), 2020-2020, code_fusheng
 * FileName: StudentRepository
 * Author:   25610
 * Date:     2020/2/4 22:22
 * Description:
 * History:
 * <author>        <time>      <version>       <desc>
 * 作者姓名       修改时间       版本号         描述
 */
package xyz.fusheng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.fusheng.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
