import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    casestepid: "用例步骤编号",
    dept: "归属部门",
    reals: "实际情况",
    steps: "测试结果",
    type: "用例步骤类型",
    createby: "由谁创建",
    id: "编号",
    desc: "步骤",
    expect: "预期",
    files: "附件",
    updateby: "由谁更新",
    runid: "执行编号",
    org: "归属组织",
    version: "用例版本",
    ibizcase: "用例",
    parent: "分组用例步骤的组编号",
    deptname: "归属部门名",
    orgname: "归属组织名",
  },
	views: {
		mobmdview9: {
			caption: commonLogic.appcommonhandle("用例步骤",null),
		},
	},
};