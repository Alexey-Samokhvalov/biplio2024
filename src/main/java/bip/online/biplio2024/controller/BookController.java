package bip.online.biplio2024.controller;

import bip.online.biplio2024.entity.BookEntity;
import bip.online.biplio2024.response.BaseResponse;
import bip.online.biplio2024.response.DataResponse;
import bip.online.biplio2024.response.ListResponse;
import bip.online.biplio2024.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
@AllArgsConstructor
@Tag(name="Книги", description="Взаимодействие с книгами")
public class BookController {
    private final BookService service;

    @GetMapping("/all")
    @Operation(
            summary = "Просмотреть все книги"
    )
    public ResponseEntity<ListResponse<BookEntity>> getAll() {
        return ResponseEntity.ok(
                new ListResponse<BookEntity>(true, "Список книг", service.findAll()));
    }

    @GetMapping
    @Operation(
            summary = "Поиск книги по id"
    )
    public ResponseEntity<DataResponse<BookEntity>> by_id(@RequestParam Long id) {
        return ResponseEntity.ok(
                new DataResponse<BookEntity>(true, "Найдена следующая книга", service.findById(id).orElseThrow()));
    }

    @PostMapping
    @Operation(
            summary = "Добавить книгу"
    )
    public ResponseEntity<DataResponse<BookEntity>> save(@RequestBody BookEntity book) {
        return ResponseEntity.ok(
                new DataResponse<BookEntity>(true, "Книга сохранена", service.save(book)));
    }

    @PutMapping("/update")
    @Operation(
            summary = "Обновить книгу"
    )
    public ResponseEntity<BaseResponse> update(@RequestBody BookEntity book) {
        service.update(book);
        return ResponseEntity.ok(
                new BaseResponse(true, "Книга сохранена"));
    }
    @GetMapping("/title")
    @Operation(
            summary = "Поиск книги по названию"
    )
    public ResponseEntity getTitle(@RequestParam String title){
        return ResponseEntity.ok(new ListResponse<>(service.getTitle(title)));
    }
}