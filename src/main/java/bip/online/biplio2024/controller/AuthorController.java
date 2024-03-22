package bip.online.biplio2024.controller;

import bip.online.biplio2024.entity.AuthorEntity;
import bip.online.biplio2024.response.BaseResponse;
import bip.online.biplio2024.response.DataResponse;
import bip.online.biplio2024.response.ListResponse;
import bip.online.biplio2024.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/author")
@AllArgsConstructor
@Tag(name="Автор", description="Взаимодействие с авторами")
public class AuthorController {
    private final AuthorService service;

    @GetMapping("/all")
    @Operation(
            summary = "Просмотреть всех авторов"
    )
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.ok(
                new ListResponse<AuthorEntity>(true, "Список акторов", service.findAll()));
    }

    @GetMapping
    @Operation(
            summary = "Поиск автора по id"
    )
    public ResponseEntity<BaseResponse> by_id(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(
                    new DataResponse<AuthorEntity>(true, "Найден следующий автор", service.findById(id).orElseThrow()));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }

    @PostMapping
    @Operation(
            summary = "Добавить автора"
    )
    public ResponseEntity<BaseResponse> save(@RequestBody AuthorEntity author) {
        try {
            return ResponseEntity.ok(
                    new DataResponse<AuthorEntity>(true, "Автор сохранен", service.save(author)));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }

    @PutMapping
    @Operation(
            summary = "Обновить автора"
    )
    public ResponseEntity<BaseResponse> update(@RequestBody AuthorEntity author) {
        try {
            service.update(author);
            return ResponseEntity.ok(
                    new BaseResponse(true, "Автор сохранен"));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить автора"
    )
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new BaseResponse(true, "Автор удален"));
        }catch (RuntimeException e) {
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }
}

