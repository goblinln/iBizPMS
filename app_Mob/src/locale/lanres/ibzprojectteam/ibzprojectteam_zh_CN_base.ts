import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    deptname: "归属部门名",
    role: "角色",
    createby: "由谁创建",
    limited: "受限用户",
    dept: "归属部门",
    total: "总计可用",
    username: "用户",
    org: "归属组织",
    days: "可用工日",
    updateby: "由谁更新",
    exitdate: "退场时间",
    type: "团队类型",
    order: "排序",
    id: "编号",
    orgname: "归属组织名",
    consumed: "总计消耗",
    account: "用户",
    estimate: "最初预计",
    join: "加盟日",
    hours: "可用工时/天",
    taskcnt: "任务数",
    left: "预计剩余",
    pm: "项目经理",
    projectname: "所属项目",
    root: "项目编号",
  },
	views: {
		projectteammobmeditview: {
			caption: commonLogic.appcommonhandle("项目团队",null),
		},
	},
};