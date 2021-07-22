import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    estimate:  commonLogic.appcommonhandle("最初预计",null),
    org:  commonLogic.appcommonhandle("归属组织",null),
    updateBy:  commonLogic.appcommonhandle("由谁更新",null),
    username:  commonLogic.appcommonhandle("用户",null),
    join:  commonLogic.appcommonhandle("加盟日",null),
    hours:  commonLogic.appcommonhandle("可用工时/天",null),
    account:  commonLogic.appcommonhandle("用户",null),
    order:  commonLogic.appcommonhandle("排序",null),
    left:  commonLogic.appcommonhandle("预计剩余",null),
    deptName:  commonLogic.appcommonhandle("归属部门名",null),
    createBy:  commonLogic.appcommonhandle("由谁创建",null),
    consumed:  commonLogic.appcommonhandle("总计消耗",null),
    limited:  commonLogic.appcommonhandle("受限用户",null),
    role:  commonLogic.appcommonhandle("角色",null),
    dept:  commonLogic.appcommonhandle("归属部门",null),
    id:  commonLogic.appcommonhandle("编号",null),
    days:  commonLogic.appcommonhandle("可用工日",null),
    total:  commonLogic.appcommonhandle("总计可用",null),
    orgName:  commonLogic.appcommonhandle("归属组织名",null),
    type:  commonLogic.appcommonhandle("团队类型",null),
    root:  commonLogic.appcommonhandle("编号",null),
  },
	views: {
		mobmeditview9: {
			caption: commonLogic.appcommonhandle("任务预计",null),
		},
		mobeditview9: {
			caption: commonLogic.appcommonhandle("任务预计",null),
		},
	},
	newform_form: {
		details: {
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("编号",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			dates: commonLogic.appcommonhandle("日期",null), 
			consumed: commonLogic.appcommonhandle("工时",null), 
			left: commonLogic.appcommonhandle("剩余",null), 
			work: commonLogic.appcommonhandle("备注",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
};