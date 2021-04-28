import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    casestepid:  commonLogic.appcommonhandle("用例步骤编号",null),
    dept:  commonLogic.appcommonhandle("归属部门",null),
    reals:  commonLogic.appcommonhandle("实际情况",null),
    steps:  commonLogic.appcommonhandle("测试结果",null),
    type:  commonLogic.appcommonhandle("用例步骤类型",null),
    createBy:  commonLogic.appcommonhandle("由谁创建",null),
    id:  commonLogic.appcommonhandle("编号",null),
    desc:  commonLogic.appcommonhandle("步骤",null),
    expect:  commonLogic.appcommonhandle("预期",null),
    files:  commonLogic.appcommonhandle("附件",null),
    updateBy:  commonLogic.appcommonhandle("由谁更新",null),
    runid:  commonLogic.appcommonhandle("执行编号",null),
    org:  commonLogic.appcommonhandle("归属组织",null),
    version:  commonLogic.appcommonhandle("用例版本",null),
    iBizCase:  commonLogic.appcommonhandle("用例",null),
    parent:  commonLogic.appcommonhandle("分组用例步骤的组编号",null),
    deptName:  commonLogic.appcommonhandle("归属部门名",null),
    orgName:  commonLogic.appcommonhandle("归属组织名",null),
  },
	views: {
		mobmdview9: {
			caption: commonLogic.appcommonhandle("用例步骤",null),
		},
	},
};