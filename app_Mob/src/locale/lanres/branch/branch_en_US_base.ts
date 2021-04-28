import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    name:  commonLogic.appcommonhandle("名称",null),
    dept:  commonLogic.appcommonhandle("归属部门",null),
    orgName:  commonLogic.appcommonhandle("归属组织名",null),
    updateBy:  commonLogic.appcommonhandle("由谁更新",null),
    deleted:  commonLogic.appcommonhandle("已删除",null),
    createBy:  commonLogic.appcommonhandle("由谁创建",null),
    id:  commonLogic.appcommonhandle("编号",null),
    order:  commonLogic.appcommonhandle("排序",null),
    org:  commonLogic.appcommonhandle("归属组织",null),
    deptName:  commonLogic.appcommonhandle("归属部门名",null),
    product:  commonLogic.appcommonhandle("所属产品",null),
  },
};