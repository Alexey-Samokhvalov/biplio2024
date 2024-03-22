package bip.online.biplio2024.controller;

import bip.online.biplio2024.entity.PublisherEntity;
import bip.online.biplio2024.response.BaseResponse;
import bip.online.biplio2024.response.DataResponse;
import bip.online.biplio2024.response.ListResponse;
import bip.online.biplio2024.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/publisher")
@AllArgsConstructor
@Tag(name="Издательство", description="Взаимодействие с издательствами")
public class PublisherController {
    private final PublisherService service;

    @GetMapping("/all")
    @Operation(
            summary = "Просмотреть все издательства"
    )
    public ResponseEntity<ListResponse<PublisherEntity>> getAll() {
        return ResponseEntity.ok(
                new ListResponse<PublisherEntity>(true, "Список издательств", service.findAll()));
    }

    @GetMapping
    @Operation(
            summary = "Поиск издательства по id"
    )
    public ResponseEntity<DataResponse<PublisherEntity>> by_id(@RequestParam Long id) {
        return ResponseEntity.ok(
                new DataResponse<PublisherEntity>(true, "Найдено следующее издательство", service.findById(id).orElseThrow()));
    }

    @PostMapping
    @Operation(
            summary = "Добавить издательство"
    )
    public ResponseEntity<DataResponse<PublisherEntity>> save(@RequestBody PublisherEntity publisher) {
        return ResponseEntity.ok(
                new DataResponse<PublisherEntity>(true, "Издательство сохранено", service.save(publisher)));
    }

    @PutMapping
    @Operation(
            summary = "Обновить издательство"
    )
    public ResponseEntity<BaseResponse> update(@RequestBody PublisherEntity publisher) {
        service.update(publisher);
        return ResponseEntity.ok(
                new BaseResponse(true, "Издательство сохранено"));
    }
}
