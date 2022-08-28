package com.example.demo.to;

import lombok.*;

import java.util.Locale;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookTO extends AbstractTO {

    private Long id;
    private String name;
    private String author;
    private String isbn;

    public Character getFirstLetter(){
        return name.toLowerCase(Locale.ROOT).charAt(0);
    }

}
