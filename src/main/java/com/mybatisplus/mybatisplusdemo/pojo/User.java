package com.mybatisplus.mybatisplusdemo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user")
public class User implements Serializable {
        private Long id;
        private String name;
        private Integer age;
        private String email;
//        private transient String remark;
        @TableField(exist = false)
        private String remark;

        private Date time;

}
