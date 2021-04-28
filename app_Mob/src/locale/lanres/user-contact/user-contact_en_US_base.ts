import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    userList:  commonLogic.appcommonhandle("userList",null),
    org:  commonLogic.appcommonhandle("归属组织",null),
    listName:  commonLogic.appcommonhandle("标题",null),
    id:  commonLogic.appcommonhandle("id",null),
    updateBy:  commonLogic.appcommonhandle("由谁更新",null),
    dept:  commonLogic.appcommonhandle("归属部门",null),
    account:  commonLogic.appcommonhandle("account",null),
    deptName:  commonLogic.appcommonhandle("归属部门名",null),
    orgName:  commonLogic.appcommonhandle("归属组织名",null),
  },
};