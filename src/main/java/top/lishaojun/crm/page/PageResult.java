package top.lishaojun.crm.page;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter@Setter
public class PageResult {

    private Long total;

    private List rows;

    public static  final PageResult EMPTY = new PageResult(0L, Collections.EMPTY_LIST);


    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult() {
    }
}
