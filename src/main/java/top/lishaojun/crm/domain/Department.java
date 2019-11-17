package top.lishaojun.crm.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("Department")
@Setter @Getter
public class Department {
    private Long id;

    private String sn;

    private String name;

    private Employee manager;

    private Department parent;

    private Boolean state;




}