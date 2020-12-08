import commonLogic from '@/locale/logic/common/common-logic';

export default {
  fields: {
    ibzplantempletdetailid: commonLogic.appcommonhandle("计划模板详情标识",null),
    ibzplantempletdetailname: commonLogic.appcommonhandle("计划模板详情名称",null),
    createman: commonLogic.appcommonhandle("建立人",null),
    createdate: commonLogic.appcommonhandle("建立时间",null),
    updateman: commonLogic.appcommonhandle("更新人",null),
    updatedate: commonLogic.appcommonhandle("更新时间",null),
    plantempletid: commonLogic.appcommonhandle("产品计划模板标识",null),
    plancode: commonLogic.appcommonhandle("计划编号",null),
    order: commonLogic.appcommonhandle("排序",null),
    desc: commonLogic.appcommonhandle("计划名称",null),
    expect: commonLogic.appcommonhandle("描述",null),
    type: commonLogic.appcommonhandle("类型",null),
  },
};