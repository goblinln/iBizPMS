import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'orgid': commonLogic.appcommonhandle("组织标识",null),
		'istop': commonLogic.appcommonhandle("是否置顶",null),
		'comment': commonLogic.appcommonhandle("备注",null),
		'qd': commonLogic.appcommonhandle("测试负责人",null),
		'productclass': commonLogic.appcommonhandle("产品分类",null),
		'unconfirmbugcnt': commonLogic.appcommonhandle("未确认Bug数",null),
		'acl': commonLogic.appcommonhandle("访问控制",null),
		'name': commonLogic.appcommonhandle("产品名称",null),
		'mobimage': commonLogic.appcommonhandle("移动端图片",null),
		'testtaskcnt': commonLogic.appcommonhandle("测试单数",null),
		'testsuitecnt': commonLogic.appcommonhandle("套件数",null),
		'productplancnt': commonLogic.appcommonhandle("计划总数",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'deleted': commonLogic.appcommonhandle("已删除",null),
		'closedstorycnt': commonLogic.appcommonhandle("已关闭需求",null),
		'relatedbugcnt': commonLogic.appcommonhandle("相关Bug数",null),
		'whitelist': commonLogic.appcommonhandle("分组白名单",null),
		'mdeptid': commonLogic.appcommonhandle("部门标识",null),
		'releasecnt': commonLogic.appcommonhandle("发布总数",null),
		'rd': commonLogic.appcommonhandle("发布负责人",null),
		'popk': commonLogic.appcommonhandle("产品负责人（选择）",null),
		'notclosedbugcnt': commonLogic.appcommonhandle("未关闭Bug数",null),
		'supproreport': commonLogic.appcommonhandle("支持产品汇报",null),
		'order': commonLogic.appcommonhandle("排序",null),
		'type': commonLogic.appcommonhandle("产品类型",null),
		'po': commonLogic.appcommonhandle("产品负责人",null),
		'qdpk': commonLogic.appcommonhandle("测试负责人（选择）",null),
		'desc': commonLogic.appcommonhandle("产品描述	",null),
		'status': commonLogic.appcommonhandle("状态",null),
		'changedstorycnt': commonLogic.appcommonhandle("已变更需求",null),
		'activebugcnt': commonLogic.appcommonhandle("未解决Bug数",null),
		'createdby': commonLogic.appcommonhandle("由谁创建",null),
		'rdpk': commonLogic.appcommonhandle("发布负责人（选择）",null),
		'createdversion': commonLogic.appcommonhandle("当前系统版本",null),
		'draftstorycnt': commonLogic.appcommonhandle("草稿需求",null),
		'doccnt': commonLogic.appcommonhandle("文档数",null),
		'casecnt': commonLogic.appcommonhandle("用例数",null),
		'relatedprojects': commonLogic.appcommonhandle("关联项目数",null),
		'ibiz_id': commonLogic.appcommonhandle("IBIZ标识",null),
		'substatus': commonLogic.appcommonhandle("子状态",null),
		'code': commonLogic.appcommonhandle("产品代号",null),
		'srfcount': commonLogic.appcommonhandle("属性",null),
		'order1': commonLogic.appcommonhandle("排序",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'buildcnt': commonLogic.appcommonhandle("BUILD数",null),
		'createddate': commonLogic.appcommonhandle("创建日期",null),
		'noticeusers': commonLogic.appcommonhandle("消息通知用户",null),
		'activestorycnt': commonLogic.appcommonhandle("激活需求数",null),
		'linename': commonLogic.appcommonhandle("产品线",null),
		'line': commonLogic.appcommonhandle("产品线",null),
	},
		views: {
			'testtabexpview': {
				caption: commonLogic.appcommonhandle("测试",null),
				title: commonLogic.appcommonhandle("测试",null),
			},
			'testdashboardview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("产品测试数据看板视图",null),
			},
			'mytesttabexpview': {
				caption: commonLogic.appcommonhandle("测试",null),
				title: commonLogic.appcommonhandle("测试",null),
			},
			'testgridview': {
				caption: commonLogic.appcommonhandle("测试",null),
				title: commonLogic.appcommonhandle("所有产品",null),
			},
			'projectgridview9': {
				caption: commonLogic.appcommonhandle("项目",null),
				title: commonLogic.appcommonhandle("产品表格视图（项目）",null),
			},
			'storytreeexpview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("产品需求导航视图",null),
			},
			'gridview_unclosed': {
				caption: commonLogic.appcommonhandle("未关闭产品",null),
				title: commonLogic.appcommonhandle("产品表格视图",null),
			},
			'wizardview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("产品向导视图",null),
			},
			'testlistexpview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("测试统计",null),
			},
			'maintabexpview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("产品",null),
			},
			'chartview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("产品图表视图",null),
			},
			'gridview': {
				caption: commonLogic.appcommonhandle("所有产品",null),
				title: commonLogic.appcommonhandle("所有产品",null),
			},
			'pickupview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("产品数据选择视图",null),
			},
			'leftsidebarlistview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("所有产品",null),
			},
			'pickupgridview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("产品选择表格视图",null),
			},
			'listexpview': {
				caption: commonLogic.appcommonhandle("产品统计",null),
				title: commonLogic.appcommonhandle("产品列表导航视图",null),
			},
			'testleftsidebarlistview': {
				caption: commonLogic.appcommonhandle("测试",null),
				title: commonLogic.appcommonhandle("所有测试",null),
			},
			'dashboardinfomainview9': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("产品信息",null),
			},
			'htmlview': {
				caption: commonLogic.appcommonhandle("iBiz软件生产管理",null),
				title: commonLogic.appcommonhandle("iBiz软件生产管理",null),
			},
			'maindashboardview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("产品数据看板视图",null),
			},
			'mainview_edit': {
				caption: commonLogic.appcommonhandle("产品基本信息",null),
				title: commonLogic.appcommonhandle("产品编辑视图",null),
			},
			'expeditview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("产品编辑视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("产品编辑视图",null),
			},
			'casetreeexpview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("产品需求导航视图",null),
			},
			'editview_close': {
				caption: commonLogic.appcommonhandle("关闭产品",null),
				title: commonLogic.appcommonhandle("关闭产品",null),
			},
			'bugtreeexpview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("产品需求导航视图",null),
			},
		},
		close_form: {
			details: {
				'druipart1': commonLogic.appcommonhandle("",null), 
				'group1': commonLogic.appcommonhandle("基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("产品名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'comment': commonLogic.appcommonhandle("备注",null), 
				'id': commonLogic.appcommonhandle("编号",null), 
			},
			uiactions: {
			},
		},
		main_edit_form: {
			details: {
				'grouppanel2': commonLogic.appcommonhandle("分组面板",null), 
				'button1': commonLogic.appcommonhandle("维护产品线",null), 
				'grouppanel3': commonLogic.appcommonhandle("分组面板",null), 
				'grouppanel1': commonLogic.appcommonhandle("分组面板",null), 
				'group1': commonLogic.appcommonhandle("product基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("产品名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'name': commonLogic.appcommonhandle("产品名称",null), 
				'code': commonLogic.appcommonhandle("产品代号",null), 
				'line': commonLogic.appcommonhandle("产品线",null), 
				'linename': commonLogic.appcommonhandle("产品线",null), 
				'po': commonLogic.appcommonhandle("产品负责人",null), 
				'qd': commonLogic.appcommonhandle("测试负责人",null), 
				'rd': commonLogic.appcommonhandle("发布负责人",null), 
				'type': commonLogic.appcommonhandle("产品类型",null), 
				'status': commonLogic.appcommonhandle("状态",null), 
				'supproreport': commonLogic.appcommonhandle("支持产品汇报",null), 
				'desc': commonLogic.appcommonhandle("产品描述	",null), 
				'acl': commonLogic.appcommonhandle("访问控制",null), 
				'id': commonLogic.appcommonhandle("编号",null), 
			},
			uiactions: {
				product_seline: commonLogic.appcommonhandle("维护产品线",null),
			},
		},
		dashboardinfo_form: {
			details: {
				'grouppanel5': commonLogic.appcommonhandle("分组面板",null), 
				'group1': commonLogic.appcommonhandle("产品基本信息",null), 
				'grouppanel2': commonLogic.appcommonhandle("负责人",null), 
				'grouppanel3': commonLogic.appcommonhandle("基本信息",null), 
				'grouppanel4': commonLogic.appcommonhandle("其他信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("产品名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'id': commonLogic.appcommonhandle("",null), 
				'code': commonLogic.appcommonhandle("",null), 
				'name': commonLogic.appcommonhandle("",null), 
				'desc': commonLogic.appcommonhandle("",null), 
				'type': commonLogic.appcommonhandle("类型：",null), 
				'status': commonLogic.appcommonhandle("状态:",null), 
				'po': commonLogic.appcommonhandle("产品",null), 
				'rd': commonLogic.appcommonhandle("发布",null), 
				'qd': commonLogic.appcommonhandle("测试",null), 
				'linename': commonLogic.appcommonhandle("产品线",null), 
				'createdby': commonLogic.appcommonhandle("创建人",null), 
				'createddate': commonLogic.appcommonhandle("创建日期",null), 
				'acl': commonLogic.appcommonhandle("访问控制",null), 
				'activestorycnt': commonLogic.appcommonhandle("激活需求",null), 
				'productplancnt': commonLogic.appcommonhandle("计划数",null), 
				'relatedbugcnt': commonLogic.appcommonhandle("相关Bug",null), 
				'changedstorycnt': commonLogic.appcommonhandle("已变更需求",null), 
				'relatedprojects': commonLogic.appcommonhandle("关联项目数",null), 
				'casecnt': commonLogic.appcommonhandle("用例数",null), 
				'draftstorycnt': commonLogic.appcommonhandle("草稿需求",null), 
				'buildcnt': commonLogic.appcommonhandle("BUILD数",null), 
				'doccnt': commonLogic.appcommonhandle("文档数",null), 
				'closedstorycnt': commonLogic.appcommonhandle("已关闭需求",null), 
				'releasecnt': commonLogic.appcommonhandle("发布数",null), 
			},
			uiactions: {
			},
		},
		main_form: {
			details: {
				'grouppanel3': commonLogic.appcommonhandle("分组面板",null), 
				'button1': commonLogic.appcommonhandle("维护产品线",null), 
				'grouppanel2': commonLogic.appcommonhandle("分组面板",null), 
				'grouppanel1': commonLogic.appcommonhandle("分组面板",null), 
				'group1': commonLogic.appcommonhandle("product基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("产品名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'name': commonLogic.appcommonhandle("产品名称",null), 
				'code': commonLogic.appcommonhandle("产品代号",null), 
				'line': commonLogic.appcommonhandle("产品线",null), 
				'linename': commonLogic.appcommonhandle("产品线",null), 
				'po': commonLogic.appcommonhandle("产品负责人",null), 
				'qd': commonLogic.appcommonhandle("测试负责人",null), 
				'rd': commonLogic.appcommonhandle("发布负责人",null), 
				'type': commonLogic.appcommonhandle("产品类型",null), 
				'supproreport': commonLogic.appcommonhandle("支持产品汇报",null), 
				'desc': commonLogic.appcommonhandle("产品描述	",null), 
				'acl': commonLogic.appcommonhandle("访问控制",null), 
				'id': commonLogic.appcommonhandle("编号",null), 
			},
			uiactions: {
				product_seline: commonLogic.appcommonhandle("维护产品线",null),
			},
		},
		wizardproduct_form: {
			details: {
				'rawitem1': commonLogic.appcommonhandle("",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("产品名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'id': commonLogic.appcommonhandle("编号",null), 
			},
			uiactions: {
			},
		},
		wizardaddproduct_form: {
			details: {
				'grouppanel3': commonLogic.appcommonhandle("分组面板",null), 
				'button1': commonLogic.appcommonhandle("维护产品线",null), 
				'grouppanel2': commonLogic.appcommonhandle("分组面板",null), 
				'grouppanel1': commonLogic.appcommonhandle("分组面板",null), 
				'group1': commonLogic.appcommonhandle("product基本信息",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("产品名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'name': commonLogic.appcommonhandle("产品名称",null), 
				'code': commonLogic.appcommonhandle("产品代号",null), 
				'line': commonLogic.appcommonhandle("产品线",null), 
				'linename': commonLogic.appcommonhandle("产品线",null), 
				'po': commonLogic.appcommonhandle("产品负责人",null), 
				'qd': commonLogic.appcommonhandle("测试负责人",null), 
				'rd': commonLogic.appcommonhandle("发布负责人",null), 
				'type': commonLogic.appcommonhandle("产品类型",null), 
				'desc': commonLogic.appcommonhandle("产品描述	",null), 
				'acl': commonLogic.appcommonhandle("访问控制",null), 
				'id': commonLogic.appcommonhandle("编号",null), 
			},
			uiactions: {
				product_seline: commonLogic.appcommonhandle("维护产品线",null),
			},
		},
		wizardcreatestory_form: {
			details: {
				'rawitem1': commonLogic.appcommonhandle("",null), 
				'formpage1': commonLogic.appcommonhandle("表单分页",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("产品名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'id': commonLogic.appcommonhandle("编号",null), 
			},
			uiactions: {
			},
		},
		wizardaddstory_form: {
			details: {
				'grouppanel1': commonLogic.appcommonhandle("分组面板",null), 
				'formpage1': commonLogic.appcommonhandle("表单分页",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("产品名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'formitem': commonLogic.appcommonhandle("所属产品",null), 
				'formitem1': commonLogic.appcommonhandle("",null), 
				'formitem2': commonLogic.appcommonhandle("所属模块",null), 
				'formitem3': commonLogic.appcommonhandle("计划",null), 
				'formitem4': commonLogic.appcommonhandle("需求来源",null), 
				'formitem5': commonLogic.appcommonhandle("来源备注",null), 
				'formitem6': commonLogic.appcommonhandle("由谁评审",null), 
				'formitem7': commonLogic.appcommonhandle("",null), 
				'formitem8': commonLogic.appcommonhandle("需求名称",null), 
				'formitem9': commonLogic.appcommonhandle("优先级",null), 
				'formitem10': commonLogic.appcommonhandle("预计",null), 
				'formitem11': commonLogic.appcommonhandle("需求描述",null), 
				'formitem12': commonLogic.appcommonhandle("验收标准",null), 
				'formitem13': commonLogic.appcommonhandle("抄送给",null), 
				'formitem14': commonLogic.appcommonhandle("关键词",null), 
				'id': commonLogic.appcommonhandle("编号",null), 
			},
			uiactions: {
			},
		},
		mainproject_grid: {
			columns: {
				'name': commonLogic.appcommonhandle("产品名称",null),
				'code': commonLogic.appcommonhandle("产品代号",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				'name': commonLogic.appcommonhandle("产品名称",null),
				'code': commonLogic.appcommonhandle("产品代号",null),
				'status': commonLogic.appcommonhandle("状态",null),
				'type': commonLogic.appcommonhandle("产品类型",null),
				'linename': commonLogic.appcommonhandle("产品线",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		test_grid: {
			columns: {
				'name': commonLogic.appcommonhandle("产品名称",null),
				'code': commonLogic.appcommonhandle("产品代号",null),
				'status': commonLogic.appcommonhandle("状态",null),
				'type': commonLogic.appcommonhandle("产品类型",null),
				'linename': commonLogic.appcommonhandle("产品线",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		main2_grid: {
			columns: {
				'name': commonLogic.appcommonhandle("产品名称",null),
				'code': commonLogic.appcommonhandle("产品代号",null),
				'status': commonLogic.appcommonhandle("状态",null),
				'type': commonLogic.appcommonhandle("产品类型",null),
				'linename': commonLogic.appcommonhandle("产品线",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		exp_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				new: commonLogic.appcommonhandle("新建",null),
			},
		},
		testexp_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				new: commonLogic.appcommonhandle("新建",null),
			},
		},
		testsidebar_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				product_producttop: commonLogic.appcommonhandle("置顶",null),
				product_cancelproducttop: commonLogic.appcommonhandle("取消置顶",null),
			},
		},
		sidebar_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				product_producttop: commonLogic.appcommonhandle("置顶",null),
				product_cancelproducttop: commonLogic.appcommonhandle("取消置顶",null),
			},
		},
		productstatus_chart: {
			nodata:commonLogic.appcommonhandle("",null),
		},
		default_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("常规条件",null), 
				'n_acl_eq': commonLogic.appcommonhandle("访问控制(等于(=))",null), 
				'n_line_eq': commonLogic.appcommonhandle("产品线(等于(=))",null), 
				'n_linename_like': commonLogic.appcommonhandle("产品线(文本包含(%))",null), 
				'n_linename_eq': commonLogic.appcommonhandle("产品线(等于(=))",null), 
				'n_name_like': commonLogic.appcommonhandle("产品名称(文本包含(%))",null), 
			},
			uiactions: {
			},
		},
		editview_closetoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("保存并关闭",null),
				tip: commonLogic.appcommonhandle("保存并关闭",null),
			},
		},
		mainview_edittoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("保存并关闭",null),
				tip: commonLogic.appcommonhandle("保存并关闭",null),
			},
		},
		editviewtoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("保存并关闭",null),
				tip: commonLogic.appcommonhandle("保存并关闭",null),
			},
		},
		listexpviewlistexpbar_list_quicktoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
		},
		testlistexpviewlistexpbar_list_quicktoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
		},
		testgridviewtoolbar_toolbar: {
		},
		testleftsidebarlistviewtoolbar_toolbar: {
			'deuiaction3_testmanager': {
				caption: commonLogic.appcommonhandle("管理",null),
				tip: commonLogic.appcommonhandle("管理",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
		},
		testleftsidebarlistviewlist_quicktoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("置顶",null),
				tip: commonLogic.appcommonhandle("置顶",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("取消置顶",null),
				tip: commonLogic.appcommonhandle("取消置顶",null),
			},
		},
		leftsidebarlistviewtoolbar_toolbar: {
			'deuiaction3_manager': {
				caption: commonLogic.appcommonhandle("管理",null),
				tip: commonLogic.appcommonhandle("管理",null),
			},
			'deuiaction3_create': {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
		},
		leftsidebarlistviewlist_quicktoolbar_toolbar: {
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("置顶",null),
				tip: commonLogic.appcommonhandle("置顶",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("取消置顶",null),
				tip: commonLogic.appcommonhandle("取消置顶",null),
			},
		},
		gridviewtoolbar_toolbar: {
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("导出",null),
				tip: commonLogic.appcommonhandle("导出",null),
			},
			'deuiaction4': {
				caption: commonLogic.appcommonhandle("过滤",null),
				tip: commonLogic.appcommonhandle("过滤",null),
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;