package com.pione.categoryservice;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullCategoryResponse {

    private String name;

    List<blog> blogs;


}
