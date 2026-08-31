package com.vo;

import com.entity.Apply;
import com.entity.Userlist;
import lombok.Data;

@Data
public class ApplyBean extends Apply {

    private Userlist userlist;

}
