package cn.ibizlab.pms.core.util.model;

import java.util.HashMap;
import java.util.Map;

public class DataEntityModelGlobalHelper {

    private static Map<String, IDataEntityModel> dataEntityModelMap = new HashMap<>();

    public static IDataEntityModel getDataEntityModel(String dataEntity) {
        return dataEntityModelMap.get(dataEntity);
    }

    public static void setDataEntityModel(String dataEntity, IDataEntityModel dataEntityModel) {
        dataEntityModelMap.put(dataEntity, dataEntityModel);
    }
}

