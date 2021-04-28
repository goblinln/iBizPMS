import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    deptId:  commonLogic.appcommonhandle("decimal",null),
    deptCode:  commonLogic.appcommonhandle("Cancel",null),
    deptName:  commonLogic.appcommonhandle("{0}/P",null),
    orgId:  commonLogic.appcommonhandle("单位",null),
    parentDeptid:  commonLogic.appcommonhandle("上级部门",null),
    shortName:  commonLogic.appcommonhandle("Others",null),
    deptLevel:  commonLogic.appcommonhandle("部门级别",null),
    domains:  commonLogic.appcommonhandle("区属",null),
    showOrder:  commonLogic.appcommonhandle("Audit",null),
    bCode:  commonLogic.appcommonhandle("Condition",null),
    leaderId:  commonLogic.appcommonhandle("分管领导标识",null),
    leaderName:  commonLogic.appcommonhandle("分管领导",null),
    orgName:  commonLogic.appcommonhandle("单位",null),
    parentDeptName:  commonLogic.appcommonhandle("上级部门",null),
    createDate:  commonLogic.appcommonhandle("建立时间",null),
    enable:  commonLogic.appcommonhandle("逻辑有效标志",null),
    updateMan:  commonLogic.appcommonhandle("更新人",null),
    updateDate:  commonLogic.appcommonhandle("更新时间",null),
    createMan:  commonLogic.appcommonhandle("建立人",null),
  },
};