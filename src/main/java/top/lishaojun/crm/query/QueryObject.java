package top.lishaojun.crm.query;

import lombok.Data;

import java.util.List;

@Data
public class QueryObject {

    private int page;

    private int rows;

    private int getStart(){

        return (this.page -1) * this.rows;
    }
}
