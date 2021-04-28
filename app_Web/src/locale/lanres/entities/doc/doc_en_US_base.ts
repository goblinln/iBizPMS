import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'groups': commonLogic.appcommonhandle("分组",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'content': commonLogic.appcommonhandle("文档正文",null),
		'docqtype': commonLogic.appcommonhandle("文档查询类型",null),
		'editeddate': commonLogic.appcommonhandle("更新时间",null),
		'views': commonLogic.appcommonhandle("views",null),
		'version': commonLogic.appcommonhandle("版本号",null),
		'editedby': commonLogic.appcommonhandle("由谁更新",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'id': commonLogic.appcommonhandle("文档编号",null),
		'title': commonLogic.appcommonhandle("文档标题",null),
		'deleted': commonLogic.appcommonhandle("已删除",null),
		'recentupdatecnt': commonLogic.appcommonhandle("最近更新数量",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'type': commonLogic.appcommonhandle("文档类型",null),
		'alldoccnt': commonLogic.appcommonhandle("所有文档数量",null),
		'addeddate': commonLogic.appcommonhandle("添加时间",null),
		'acl': commonLogic.appcommonhandle("权限",null),
		'mydoccnt': commonLogic.appcommonhandle("我的文档数量",null),
		'url': commonLogic.appcommonhandle("文档链接",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'doccnt': commonLogic.appcommonhandle("文档数",null),
		'myfavouritecnt': commonLogic.appcommonhandle("我的收藏数量",null),
		'users': commonLogic.appcommonhandle("用户",null),
		'recentaddcnt': commonLogic.appcommonhandle("最近添加数量",null),
		'keywords': commonLogic.appcommonhandle("关键字",null),
		'isfavourites': commonLogic.appcommonhandle("是否收藏",null),
		'collector': commonLogic.appcommonhandle("收藏者",null),
		'addedby': commonLogic.appcommonhandle("由谁添加",null),
		'todayupdatecnt': commonLogic.appcommonhandle("今日更新数量",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'files': commonLogic.appcommonhandle("附件",null),
		'productname': commonLogic.appcommonhandle("所属产品",null),
		'projectname': commonLogic.appcommonhandle("所属项目",null),
		'libname': commonLogic.appcommonhandle("所属文档库",null),
		'modulename': commonLogic.appcommonhandle("模块分类",null),
		'lib': commonLogic.appcommonhandle("所属文档库",null),
		'project': commonLogic.appcommonhandle("所属项目",null),
		'product': commonLogic.appcommonhandle("所属产品",null),
		'module': commonLogic.appcommonhandle("所属分类",null),
	},
		views: {
			'basiceditview9': {
				caption: commonLogic.appcommonhandle("基本信息",null),
				title: commonLogic.appcommonhandle("文档编辑视图（基本信息）",null),
			},
			'myfavouritegridview': {
				caption: commonLogic.appcommonhandle("我的收藏",null),
				title: commonLogic.appcommonhandle("doc表格视图",null),
			},
			'myfavouritepartgridview': {
				caption: commonLogic.appcommonhandle("我的收藏",null),
				title: commonLogic.appcommonhandle("文档表格视图",null),
			},
			'recentmoregridview': {
				caption: commonLogic.appcommonhandle("最近更新",null),
				title: commonLogic.appcommonhandle("doc表格视图",null),
			},
			'modulegridview': {
				caption: commonLogic.appcommonhandle("文档",null),
				title: commonLogic.appcommonhandle("doc表格视图",null),
			},
			'gridview': {
				caption: commonLogic.appcommonhandle("文档",null),
				title: commonLogic.appcommonhandle("doc表格视图",null),
			},
			'docmodulegridview': {
				caption: commonLogic.appcommonhandle("文档",null),
				title: commonLogic.appcommonhandle("doc表格视图",null),
			},
			'mianbeditview9': {
				caption: commonLogic.appcommonhandle("正文",null),
				title: commonLogic.appcommonhandle("文档编辑视图（正文）",null),
			},
			'moremyfavouritestreeview': {
				caption: commonLogic.appcommonhandle("我的收藏",null),
				title: commonLogic.appcommonhandle("文档树视图",null),
			},
			'gridview10': {
				caption: commonLogic.appcommonhandle("最近更新",null),
				title: commonLogic.appcommonhandle("文档表格视图",null),
			},
			'keditview9': {
				caption: commonLogic.appcommonhandle("关键字",null),
				title: commonLogic.appcommonhandle("文档编辑视图（关键字）",null),
			},
			'dashboardview': {
				caption: commonLogic.appcommonhandle("文档",null),
				title: commonLogic.appcommonhandle("文档数据看板视图",null),
			},
			'docsumeditview9': {
				caption: commonLogic.appcommonhandle("文档摘要",null),
				title: commonLogic.appcommonhandle("文档编辑视图（文档摘要）",null),
			},
			'gridviewlookdoc': {
				caption: commonLogic.appcommonhandle("文档",null),
				title: commonLogic.appcommonhandle("doc表格视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("文档",null),
				title: commonLogic.appcommonhandle("doc编辑视图",null),
			},
			'mymoregridview': {
				caption: commonLogic.appcommonhandle("我的文档",null),
				title: commonLogic.appcommonhandle("doc表格视图",null),
			},
			'gridview9': {
				caption: commonLogic.appcommonhandle("最近更新",null),
				title: commonLogic.appcommonhandle("文档表格视图",null),
			},
			'docstatuseditview': {
				caption: commonLogic.appcommonhandle("嵌入文档库主页",null),
				title: commonLogic.appcommonhandle("文档编辑视图",null),
			},
			'moremydoctreeview': {
				caption: commonLogic.appcommonhandle("我的文档",null),
				title: commonLogic.appcommonhandle("文档树视图",null),
			},
			'createeditview': {
				caption: commonLogic.appcommonhandle("文档",null),
				title: commonLogic.appcommonhandle("创建文档",null),
			},
			'morerecentupdatetreeview': {
				caption: commonLogic.appcommonhandle("最近更新",null),
				title: commonLogic.appcommonhandle("文档树视图",null),
			},
		},
		createeditform_form: {
			details: {
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("文档编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("文档标题",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'lib': commonLogic.appcommonhandle("所属文档库",null), 
				'libname': commonLogic.appcommonhandle("所属文档库",null), 
				'module': commonLogic.appcommonhandle("所属分类",null), 
				'modulename': commonLogic.appcommonhandle("所属分类",null), 
				'title': commonLogic.appcommonhandle("文档标题",null), 
				'keywords': commonLogic.appcommonhandle("关键字",null), 
				'type': commonLogic.appcommonhandle("文档类型",null), 
				'content': commonLogic.appcommonhandle("文档正文",null), 
				'url': commonLogic.appcommonhandle("文档链接",null), 
				'files': commonLogic.appcommonhandle("附件",null), 
				'acl': commonLogic.appcommonhandle("权限",null), 
				'id': commonLogic.appcommonhandle("文档编号",null), 
			},
			uiactions: {
			},
		},
		mainbody_form: {
			details: {
				'druipart1': commonLogic.appcommonhandle("",null), 
				'grouppanel1': commonLogic.appcommonhandle("附件",null), 
				'group1': commonLogic.appcommonhandle("文档基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("文档编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("文档标题",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'id': commonLogic.appcommonhandle("",null), 
				'title': commonLogic.appcommonhandle("",null), 
				'version': commonLogic.appcommonhandle("",null), 
				'content': commonLogic.appcommonhandle("",null), 
			},
			uiactions: {
			},
		},
		docsummary_form: {
			details: {
				'grouppanel1': commonLogic.appcommonhandle("文档摘要",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("文档编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("文档标题",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'id': commonLogic.appcommonhandle("文档编号",null), 
			},
			uiactions: {
			},
		},
		keywords_form: {
			details: {
				'grouppanel1': commonLogic.appcommonhandle("关键字",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("文档编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("文档标题",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'keywords': commonLogic.appcommonhandle("",null), 
				'id': commonLogic.appcommonhandle("文档编号",null), 
			},
			uiactions: {
			},
		},
		main_form: {
			details: {
				'group1': commonLogic.appcommonhandle("doc基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("文档编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("文档标题",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'projectname': commonLogic.appcommonhandle("所属项目",null), 
				'productname': commonLogic.appcommonhandle("所属产品",null), 
				'libname': commonLogic.appcommonhandle("所属文档库",null), 
				'modulename': commonLogic.appcommonhandle("模块分类",null), 
				'addeddate': commonLogic.appcommonhandle("添加时间",null), 
				'editedby': commonLogic.appcommonhandle("由谁更新",null), 
				'editeddate': commonLogic.appcommonhandle("更新时间",null), 
				'id': commonLogic.appcommonhandle("文档编号",null), 
				'product': commonLogic.appcommonhandle("所属产品",null), 
				'project': commonLogic.appcommonhandle("所属项目",null), 
			},
			uiactions: {
			},
		},
		docstatus_form: {
			details: {
				'grouppanel3': commonLogic.appcommonhandle("分组面板",null), 
				'rawitem4': commonLogic.appcommonhandle("",null), 
				'rawitem5': commonLogic.appcommonhandle("",null), 
				'grouppanel4': commonLogic.appcommonhandle("分组面板",null), 
				'grouppanel1': commonLogic.appcommonhandle("分组面板",null), 
				'rawitem6': commonLogic.appcommonhandle("",null), 
				'rawitem1': commonLogic.appcommonhandle("",null), 
				'grouppanel5': commonLogic.appcommonhandle("分组面板",null), 
				'rawitem2': commonLogic.appcommonhandle("",null), 
				'grouppanel6': commonLogic.appcommonhandle("分组面板",null), 
				'rawitem3': commonLogic.appcommonhandle("",null), 
				'grouppanel7': commonLogic.appcommonhandle("分组面板",null), 
				'grouppanel2': commonLogic.appcommonhandle("分组面板",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("文档编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("文档标题",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'alldoccnt1': commonLogic.appcommonhandle("所有文档数量",null), 
				'recentupdatecnt': commonLogic.appcommonhandle("最近更新数量",null), 
				'formitemex1': commonLogic.appcommonhandle("所有",null), 
				'alldoccnt2': commonLogic.appcommonhandle("所有文档数量",null), 
				'recentaddcnt': commonLogic.appcommonhandle("最近添加数量",null), 
				'formitemex2': commonLogic.appcommonhandle("",null), 
				'alldoccnt3': commonLogic.appcommonhandle("所有文档数量",null), 
				'mydoccnt': commonLogic.appcommonhandle("我的文档数量",null), 
				'formitemex3': commonLogic.appcommonhandle("",null), 
				'alldoccnt4': commonLogic.appcommonhandle("所有文档数量",null), 
				'myfavouritecnt': commonLogic.appcommonhandle("我的收藏数量",null), 
				'formitemex4': commonLogic.appcommonhandle("",null), 
				'id': commonLogic.appcommonhandle("文档编号",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("文档编号",null),
				'title': commonLogic.appcommonhandle("文档标题",null),
				'addedby': commonLogic.appcommonhandle("由谁添加",null),
				'addeddate': commonLogic.appcommonhandle("添加时间",null),
				'editedby': commonLogic.appcommonhandle("由谁更新",null),
				'editeddate': commonLogic.appcommonhandle("更新时间",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			doc_look: commonLogic.appcommonhandle("查看",null),
			doc_edit: commonLogic.appcommonhandle("编辑",null),
			doc_delete: commonLogic.appcommonhandle("删除",null),
			doc_collect: commonLogic.appcommonhandle("收藏",null),
			doc_uncollect: commonLogic.appcommonhandle("取消收藏",null),
			},
		},
		moremyfavourite_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("文档编号",null),
				'title': commonLogic.appcommonhandle("文档标题",null),
				'addedby': commonLogic.appcommonhandle("由谁添加",null),
				'addeddate': commonLogic.appcommonhandle("添加时间",null),
				'editedby': commonLogic.appcommonhandle("由谁更新",null),
				'editeddate': commonLogic.appcommonhandle("更新时间",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			doc_look: commonLogic.appcommonhandle("查看",null),
			doc_edit: commonLogic.appcommonhandle("编辑",null),
			doc_delete: commonLogic.appcommonhandle("删除",null),
			doc_collect: commonLogic.appcommonhandle("收藏",null),
			doc_uncollect: commonLogic.appcommonhandle("取消收藏",null),
			},
		},
		recentupdate_grid: {
			columns: {
				'title': commonLogic.appcommonhandle("文档标题",null),
				'addedby': commonLogic.appcommonhandle("由谁添加",null),
				'editeddate': commonLogic.appcommonhandle("更新时间",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		mydoc_grid: {
			columns: {
				'title': commonLogic.appcommonhandle("文档标题",null),
				'addedby': commonLogic.appcommonhandle("由谁添加",null),
				'editeddate': commonLogic.appcommonhandle("更新时间",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		myfavourite_grid: {
			columns: {
				'title': commonLogic.appcommonhandle("文档标题",null),
				'addedby': commonLogic.appcommonhandle("由谁添加",null),
				'editeddate': commonLogic.appcommonhandle("更新时间",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		morerecentupdate_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("文档编号",null),
				'title': commonLogic.appcommonhandle("文档标题",null),
				'addedby': commonLogic.appcommonhandle("由谁添加",null),
				'addeddate': commonLogic.appcommonhandle("添加时间",null),
				'editedby': commonLogic.appcommonhandle("由谁更新",null),
				'editeddate': commonLogic.appcommonhandle("更新时间",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			doc_look: commonLogic.appcommonhandle("查看",null),
			doc_edit: commonLogic.appcommonhandle("编辑",null),
			doc_delete: commonLogic.appcommonhandle("删除",null),
			doc_collect: commonLogic.appcommonhandle("收藏",null),
			doc_uncollect: commonLogic.appcommonhandle("取消收藏",null),
			},
		},
		editviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
		},
		docmodulegridviewtoolbar_toolbar: {
			'deuiaction7': {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
			'deuiaction3_togglefilter': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
		gridviewlookdoctoolbar_toolbar: {
			'deuiaction7': {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
			'deuiaction3_togglefilter': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
		myfavouritegridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
		createeditviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
		},
		recentmoregridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
		modulegridviewtoolbar_toolbar: {
			'deuiaction7': {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
			'deuiaction3_togglefilter': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
		gridviewtoolbar_toolbar: {
			'deuiaction7': {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
			'deuiaction3_togglefilter': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
		mymoregridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
		morerecentupdatetree_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'root': commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
			doc_edit: commonLogic.appcommonhandle("编辑",null),
			doc_delete: commonLogic.appcommonhandle("删除",null),
			doc_onlycollectdoc: commonLogic.appcommonhandle("收藏",null),
			doc_onlyuncollectdoc: commonLogic.appcommonhandle("取消收藏",null),
			doc_docinfo: commonLogic.appcommonhandle("查看文档详情",null),
			},
		},
		moremydoctree_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'root': commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
			doc_edit: commonLogic.appcommonhandle("编辑",null),
			doc_delete: commonLogic.appcommonhandle("删除",null),
			doc_onlycollectdoc: commonLogic.appcommonhandle("收藏",null),
			doc_onlyuncollectdoc: commonLogic.appcommonhandle("取消收藏",null),
			doc_docinfo: commonLogic.appcommonhandle("查看文档详情",null),
			},
		},
		moremyfavouritestree_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				'root': commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
			doc_edit: commonLogic.appcommonhandle("编辑",null),
			doc_delete: commonLogic.appcommonhandle("删除",null),
			doc_onlycollectdoc: commonLogic.appcommonhandle("收藏",null),
			doc_onlyuncollectdoc: commonLogic.appcommonhandle("取消收藏",null),
			doc_docinfo: commonLogic.appcommonhandle("查看文档详情",null),
			doclibmodule_edit: commonLogic.appcommonhandle("编辑",null),
			doclibmodule_weihufenlei: commonLogic.appcommonhandle("维护分类",null),
			doclibmodule_favorite: commonLogic.appcommonhandle("收藏",null),
			doclibmodule_nfavorite: commonLogic.appcommonhandle("取消收藏",null),
			doclib_editdoclib: commonLogic.appcommonhandle("编辑",null),
			doclib_weihufenlei: commonLogic.appcommonhandle("维护分类",null),
			doclib_uncollect: commonLogic.appcommonhandle("取消收藏",null),
			doclib_collect: commonLogic.appcommonhandle("收藏",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;