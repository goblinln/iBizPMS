import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'end': commonLogic.appcommonhandle("结束日期",null),
		'begin': commonLogic.appcommonhandle("开始日期",null),
		'ownerpk': commonLogic.appcommonhandle("负责人（选择）",null),
		'mailto': commonLogic.appcommonhandle("抄送给",null),
		'casecnt': commonLogic.appcommonhandle("用例数",null),
		'mailtopk': commonLogic.appcommonhandle("抄送给",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'pri': commonLogic.appcommonhandle("优先级",null),
		'comment': commonLogic.appcommonhandle("备注",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'substatus': commonLogic.appcommonhandle("子状态",null),
		'report': commonLogic.appcommonhandle("report",null),
		'desc': commonLogic.appcommonhandle("描述",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'status': commonLogic.appcommonhandle("当前状态",null),
		'mailtoconact': commonLogic.appcommonhandle("联系人",null),
		'owner': commonLogic.appcommonhandle("负责人",null),
		'deleted': commonLogic.appcommonhandle("已删除",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'auto': commonLogic.appcommonhandle("auto",null),
		'name': commonLogic.appcommonhandle("名称",null),
		'buildname': commonLogic.appcommonhandle("版本",null),
		'productname': commonLogic.appcommonhandle("产品",null),
		'projecttname': commonLogic.appcommonhandle("项目",null),
		'product': commonLogic.appcommonhandle("所属产品",null),
		'build': commonLogic.appcommonhandle("版本",null),
		'project': commonLogic.appcommonhandle("所属项目",null),
		'testtasksn': commonLogic.appcommonhandle("测试版本编号",null),
		'createman': commonLogic.appcommonhandle("建立人",null),
		'createdate': commonLogic.appcommonhandle("建立时间",null),
		'updateman': commonLogic.appcommonhandle("更新人",null),
		'updatedate': commonLogic.appcommonhandle("更新时间",null),
	},
		views: {
			'myygridview': {
				caption: commonLogic.appcommonhandle("测试版本",null),
				title: commonLogic.appcommonhandle("测试单表格视图",null),
			},
			'mydgridview': {
				caption: commonLogic.appcommonhandle("测试版本",null),
				title: commonLogic.appcommonhandle("测试单表格视图",null),
			},
		},
		main_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("编号",null),
				'name': commonLogic.appcommonhandle("名称",null),
				'productname': commonLogic.appcommonhandle("产品",null),
				'projecttname': commonLogic.appcommonhandle("项目",null),
				'buildname': commonLogic.appcommonhandle("版本",null),
				'owner': commonLogic.appcommonhandle("负责人",null),
				'begin': commonLogic.appcommonhandle("开始日期",null),
				'end': commonLogic.appcommonhandle("结束日期",null),
				'status': commonLogic.appcommonhandle("当前状态",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
		exportColumns: {
				'testtasksn': commonLogic.appcommonhandle("编号",null),
				'id': commonLogic.appcommonhandle("编号",null),
				'name': commonLogic.appcommonhandle("名称",null),
				'productname': commonLogic.appcommonhandle("产品",null),
				'projecttname': commonLogic.appcommonhandle("项目",null),
				'product': commonLogic.appcommonhandle("所属产品",null),
				'project': commonLogic.appcommonhandle("所属项目",null),
				'build': commonLogic.appcommonhandle("版本",null),
				'buildname': commonLogic.appcommonhandle("版本",null),
				'owner': commonLogic.appcommonhandle("负责人",null),
				'begin': commonLogic.appcommonhandle("开始日期",null),
				'end': commonLogic.appcommonhandle("结束日期",null),
				'status': commonLogic.appcommonhandle("当前状态",null),
		},
			uiactions: {
			accounttesttask_linkcase: commonLogic.appcommonhandle("关联用例",null),
			accounttesttask_testreportr: commonLogic.appcommonhandle("测试报告",null),
			accounttesttask_openinfoview: commonLogic.appcommonhandle("概况",null),
			accounttesttask_editedit: commonLogic.appcommonhandle("编辑",null),
			accounttesttask_delete: commonLogic.appcommonhandle("删除",null),
			},
		},
		mydgridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
		myygridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;