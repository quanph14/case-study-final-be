package com.codegym.webthuenha.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class EmailDetails {
//    nguoi nhan
    private String recipient;
//    body
    private String msgBody;
//    tieu de
    private String subject;
    private String attachment;
}
