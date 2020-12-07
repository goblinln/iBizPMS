import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    deptid: commonLogic.appcommonhandle("部门标识",null),
    deptcode: commonLogic.appcommonhandle("部门代码",null),
    deptname: commonLogic.appcommonhandle("部门名称",null),
    orgid: commonLogic.appcommonhandle("单位",null),
    parentdeptid: commonLogic.appcommonhandle("上级部门",null),
    shortname: commonLogic.appcommonhandle("部门简称",null),
    deptlevel: commonLogic.appcommonhandle("部门级别",null),
    domains: commonLogic.appcommonhandle("区属",null),
    showorder: commonLogic.appcommonhandle("访问审计",null),
    bcode: commonLogic.appcommonhandle("业务编码",null),
    leaderid: commonLogic.appcommonhandle("分管领导标识",null),
    leadername: commonLogic.appcommonhandle("分管领导",null),
    orgname: commonLogic.appcommonhandle("单位",null),
    parentdeptname: commonLogic.appcommonhandle("上级部门",null),
    createdate: commonLogic.appcommonhandle("建立时间",null),
    enable: commonLogic.appcommonhandle("逻辑有效标志",null),
    createman: commonLogic.appcommonhandle("建立人",null),
    updateman: commonLogic.appcommonhandle("更新人",null),
    updatedate: commonLogic.appcommonhandle("更新时间",null),
  },
};