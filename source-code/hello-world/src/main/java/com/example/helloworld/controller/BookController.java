package com.example.helloworld.controller;

import com.example.helloworld.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BookController {

    private List<Book> books = new ArrayList<>();


    //test
    /*
curl -X POST \
  http://localhost:8083/api/book \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 63cc9193-7858-faac-ff75-cd3255f5816c' \
  -d '{"name":"book3", "description":"book3描述"}'

     */

    // RequestBody  可以把HttpRequest中的JSON类型反序列化为合适的JAVA类型
    @PostMapping("/book")
    public ResponseEntity<List<Book>> addBook(@RequestBody Book book) {
        books.add(book);
        return ResponseEntity.ok(books);
    }


    /**
     curl -X POST \
     http://localhost:8083/api/books \
     -H 'cache-control: no-cache' \
     -H 'content-type: application/json' \
     -H 'postman-token: 63cc9193-7858-faac-ff75-cd3255f5816c' \
     -d '[{"name":"book5", "description":"book5描述"},{"name":"book4", "description":"book4描述"}]'
     */
    @PostMapping("/books")
    public ResponseEntity addBooks(@RequestBody List<Book> book) {
        books.addAll(book);
        return ResponseEntity.ok(books);
    }

    /**
     curl -X DELETE http://localhost:8083/api/delete/0

     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBookById(@PathVariable("id") int id) {
        books.remove(id);
        return ResponseEntity.ok(books);
    }

    /**
curl http://localhost:8083/api/book?name=book3
     */
    @GetMapping("/book")
    public ResponseEntity getBookByName(@RequestParam("name") String name) {
        List<Book> results = books.stream().filter(book -> book.getName().equals(name)).collect(Collectors.toList());
        return ResponseEntity.ok(results);

    }

    @GetMapping
    public ResponseEntity getAllBooks() {
        return ResponseEntity.ok(books);
    }
}
