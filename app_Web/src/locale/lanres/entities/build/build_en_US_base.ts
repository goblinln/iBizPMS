import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'ids': commonLogic.appcommonhandle("Bug版本健值",null),
		'name': commonLogic.appcommonhandle("名称编号",null),
		'backgroundid': commonLogic.appcommonhandle("后台体系",null),
		'builder': commonLogic.appcommonhandle("构建者",null),
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'files': commonLogic.appcommonhandle("附件",null),
		'releasetype': commonLogic.appcommonhandle("运行模式",null),
		'builderpk': commonLogic.appcommonhandle("构建者（选择）",null),
		'rebuild': commonLogic.appcommonhandle("重新构建",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'desc': commonLogic.appcommonhandle("描述",null),
		'id': commonLogic.appcommonhandle("id",null),
		'deleted': commonLogic.appcommonhandle("已删除",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'sqlid': commonLogic.appcommonhandle("运行数据库",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'scmpath': commonLogic.appcommonhandle("源代码地址",null),
		'filepath': commonLogic.appcommonhandle("下载地址",null),
		'createbugcnt': commonLogic.appcommonhandle("产生的bug",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'stories': commonLogic.appcommonhandle("完成的需求",null),
		'bugs': commonLogic.appcommonhandle("解决的Bug",null),
		'frontapplication': commonLogic.appcommonhandle("系统应用",null),
		'noticeusers': commonLogic.appcommonhandle("消息通知用户",null),
		'date': commonLogic.appcommonhandle("打包日期",null),
		'productname': commonLogic.appcommonhandle("产品名称",null),
		'product': commonLogic.appcommonhandle("产品",null),
		'branch': commonLogic.appcommonhandle("平台/分支",null),
		'project': commonLogic.appcommonhandle("所属项目",null),
		'buildsn': commonLogic.appcommonhandle("版本编号",null),
		'createman': commonLogic.appcommonhandle("建立人",null),
		'createdate': commonLogic.appcommonhandle("建立时间",null),
		'updateman': commonLogic.appcommonhandle("更新人",null),
		'updatedate': commonLogic.appcommonhandle("更新时间",null),
	},
		views: {
			'maingridview': {
				caption: commonLogic.appcommonhandle("版本",null),
				title: commonLogic.appcommonhandle("版本表格视图",null),
			},
			'testroundsgridview': {
				caption: commonLogic.appcommonhandle("轮次",null),
				title: commonLogic.appcommonhandle("版本表格视图（轮次）",null),
			},
			'optionview': {
				caption: commonLogic.appcommonhandle("版本",null),
				title: commonLogic.appcommonhandle("版本选项操作视图",null),
			},
			'mainview': {
				caption: commonLogic.appcommonhandle("版本",null),
				title: commonLogic.appcommonhandle("版本编辑视图",null),
			},
			'maintabexpview': {
				caption: commonLogic.appcommonhandle("版本",null),
				title: commonLogic.appcommonhandle("版本分页导航视图",null),
			},
			'editformeditview': {
				caption: commonLogic.appcommonhandle("版本",null),
				title: commonLogic.appcommonhandle("版本编辑视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("版本",null),
				title: commonLogic.appcommonhandle("版本编辑视图",null),
			},
		},
		main_form: {
			details: {
				'druipart2': commonLogic.appcommonhandle("",null), 
				'grouppanel2': commonLogic.appcommonhandle("附件",null), 
				'group1': commonLogic.appcommonhandle("基本信息",null), 
				'druipart1': commonLogic.appcommonhandle("",null), 
				'grouppanel1': commonLogic.appcommonhandle("历史记录",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srfupdatedate': commonLogic.appcommonhandle("更新时间",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("名称编号",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'productname': commonLogic.appcommonhandle("产品",null), 
				'name': commonLogic.appcommonhandle("名称编号",null), 
				'builder': commonLogic.appcommonhandle("构建者",null), 
				'date': commonLogic.appcommonhandle("打包日期",null), 
				'scmpath': commonLogic.appcommonhandle("源代码地址",null), 
				'filepath': commonLogic.appcommonhandle("下载地址",null), 
				'desc': commonLogic.appcommonhandle("描述",null), 
				'id': commonLogic.appcommonhandle("id",null), 
				'product': commonLogic.appcommonhandle("产品",null), 
			},
			uiactions: {
			},
		},
		editform_form: {
			details: {
				'grouppanel2': commonLogic.appcommonhandle("分组面板",null), 
				'group1': commonLogic.appcommonhandle("基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srfupdatedate': commonLogic.appcommonhandle("更新时间",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("名称编号",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'project': commonLogic.appcommonhandle("所属项目",null), 
				'productname': commonLogic.appcommonhandle("产品",null), 
				'name': commonLogic.appcommonhandle("名称编号",null), 
				'builder': commonLogic.appcommonhandle("构建者",null), 
				'date': commonLogic.appcommonhandle("打包日期",null), 
				'scmpath': commonLogic.appcommonhandle("源代码地址",null), 
				'product': commonLogic.appcommonhandle("产品",null), 
				'filepath': commonLogic.appcommonhandle("下载地址",null), 
				'files': commonLogic.appcommonhandle("上传发行包",null), 
				'desc': commonLogic.appcommonhandle("描述",null), 
				'id': commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			},
		},
		quickcreate_form: {
			details: {
				'grouppanel2': commonLogic.appcommonhandle("分组面板",null), 
				'group1': commonLogic.appcommonhandle("基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srfupdatedate': commonLogic.appcommonhandle("更新时间",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("名称编号",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'project': commonLogic.appcommonhandle("所属项目",null), 
				'productname': commonLogic.appcommonhandle("产品",null), 
				'name': commonLogic.appcommonhandle("名称编号",null), 
				'builder': commonLogic.appcommonhandle("构建者",null), 
				'date': commonLogic.appcommonhandle("打包日期",null), 
				'product': commonLogic.appcommonhandle("产品",null), 
				'scmpath': commonLogic.appcommonhandle("源代码地址",null), 
				'filepath': commonLogic.appcommonhandle("下载地址",null), 
				'files': commonLogic.appcommonhandle("上传发行包",null), 
				'desc': commonLogic.appcommonhandle("描述",null), 
				'id': commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			},
		},
		testbuildrelease_form: {
			details: {
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srfupdatedate': commonLogic.appcommonhandle("更新时间",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("id",null), 
				'srfmajortext': commonLogic.appcommonhandle("名称编号",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'releasetype': commonLogic.appcommonhandle("运行模式",null), 
				'backgroundid': commonLogic.appcommonhandle("后台体系",null), 
				'sqlid': commonLogic.appcommonhandle("运行数据库",null), 
				'frontapplication': commonLogic.appcommonhandle("系统应用",null), 
				'rebuild': commonLogic.appcommonhandle("重新构建",null), 
				'id': commonLogic.appcommonhandle("id",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("编号",null),
				'productname': commonLogic.appcommonhandle("产品名称",null),
				'name': commonLogic.appcommonhandle("名称编号",null),
				'scmpath': commonLogic.appcommonhandle("源代码地址",null),
				'filepath': commonLogic.appcommonhandle("下载地址",null),
				'date': commonLogic.appcommonhandle("打包日期",null),
				'builder': commonLogic.appcommonhandle("构建者",null),
				'uagridcolumn1': commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
		exportColumns: {
				'buildsn': commonLogic.appcommonhandle("编号",null),
				'id': commonLogic.appcommonhandle("编号",null),
				'productname': commonLogic.appcommonhandle("产品名称",null),
				'name': commonLogic.appcommonhandle("名称编号",null),
				'scmpath': commonLogic.appcommonhandle("源代码地址",null),
				'filepath': commonLogic.appcommonhandle("下载地址",null),
				'date': commonLogic.appcommonhandle("打包日期",null),
				'builder': commonLogic.appcommonhandle("构建者",null),
				'product': commonLogic.appcommonhandle("产品",null),
				'project': commonLogic.appcommonhandle("所属项目",null),
		},
			uiactions: {
			build_linkstories: commonLogic.appcommonhandle("关联需求",null),
			build_submittotesting: commonLogic.appcommonhandle("提交测试",null),
			build_viewbugs: commonLogic.appcommonhandle("查看Bug",null),
			build_editbuild: commonLogic.appcommonhandle("编辑版本",null),
			build_delete: commonLogic.appcommonhandle("删除",null),
			},
		},
		testrounds_grid: {
			columns: {
				'id': commonLogic.appcommonhandle("编号",null),
				'productname': commonLogic.appcommonhandle("产品名称",null),
				'name': commonLogic.appcommonhandle("名称编号",null),
				'scmpath': commonLogic.appcommonhandle("源代码地址",null),
				'filepath': commonLogic.appcommonhandle("下载地址",null),
				'builder': commonLogic.appcommonhandle("构建者",null),
				'date': commonLogic.appcommonhandle("打包日期",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		editformeditviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
		},
		editviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
		},
		maingridviewtoolbar_toolbar: {
			'deuiaction3_create': {
				caption: commonLogic.appcommonhandle("创建版本",null),
				tip: commonLogic.appcommonhandle("创建版本",null),
			},
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
		testroundsgridviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;