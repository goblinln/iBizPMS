import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    deptName:  commonLogic.appcommonhandle("归属部门名",null),
    role:  commonLogic.appcommonhandle("角色",null),
    createBy:  commonLogic.appcommonhandle("由谁创建",null),
    limited:  commonLogic.appcommonhandle("受限用户",null),
    dept:  commonLogic.appcommonhandle("归属部门",null),
    total:  commonLogic.appcommonhandle("总计可用",null),
    username:  commonLogic.appcommonhandle("用户",null),
    org:  commonLogic.appcommonhandle("归属组织",null),
    days:  commonLogic.appcommonhandle("可用工日",null),
    updateBy:  commonLogic.appcommonhandle("由谁更新",null),
    exitdate:  commonLogic.appcommonhandle("退场时间",null),
    type:  commonLogic.appcommonhandle("团队类型",null),
    order:  commonLogic.appcommonhandle("排序",null),
    id:  commonLogic.appcommonhandle("编号",null),
    orgName:  commonLogic.appcommonhandle("归属组织名",null),
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