package bip.online.biplio2024.controller;

import bip.online.biplio2024.entity.CityEntity;
import bip.online.biplio2024.response.BaseResponse;
import bip.online.biplio2024.response.DataResponse;
import bip.online.biplio2024.response.ListResponse;
import bip.online.biplio2024.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/city")
@AllArgsConstructor
@Tag(name="Город", description="Взаимодействие с городами")
public class CityController {
    private final CityService service;

    @GetMapping("/all")
    @Operation(
            summary = "Просмотреть все города"
    )
    public ResponseEntity<ListResponse<CityEntity>> getAll() {
        return ResponseEntity.ok(
                new ListResponse<CityEntity>(true, "Список городов", service.findAll()));
    }

    @GetMapping
    @Operation(
            summary = "Поиск города по id"
    )
    public ResponseEntity<DataResponse<CityEntity>> by_id(@RequestParam Long id) {
        return ResponseEntity.ok(
                new DataResponse<CityEntity>(true, "Найден следующий город", service.findById(id).orElseThrow()));
    }

    @PostMapping
    @Operation(
            summary = "Добавить город"
    )
    public ResponseEntity<DataResponse<CityEntity>> save(@RequestBody CityEntity city) {
        return ResponseEntity.ok(
                new DataResponse<CityEntity>(true, "Город сохранен", service.save(city)));
    }

    @PutMapping
    @Operation(
            summary = "Обновить город"
    )
    public ResponseEntity<BaseResponse> update(@RequestBody CityEntity city) {
        service.update(city);
        return ResponseEntity.ok(
                new BaseResponse(true, "Город сохранен"));
    }
}
