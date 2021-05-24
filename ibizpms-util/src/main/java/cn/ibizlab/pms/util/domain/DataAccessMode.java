package cn.ibizlab.pms.util.domain;


import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataAccessMode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 访问模式（READ/UPDATE/DENY）
     */
    private int accessMode;
    /**
     * 编辑模式（指定/排除属性）
     */
    private String editMode;
    /**
     * 属性列表
     */
    private List<String> fieldMap;
}
