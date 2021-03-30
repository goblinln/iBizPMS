import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'teamid': commonLogic.appcommonhandle("组标识",null),
		'teamname': commonLogic.appcommonhandle("组名称",null),
		'memo': commonLogic.appcommonhandle("备注",null),
		'domains': commonLogic.appcommonhandle("区属",null),
	},
		views: {
			'gridexpview': {
				caption: commonLogic.appcommonhandle("组",null),
				title: commonLogic.appcommonhandle("组表格导航视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("组",null),
				title: commonLogic.appcommonhandle("组编辑视图",null),
			},
			'usr2gridview': {
				caption: commonLogic.appcommonhandle("组",null),
				title: commonLogic.appcommonhandle("组表格视图",null),
			},
			'pickupgridview': {
				caption: commonLogic.appcommonhandle("组",null),
				title: commonLogic.appcommonhandle("组选择表格视图",null),
			},
			'pickupview': {
				caption: commonLogic.appcommonhandle("组",null),
				title: commonLogic.appcommonhandle("组数据选择视图",null),
			},
			'gridview': {
				caption: commonLogic.appcommonhandle("组",null),
				title: commonLogic.appcommonhandle("组表格视图",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("组基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'group2': commonLogic.appcommonhandle("操作信息",null), 
				'formpage2': commonLogic.appcommonhandle("其它",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("组标识",null), 
				'srfmajortext': commonLogic.appcommonhandle("组名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'teamid': commonLogic.appcommonhandle("组标识",null), 
			},
			uiactions: {
			},
		},
		mainexp_grid: {
			columns: {
				'teamid': commonLogic.appcommonhandle("组标识",null),
				'teamname': commonLogic.appcommonhandle("组名称",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				'teamid': commonLogic.appcommonhandle("组标识",null),
				'teamname': commonLogic.appcommonhandle("组名称",null),
				'domains': commonLogic.appcommonhandle("区属",null),
				'memo': commonLogic.appcommonhandle("备注",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		default_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("常规条件",null), 
			},
			uiactions: {
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
		usr2gridviewtoolbar_toolbar: {
		},
		gridviewtoolbar_toolbar: {
			'tbitem3': {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
			'tbitem4': {
				caption: commonLogic.appcommonhandle("编辑",null),
				tip: commonLogic.appcommonhandle("编辑",null),
			},
			'tbitem6': {
				caption: commonLogic.appcommonhandle("拷贝",null),
				tip: commonLogic.appcommonhandle("拷贝",null),
			},
			'tbitem7': {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'tbitem8': {
				caption: commonLogic.appcommonhandle("删除",null),
				tip: commonLogic.appcommonhandle("删除",null),
			},
			'tbitem9': {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'tbitem13': {
				caption: commonLogic.appcommonhandle("导出",null),
				tip: commonLogic.appcommonhandle("导出",null),
			},
			'tbitem10': {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'tbitem16': {
				caption: commonLogic.appcommonhandle("其它",null),
				tip: commonLogic.appcommonhandle("其它",null),
			},
			'tbitem21': {
				caption: commonLogic.appcommonhandle("导出数据模型",null),
				tip: commonLogic.appcommonhandle("导出数据模型",null),
			},
			'tbitem23': {
				caption: commonLogic.appcommonhandle("数据导入",null),
				tip: commonLogic.appcommonhandle("数据导入",null),
			},
			'tbitem17': {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			'tbitem19': {
				caption: commonLogic.appcommonhandle("过滤",null),
				tip: commonLogic.appcommonhandle("过滤",null),
			},
			'tbitem18': {
				caption: commonLogic.appcommonhandle("帮助",null),
				tip: commonLogic.appcommonhandle("帮助",null),
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;