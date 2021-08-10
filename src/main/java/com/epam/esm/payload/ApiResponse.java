package com.epam.esm.payload;

import com.epam.esm.enums.HttpStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@Builder
public class ApiResponse  {
    private String message;
    private HttpStatus codes;
    private Object object;


    public ApiResponse(String message, HttpStatus codes) {
        this.message = message;
        this.codes = codes;
    }

    public ApiResponse(String message, HttpStatus codes, Object object) {
        this.message = message;
        this.codes = codes;
        this.object = object;
    }
}
