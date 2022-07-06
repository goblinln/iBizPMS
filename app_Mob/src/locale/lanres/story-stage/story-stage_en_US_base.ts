import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    id:  commonLogic.appcommonhandle("主键",null),
    dept:  commonLogic.appcommonhandle("归属部门",null),
    updateBy:  commonLogic.appcommonhandle("由谁更新",null),
    stagedBy:  commonLogic.appcommonhandle("设置阶段者",null),
    stage:  commonLogic.appcommonhandle("所处阶段",null),
    org:  commonLogic.appcommonhandle("归属组织",null),
    createBy:  commonLogic.appcommonhandle("由谁创建",null),
    orgName:  commonLogic.appcommonhandle("归属组织名",null),
    deptName:  commonLogic.appcommonhandle("归属部门名",null),
    story:  commonLogic.appcommonhandle("需求",null),
    branch:  commonLogic.appcommonhandle("平台/分支",null),
  },
};