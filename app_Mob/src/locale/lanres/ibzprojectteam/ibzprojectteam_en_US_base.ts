import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    role:  commonLogic.appcommonhandle("角色",null),
    limited:  commonLogic.appcommonhandle("受限用户",null),
    total:  commonLogic.appcommonhandle("总计可用",null),
    username:  commonLogic.appcommonhandle("用户",null),
    days:  commonLogic.appcommonhandle("可用工日",null),
    exitdate:  commonLogic.appcommonhandle("退场时间",null),
    type:  commonLogic.appcommonhandle("团队类型",null),
    order:  commonLogic.appcommonhandle("排序",null),
    id:  commonLogic.appcommonhandle("编号",null),
    consumed:  commonLogic.appcommonhandle("总计消耗",null),
    account:  commonLogic.appcommonhandle("用户",null),
    estimate:  commonLogic.appcommonhandle("最初预计",null),
    join:  commonLogic.appcommonhandle("加盟日",null),
    hours:  commonLogic.appcommonhandle("可用工时/天",null),
    taskCnt:  commonLogic.appcommonhandle("任务数",null),
    left:  commonLogic.appcommonhandle("预计剩余",null),
    pM:  commonLogic.appcommonhandle("项目经理",null),
    projectName:  commonLogic.appcommonhandle("所属项目",null),
    root:  commonLogic.appcommonhandle("项目编号",null),
  },
	views: {
		projectteammobmeditview: {
			caption: commonLogic.appcommonhandle("项目团队",null),
		},
	},
};