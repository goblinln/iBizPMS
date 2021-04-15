package cn.ibizlab.pms.core.es.domain;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.annotation.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.Data;

@Data
@Document(indexName = "ibizpms", type = "ibizproindex", shards = 5, replicas = 1)
public class IbizproIndex implements Serializable {

    private static final long serialVersionUID = 1L;

}
