package top.lishaojun.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter
@Setter
@ToString
@Alias("Menu")
public class Menu {


    private Long id;

    private String text;

    private String iconcls;

    private Boolean checked;

    private String state;

    private String attributes;

    private List<Menu> children;

    private String function;

}