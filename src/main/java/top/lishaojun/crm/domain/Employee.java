package top.lishaojun.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

@Alias("Employee")
@Getter@Setter
public class Employee {
    private Long id;

    private String username;

    private String realname;

    private String password;

    private String tel;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date inputtime;

    private Boolean state;

    private Boolean admin;

    private Department dept;

    private List<Role> roles;


}