package com.example.helloworld.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Book {
    @ApiModelProperty(required = true, notes = "书名", example = "平凡的世界")
    private String name;
    @ApiModelProperty(required = false, notes = "书本描述", example = "陕北大地关于奋斗、爱情、改革的史诗级著作")
    private String description;
}
