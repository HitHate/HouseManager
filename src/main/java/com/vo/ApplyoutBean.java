package com.vo;

import com.entity.Applyout;
import com.entity.Userlist;
import lombok.Data;

@Data
public class ApplyoutBean extends Applyout {
    private Userlist userlist;
}
