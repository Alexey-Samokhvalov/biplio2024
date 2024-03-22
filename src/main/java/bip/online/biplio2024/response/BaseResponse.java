package bip.online.biplio2024.response;

import bip.online.biplio2024.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    protected boolean success;
    protected String message;

}
