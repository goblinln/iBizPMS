import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'openedversion': commonLogic.appcommonhandle("当前系统版本",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'begin': commonLogic.appcommonhandle("开始时间",null),
		'hours': commonLogic.appcommonhandle("可用工时/天",null),
		'pmseeprojectinfo': commonLogic.appcommonhandle("项目立项信息",null),
		'acl': commonLogic.appcommonhandle("访问控制",null),
		'deleted': commonLogic.appcommonhandle("已删除",null),
		'totalconsumed': commonLogic.appcommonhandle("任务消耗总工时",null),
		'products': commonLogic.appcommonhandle("关联产品",null),
		'ycompletetaskcnt': commonLogic.appcommonhandle("已完成任务数",null),
		'plans': commonLogic.appcommonhandle("关联计划",null),
		'desc': commonLogic.appcommonhandle("项目描述",null),
		'temptaskcnt': commonLogic.appcommonhandle("临时任务数",null),
		'doclibcnt': commonLogic.appcommonhandle("文档数量",null),
		'mycompletetaskcnt': commonLogic.appcommonhandle("我完成任务数",null),
		'istop': commonLogic.appcommonhandle("是否置顶",null),
		'uncompletetaskcnt': commonLogic.appcommonhandle("未完成任务数",null),
		'mdeptname': commonLogic.appcommonhandle("归属部门名",null),
		'teamcnt': commonLogic.appcommonhandle("团队成员总数",null),
		'pm': commonLogic.appcommonhandle("项目负责人",null),
		'dept': commonLogic.appcommonhandle("选择部门",null),
		'id': commonLogic.appcommonhandle("项目编号",null),
		'name': commonLogic.appcommonhandle("项目名称",null),
		'substatus': commonLogic.appcommonhandle("子状态",null),
		'role': commonLogic.appcommonhandle("角色",null),
		'order': commonLogic.appcommonhandle("项目排序",null),
		'bugcnt': commonLogic.appcommonhandle("Bug总数",null),
		'rd': commonLogic.appcommonhandle("发布负责人",null),
		'managemembers': commonLogic.appcommonhandle("复制团队",null),
		'ystarttaskcnt': commonLogic.appcommonhandle("进行中任务数",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'whitelist': commonLogic.appcommonhandle("分组白名单",null),
		'mobimage': commonLogic.appcommonhandle("移动端图片",null),
		'totalwh': commonLogic.appcommonhandle("总工时",null),
		'projectteams': commonLogic.appcommonhandle("项目团队成员",null),
		'totalhours': commonLogic.appcommonhandle("可用工时",null),
		'pri': commonLogic.appcommonhandle("优先级",null),
		'end': commonLogic.appcommonhandle("结束日期",null),
		'canceleddate': commonLogic.appcommonhandle("取消日期",null),
		'plantaskcnt': commonLogic.appcommonhandle("计划任务数",null),
		'join': commonLogic.appcommonhandle("加盟日",null),
		'totalestimate': commonLogic.appcommonhandle("任务最初预计总工时",null),
		'mdeptid': commonLogic.appcommonhandle("部门标识",null),
		'totalleft': commonLogic.appcommonhandle("任务预计剩余总工时",null),
		'srfarray': commonLogic.appcommonhandle("关联数据数组",null),
		'code': commonLogic.appcommonhandle("项目代号",null),
		'period': commonLogic.appcommonhandle("时间段",null),
		'closetaskcnt': commonLogic.appcommonhandle("关闭任务数",null),
		'branchs': commonLogic.appcommonhandle("关联产品平台集合",null),
		'catid': commonLogic.appcommonhandle("catID",null),
		'orgid': commonLogic.appcommonhandle("组织标识",null),
		'unstarttaskcnt': commonLogic.appcommonhandle("未开始任务数",null),
		'accounts': commonLogic.appcommonhandle("项目团队相关成员",null),
		'noticeusers': commonLogic.appcommonhandle("消息通知用户",null),
		'statge': commonLogic.appcommonhandle("statge",null),
		'canceltaskcnt': commonLogic.appcommonhandle("取消任务数",null),
		'taskcnt': commonLogic.appcommonhandle("任务总数",null),
		'alltaskcnt': commonLogic.appcommonhandle("所有任务数",null),
		'supproreport': commonLogic.appcommonhandle("支持项目汇报",null),
		'canceledby': commonLogic.appcommonhandle("由谁取消",null),
		'iscat': commonLogic.appcommonhandle("isCat",null),
		'openeddate': commonLogic.appcommonhandle("创建日期",null),
		'unclosetaskcnt': commonLogic.appcommonhandle("未关闭任务数",null),
		'storychangecnt': commonLogic.appcommonhandle("需求变更数",null),
		'closedby': commonLogic.appcommonhandle("由谁关闭",null),
		'type': commonLogic.appcommonhandle("项目类型",null),
		'buildcnt': commonLogic.appcommonhandle("版本总数",null),
		'account': commonLogic.appcommonhandle("项目团队成员",null),
		'po': commonLogic.appcommonhandle("产品负责人",null),
		'asstomytaskcnt': commonLogic.appcommonhandle("指派给我任务数",null),
		'order1': commonLogic.appcommonhandle("项目排序",null),
		'status': commonLogic.appcommonhandle("项目状态",null),
		'moretaskcnt': commonLogic.appcommonhandle("更多任务数",null),
		'days': commonLogic.appcommonhandle("可用工作日",null),
		'cycletaskcnt': commonLogic.appcommonhandle("周期任务数",null),
		'team': commonLogic.appcommonhandle("团队名称",null),
		'closeddate': commonLogic.appcommonhandle("关闭日期",null),
		'comment': commonLogic.appcommonhandle("备注",null),
		'openedby': commonLogic.appcommonhandle("由谁创建",null),
		'storycnt': commonLogic.appcommonhandle("需求总数",null),
		'qd': commonLogic.appcommonhandle("测试负责人",null),
		'parentname': commonLogic.appcommonhandle("parent",null),
		'parent': commonLogic.appcommonhandle("父项目",null),
		'projectsn': commonLogic.appcommonhandle("项目编号",null),
	},
		views: {
			'curproductgridview': {
				caption: commonLogic.appcommonhandle("项目",null),
				title: commonLogic.appcommonhandle("project表格视图",null),
			},
		},
		main_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("编号",null),
				'name': commonLogic.appcommonhandle("项目名称",null),
				'code': commonLogic.appcommonhandle("项目代号",null),
				'status': commonLogic.appcommonhandle("项目状态",null),
				'end': commonLogic.appcommonhandle("结束日期",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		curproductgridviewtoolbar_toolbar: {
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
			'deuiaction4': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;