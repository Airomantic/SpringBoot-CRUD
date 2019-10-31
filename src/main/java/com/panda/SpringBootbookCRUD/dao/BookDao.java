package com.panda.SpringBootbookCRUD.dao;

import com.panda.SpringBootbookCRUD.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: SpringBoot-book-CRUD
 * @description:
 * @author: jiangzq
 * @create: 2019-10-30 22:00
 **/
public interface BookDao extends JpaRepository<Book,Integer>, JpaSpecificationExecutor<Book> {
    @Query(value = "select * from t_book where t_book,name like %?1%", nativeQuery = true)
    //或者@Query(value = "select * from t_book where t_book,name like %?1%")
    public List<Book> findByName(String name);

    //nativeQuery默认是HQL查询，true表示使用本地查询，就是原生的SQL方式
    @Query(value = "select * from t_book ORDER BY RAND() limit ?1", nativeQuery = true)
    public List<Book> randomList(Integer id);
}
