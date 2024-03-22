package bip.online.biplio2024.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class    BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор", example = "A-124523")
    private Long id;
    @NotNull
    @Schema(description = "Название", example = "A-124523")
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    @Schema(description = "Автора", example = "A-124523")
    private AuthorEntity author;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @Schema(description = "Издательство", example = "A-124523")
    private PublisherEntity publisher;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    @Schema(description = "Жанр", example = "A-124523")
    private GenreEntity genre;
    private String year;
}
