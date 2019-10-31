package com.panda.SpringBootbookCRUD.controller;

import com.panda.SpringBootbookCRUD.dao.BookDao;
import com.panda.SpringBootbookCRUD.entity.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @program: SpringBoot-book-CRUD
 * @description: 图书控制器
 * @author: jiangzq
 * @create: 2019-10-30 22:21
 **/
@Controller
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookDao bookDao;

    //查询所有图书
    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("booklist", bookDao.findAll());
        modelAndView.setViewName("bookList");
        return modelAndView;
    }

    //添加图书
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Book book) {
        bookDao.save(book);
        return "forward:/book/list";
    }

    //根据id查询book实体
    @RequestMapping("/preUpdate/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("book", bookDao.getOne(id));
        modelAndView.setViewName("bookUpdate");
        return modelAndView;
    }

    //修改图书
    @PostMapping(value = "update")
    public String update(Book book) {
        bookDao.save(book);
        return "forward:/book/list";
    }

    //删除图书
    @GetMapping("/delete")
    public String delete(Integer id) {
        bookDao.deleteById(id);
        return "forward:/book/list";
    }

    //根据条件动态查询
    @RequestMapping("/list2")
    public ModelAndView list2(Book book) {
        ModelAndView modelAndView = new ModelAndView();
        List<Book> bookList = bookDao.findAll(new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (book != null) {
                    if (book.getName() != null && !"".equals(book.getName())) {
                        predicate.getExpressions().add(criteriaBuilder.like(root.get("name"), "%" + book.getName() + "%"));
                    }
                    if (book.getAuthor() != null && !"".equals(book.getAuthor())) {
                        predicate.getExpressions().add(criteriaBuilder.like(root.get("author"), "%" + book.getAuthor() + "%"));
                    }
                }
                return predicate;
            }
        });
        modelAndView.addObject("book", book);
        modelAndView.addObject("booklist", bookList);
        modelAndView.setViewName("booklist");
        return modelAndView;
    }

    //查询
    @ResponseBody
    @RequestMapping("/query")
    public List<Book> findByName(String name) {
        //关键字查询
        return bookDao.findByName("A");
    }

    //随机显示
    @ResponseBody
    @RequestMapping("/randomList")
    public List<Book> randomList(String name) {
        return bookDao.randomList(1);
    }
}
