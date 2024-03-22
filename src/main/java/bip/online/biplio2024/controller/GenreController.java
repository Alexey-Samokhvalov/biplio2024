package bip.online.biplio2024.controller;

import bip.online.biplio2024.entity.GenreEntity;
import bip.online.biplio2024.response.BaseResponse;
import bip.online.biplio2024.response.DataResponse;
import bip.online.biplio2024.response.ListResponse;
import bip.online.biplio2024.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/genre")
@AllArgsConstructor
@Tag(name="Жанр", description="Взаимодействие с жанрами")
public class GenreController {
    private final GenreService service;

    @GetMapping("/all")
    @Operation(
            summary = "Просмотреть все жанры"
    )
    public ResponseEntity<ListResponse<GenreEntity>> getAll() {
        return ResponseEntity.ok(
                new ListResponse<GenreEntity>(true, "Список жанров", service.findAll()));
    }

    @GetMapping
    @Operation(
            summary = "Поиск жанра по id"
    )
    public ResponseEntity<DataResponse<GenreEntity>> by_id(@RequestParam Long id) {
        return ResponseEntity.ok(
                new DataResponse<GenreEntity>(true, "Найден следующий жанр", service.findById(id).orElseThrow()));
    }

    @PostMapping
    @Operation(
            summary = "Добавить жанр"
    )
    public ResponseEntity<DataResponse<GenreEntity>> save(@RequestBody GenreEntity genre) {
        return ResponseEntity.ok(
                new DataResponse<GenreEntity>(true, "Жанр сохранен", service.save(genre)));
    }

    @PutMapping
    @Operation(
            summary = "Обновить жанр"
    )
    public ResponseEntity<BaseResponse> update(@RequestBody GenreEntity genre) {
        service.update(genre);
        return ResponseEntity.ok(
                new BaseResponse(true, "Жанр сохранен"));
    }
}
