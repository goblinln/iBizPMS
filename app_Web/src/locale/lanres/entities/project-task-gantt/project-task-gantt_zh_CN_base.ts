import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'canceledby': commonLogic.appcommonhandle("由谁取消",null),
		'configtype': commonLogic.appcommonhandle("周期类型",null),
		'taskteams': commonLogic.appcommonhandle("项目团队成员",null),
		'storystatus': commonLogic.appcommonhandle("需求状态",null),
		'left': commonLogic.appcommonhandle("预计剩余",null),
		'isfavorites': commonLogic.appcommonhandle("是否收藏",null),
		'configend': commonLogic.appcommonhandle("过期日期",null),
		'hasdetail': commonLogic.appcommonhandle("是否填写描述",null),
		'openeddate': commonLogic.appcommonhandle("创建日期",null),
		'assign': commonLogic.appcommonhandle("是否指派",null),
		'color': commonLogic.appcommonhandle("标题颜色",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'finishedby': commonLogic.appcommonhandle("由谁完成",null),
		'mytotaltime': commonLogic.appcommonhandle("我的总消耗",null),
		'mailtopk': commonLogic.appcommonhandle("抄送给",null),
		'finishedlist': commonLogic.appcommonhandle("完成者列表",null),
		'modulename1': commonLogic.appcommonhandle("所属模块",null),
		'isleaf': commonLogic.appcommonhandle("是否子任务",null),
		'realstarted': commonLogic.appcommonhandle("实际开始",null),
		'status1': commonLogic.appcommonhandle("任务状态",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'replycount': commonLogic.appcommonhandle("回复数量",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'configbegin': commonLogic.appcommonhandle("开始日期",null),
		'updatedate': commonLogic.appcommonhandle("最后的更新日期",null),
		'noticeusers': commonLogic.appcommonhandle("消息通知用户",null),
		'closedby': commonLogic.appcommonhandle("由谁关闭",null),
		'storyversionnew': commonLogic.appcommonhandle("相关需求最新版本",null),
		'currentconsumed': commonLogic.appcommonhandle("本次消耗",null),
		'files': commonLogic.appcommonhandle("附件",null),
		'substatus': commonLogic.appcommonhandle("子状态",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'closedreason': commonLogic.appcommonhandle("关闭原因",null),
		'taskspecies': commonLogic.appcommonhandle("任务种别",null),
		'lastediteddate': commonLogic.appcommonhandle("最后修改日期",null),
		'configday': commonLogic.appcommonhandle("间隔天数",null),
		'assigneddate': commonLogic.appcommonhandle("指派日期",null),
		'pri': commonLogic.appcommonhandle("优先级",null),
		'lasteditedby': commonLogic.appcommonhandle("最后修改",null),
		'idvalue': commonLogic.appcommonhandle("关联编号",null),
		'status': commonLogic.appcommonhandle("任务状态",null),
		'multiple': commonLogic.appcommonhandle("多人任务",null),
		'name': commonLogic.appcommonhandle("任务名称",null),
		'closeddate': commonLogic.appcommonhandle("关闭时间",null),
		'inputcost': commonLogic.appcommonhandle("投入成本",null),
		'totaltime': commonLogic.appcommonhandle("总计耗时",null),
		'type': commonLogic.appcommonhandle("任务类型",null),
		'assignedto': commonLogic.appcommonhandle("指派给",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'ibztaskestimates': commonLogic.appcommonhandle("工时",null),
		'storyversion': commonLogic.appcommonhandle("需求版本",null),
		'delay': commonLogic.appcommonhandle("延期",null),
		'desc': commonLogic.appcommonhandle("任务描述",null),
		'eststarted': commonLogic.appcommonhandle("预计开始",null),
		'deadline': commonLogic.appcommonhandle("截止日期",null),
		'statusorder': commonLogic.appcommonhandle("排序",null),
		'mailtoconact': commonLogic.appcommonhandle("联系人",null),
		'deleted': commonLogic.appcommonhandle("已删除",null),
		'cycle': commonLogic.appcommonhandle("周期",null),
		'mailto': commonLogic.appcommonhandle("抄送给",null),
		'consumed': commonLogic.appcommonhandle("总计消耗",null),
		'estimate': commonLogic.appcommonhandle("最初预计",null),
		'openedby': commonLogic.appcommonhandle("由谁创建",null),
		'isfinished': commonLogic.appcommonhandle("是否完成",null),
		'canceleddate': commonLogic.appcommonhandle("取消时间",null),
		'configmonth': commonLogic.appcommonhandle("周期设置月",null),
		'comment': commonLogic.appcommonhandle("备注",null),
		'duration': commonLogic.appcommonhandle("持续时间",null),
		'assignedtozj': commonLogic.appcommonhandle("转交给",null),
		'usernames': commonLogic.appcommonhandle("团队用户",null),
		'myconsumed': commonLogic.appcommonhandle("之前消耗",null),
		'configweek': commonLogic.appcommonhandle("周期设置周几",null),
		'tasktype': commonLogic.appcommonhandle("任务类型",null),
		'allmodules': commonLogic.appcommonhandle("所有模块",null),
		'configbeforedays': commonLogic.appcommonhandle("提前天数",null),
		'finisheddate': commonLogic.appcommonhandle("实际完成",null),
		'progressrate': commonLogic.appcommonhandle("进度",null),
		'modulename': commonLogic.appcommonhandle("所属模块",null),
		'storyname': commonLogic.appcommonhandle("相关需求",null),
		'path': commonLogic.appcommonhandle("模块路径",null),
		'planname': commonLogic.appcommonhandle("所属计划",null),
		'projectname': commonLogic.appcommonhandle("所属项目",null),
		'product': commonLogic.appcommonhandle("产品",null),
		'productname': commonLogic.appcommonhandle("产品",null),
		'parentname': commonLogic.appcommonhandle("父任务",null),
		'project': commonLogic.appcommonhandle("所属项目",null),
		'plan': commonLogic.appcommonhandle("编号",null),
		'module': commonLogic.appcommonhandle("模块",null),
		'story': commonLogic.appcommonhandle("相关需求",null),
		'parent': commonLogic.appcommonhandle("父任务",null),
		'frombug': commonLogic.appcommonhandle("来源Bug",null),
		'tasksn': commonLogic.appcommonhandle("任务编号",null),
		'createman': commonLogic.appcommonhandle("建立人",null),
		'updateman': commonLogic.appcommonhandle("更新人",null),
	},
		views: {
			'tasktypeganttview': {
				caption: commonLogic.appcommonhandle("任务",null),
				title: commonLogic.appcommonhandle("任务甘特视图",null),
			},
		},
		typegantt_gantt: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;