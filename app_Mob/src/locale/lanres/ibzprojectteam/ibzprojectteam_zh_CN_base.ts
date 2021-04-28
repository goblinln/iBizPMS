import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    role: "角色",
    limited: "受限用户",
    total: "总计可用",
    username: "用户",
    days: "可用工日",
    exitdate: "退场时间",
    type: "团队类型",
    order: "排序",
    id: "编号",
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