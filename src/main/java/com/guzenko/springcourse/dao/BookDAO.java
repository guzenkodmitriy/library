package com.guzenko.springcourse.dao;

import com.guzenko.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.guzenko.springcourse.models.Book;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(com.guzenko.springcourse.models.Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES(?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, com.guzenko.springcourse.models.Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE id=?", updatedBook.getName(),
                updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public void assignBook(int id, int person_id){

        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", person_id, id);

    }

    public void unassignBook(int id){
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE id=?", id);
    }

    public List<Person> whoAssignedBook(int id){
        return jdbcTemplate.query("select\n" +
                "    person.*\n" +
                "from\n" +
                "    book book\n" +
                "    inner join person person on person.id = book.person_id\n" +
                "where book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class));
    }
}
