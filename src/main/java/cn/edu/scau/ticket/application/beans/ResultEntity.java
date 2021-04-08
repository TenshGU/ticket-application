package cn.edu.scau.ticket.application.beans;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JSONType(orders = {"code","msg","infoMap"})
public class ResultEntity {
    /**
     * status-code,such as:200,300,400...
     */
    private int code;
    /**
     * Description of status
     */
    private String msg;

    /**
     * Information of result
     */
    private Map<String,Object> infoMap = new HashMap<>();

    public static ResultEntity getResultEntity(ResultStatus rs) {
        ResultEntity entity = new ResultEntity();
        entity.setCode(rs.getCode());
        entity.setMsg(rs.getMsg());
        return entity;
    }

    public ResultEntity addInfo(Map<String,Object> map) {
        this.infoMap.putAll(map);
        return this;
    }

    public ResultEntity addInfo(String key, Object value) {
        this.infoMap.put(key, value);
        return this;
    }
}
