package bip.online.biplio2024.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "publishers")
public class PublisherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор", example = "A-124523")
    private Long id;
    @NotBlank()
    @Pattern(regexp = "[А-Я][а-я]{1,20}")
    @Schema(description = "Название", example = "A-124523")
    private String title;
    @ManyToOne
    @JoinColumn(name = "city_id")
    @Schema(description = "Город", example = "A-124523")
    private CityEntity city;
    @JsonIgnore
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private List<BookEntity> books;
}
