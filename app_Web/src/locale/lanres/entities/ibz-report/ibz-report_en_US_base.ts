import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		updatemanname: commonLogic.appcommonhandle("更新人名称",null),
		mailto: commonLogic.appcommonhandle("抄送给",null),
		ibzdailyid: commonLogic.appcommonhandle("汇报标识",null),
		reportlycnt: commonLogic.appcommonhandle("未读汇报数",null),
		updatedate: commonLogic.appcommonhandle("更新时间",null),
		worktoday: commonLogic.appcommonhandle("工作",null),
		monthlycnt: commonLogic.appcommonhandle("未读月报数",null),
		dailycnt: commonLogic.appcommonhandle("未读日报数",null),
		createdate: commonLogic.appcommonhandle("建立时间",null),
		todaytask: commonLogic.appcommonhandle("完成任务",null),
		account: commonLogic.appcommonhandle("用户",null),
		type: commonLogic.appcommonhandle("类型",null),
		createman: commonLogic.appcommonhandle("建立人",null),
		updateman: commonLogic.appcommonhandle("更新人",null),
		ibzdailyname: commonLogic.appcommonhandle("汇报名称",null),
		reportto: commonLogic.appcommonhandle("汇报给",null),
		submittime: commonLogic.appcommonhandle("提交时间",null),
		files: commonLogic.appcommonhandle("附件",null),
		createmanname: commonLogic.appcommonhandle("建立人名称",null),
		planstomorrow: commonLogic.appcommonhandle("计划",null),
		reportstatus: commonLogic.appcommonhandle("状态",null),
		date: commonLogic.appcommonhandle("日期",null),
		issubmit: commonLogic.appcommonhandle("是否提交",null),
		comment: commonLogic.appcommonhandle("其他事项",null),
		tomorrowplanstask: commonLogic.appcommonhandle("计划任务",null),
	},
		views: {
			mytabexpview: {
				caption: commonLogic.appcommonhandle("我收到的",null),
				title: commonLogic.appcommonhandle("汇报分页导航视图（我收到的）",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;