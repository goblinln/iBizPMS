import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'teamstatus': commonLogic.appcommonhandle("成员状态",null),
		'account': commonLogic.appcommonhandle("用户",null),
		'join': commonLogic.appcommonhandle("加盟日",null),
		'left': commonLogic.appcommonhandle("预计剩余",null),
		'total': commonLogic.appcommonhandle("总计可用",null),
		'end': commonLogic.appcommonhandle("结束时间",null),
		'username': commonLogic.appcommonhandle("用户",null),
		'estimate': commonLogic.appcommonhandle("最初预计",null),
		'hours': commonLogic.appcommonhandle("可用工时/天",null),
		'taskcnt': commonLogic.appcommonhandle("任务数",null),
		'type': commonLogic.appcommonhandle("团队类型",null),
		'consumed': commonLogic.appcommonhandle("总计消耗",null),
		'days': commonLogic.appcommonhandle("可用工日",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'leadingcadre': commonLogic.appcommonhandle("当前负责人",null),
		'limited': commonLogic.appcommonhandle("受限用户",null),
		'order': commonLogic.appcommonhandle("排序",null),
		'role': commonLogic.appcommonhandle("角色",null),
		'root': commonLogic.appcommonhandle("产品编号",null),
	},
		views: {
			'usr2gridview': {
				caption: commonLogic.appcommonhandle("产品团队",null),
				title: commonLogic.appcommonhandle("产品团队表格视图",null),
			},
			'productteamlistview': {
				caption: commonLogic.appcommonhandle("产品团队",null),
				title: commonLogic.appcommonhandle("产品团队列表视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("产品团队",null),
				title: commonLogic.appcommonhandle("产品团队编辑视图",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("产品团队基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'group2': commonLogic.appcommonhandle("操作信息",null), 
				'formpage2': commonLogic.appcommonhandle("其它",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("用户",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'id': commonLogic.appcommonhandle("编号",null), 
			},
			uiactions: {
			},
		},
		productteamedit_grid: {
			columns: {
				'account': commonLogic.appcommonhandle("用户",null),
				'role': commonLogic.appcommonhandle("角色",null),
				'hours': commonLogic.appcommonhandle("可用工时/天",null),
				'limited': commonLogic.appcommonhandle("受限用户",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				newrow: commonLogic.appcommonhandle("新建行",null),
				remove: commonLogic.appcommonhandle("删除",null),
			},
		},
		productteam_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		productteamsearchform_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("表单分页",null), 
				'dept': commonLogic.appcommonhandle("选择部门",null), 
				'teams': commonLogic.appcommonhandle("复制团队",null), 
			},
			uiactions: {
			},
		},
		usr2gridviewtoolbar_toolbar: {
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("新建行",null),
				tip: commonLogic.appcommonhandle("新建行",null),
			},
			'deuiaction3': {
				caption: commonLogic.appcommonhandle("保存并关闭",null),
				tip: commonLogic.appcommonhandle("保存并关闭",null),
			},
		},
		editviewtoolbar_toolbar: {
			'tbitem3': {
				caption: commonLogic.appcommonhandle("保存",null),
				tip: commonLogic.appcommonhandle("保存",null),
			},
			'tbitem4': {
				caption: commonLogic.appcommonhandle("保存并新建",null),
				tip: commonLogic.appcommonhandle("保存并新建",null),
			},
			'tbitem5': {
				caption: commonLogic.appcommonhandle("保存并关闭",null),
				tip: commonLogic.appcommonhandle("保存并关闭",null),
			},
			'tbitem7': {
				caption: commonLogic.appcommonhandle("删除",null),
				tip: commonLogic.appcommonhandle("删除",null),
			},
		},
		productteamlistviewtoolbar_toolbar: {
			'deuiaction3_managermember': {
				caption: commonLogic.appcommonhandle("团队管理",null),
				tip: commonLogic.appcommonhandle("团队管理",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;