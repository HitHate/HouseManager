package com.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SearchBean {
    private String search;
    private String select;
    private Integer cur;
    private Integer size;
    private String username;
    private Integer user_id;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date date;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date date1;
}
