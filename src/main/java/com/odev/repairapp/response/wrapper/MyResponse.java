package com.odev.repairapp.response.wrapper;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class MyResponse<T>{
    private Object status;
    private String message;
    private T data;
}
