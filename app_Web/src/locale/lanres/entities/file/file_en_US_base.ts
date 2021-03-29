import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'pathname': commonLogic.appcommonhandle("路径",null),
		'objectid': commonLogic.appcommonhandle("对象ID",null),
		'strsize': commonLogic.appcommonhandle("显示大小",null),
		'doclibtype': commonLogic.appcommonhandle("文档类型",null),
		'deleted': commonLogic.appcommonhandle("已删除",null),
		'extension': commonLogic.appcommonhandle("文件类型",null),
		'objecttype': commonLogic.appcommonhandle("对象类型",null),
		'addedby': commonLogic.appcommonhandle("由谁添加",null),
		'title': commonLogic.appcommonhandle("标题",null),
		'addeddate': commonLogic.appcommonhandle("添加时间",null),
		'downloads': commonLogic.appcommonhandle("下载次数",null),
		'size': commonLogic.appcommonhandle("大小",null),
		'id': commonLogic.appcommonhandle("id",null),
		'extra': commonLogic.appcommonhandle("备注",null),
	},
		views: {
			'productgridview': {
				caption: commonLogic.appcommonhandle("附件",null),
				title: commonLogic.appcommonhandle("file表格视图",null),
			},
			'projectgridviewfile': {
				caption: commonLogic.appcommonhandle("附件",null),
				title: commonLogic.appcommonhandle("file表格视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("附件",null),
				title: commonLogic.appcommonhandle("file编辑视图",null),
			},
			'listview9': {
				caption: commonLogic.appcommonhandle("附件",null),
				title: commonLogic.appcommonhandle("附件",null),
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("file基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'group2': commonLogic.appcommonhandle("操作信息",null), 
				'formpage2': commonLogic.appcommonhandle("其它",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("标题",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'title': commonLogic.appcommonhandle("标题",null), 
				'id': commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			},
		},
		doclibl_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("文档编号",null),
				'title': commonLogic.appcommonhandle("附件名",null),
				'pathname': commonLogic.appcommonhandle("地址",null),
				'objecttype': commonLogic.appcommonhandle("类型",null),
				'strsize': commonLogic.appcommonhandle("大小",null),
				'addedby': commonLogic.appcommonhandle("由谁添加",null),
				'addeddate': commonLogic.appcommonhandle("添加时间",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			file_look: commonLogic.appcommonhandle("查看",null),
			file_ibzdownload: commonLogic.appcommonhandle("下载",null),
			file_delete: commonLogic.appcommonhandle("删除",null),
			},
		},
		list_list: {
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
		projectgridviewfiletoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("批量下载",null),
				tip: commonLogic.appcommonhandle("批量下载",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("全部下载",null),
				tip: commonLogic.appcommonhandle("全部下载",null),
			},
		},
		productgridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("批量下载",null),
				tip: commonLogic.appcommonhandle("批量下载",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("全部下载",null),
				tip: commonLogic.appcommonhandle("全部下载",null),
			},
		},
		editviewtoolbar_toolbar: {
			'tbitem3': {
				caption: commonLogic.appcommonhandle("Save",null),
				tip: commonLogic.appcommonhandle("Save",null),
			},
			'tbitem4': {
				caption: commonLogic.appcommonhandle("Save And New",null),
				tip: commonLogic.appcommonhandle("Save And New",null),
			},
			'tbitem5': {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
			'tbitem7': {
				caption: commonLogic.appcommonhandle("Remove And Close",null),
				tip: commonLogic.appcommonhandle("Remove And Close Window",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;