package com.odev.repairapp.response.wrapper;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MyDeleteResponse{
    private Object status;
    private String message;
}