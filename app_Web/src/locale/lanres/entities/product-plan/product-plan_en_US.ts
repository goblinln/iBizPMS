
export default {
  fields: {
    title: "名称",
    id: "编号",
    begin: "开始日期",
    desc: "描述",
    end: "结束日期",
    deleted: "已删除",
    order: "排序",
    parentname: "父计划名称",
    branch: "平台/分支",
    parent: "父计划",
    product: "产品",
    statuss: "状态",
    future: "待定",
    delta: "周期",
    oldtitle: "上一次计划名称",
    storycnt: "需求数",
    bugcnt: "bug数",
    isexpired: "是否过期",
    estimatecnt: "工时数",
    beginstr: "开始日期",
    endstr: "结束日期",
  },
	views: {
		maintabexp: {
			caption: "产品计划",
      		title: "计划",
		},
		maineditview: {
			caption: "产品计划",
      		title: "计划详情",
		},
		gridview: {
			caption: "产品计划",
      		title: "产品计划表格视图",
		},
		projectgridview9: {
			caption: "项目",
      		title: "产品计划表格视图（项目）",
		},
		editview: {
			caption: "产品计划",
      		title: "计划",
		},
		maindataeditview: {
			caption: "产品计划",
      		title: "产品计划编辑视图",
		},
	},
	main_form: {
		details: {
			grouppanel2: "分组面板", 
			grouppanel1: "分组面板", 
			group1: "productplan基本信息", 
			formpage1: "基本信息", 
			srforikey: "", 
			srfkey: "编号", 
			srfmajortext: "名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			product: "产品", 
			branch: "平台/分支", 
			title: "名称", 
			oldtitle: "", 
			begin: "开始日期", 
			future: "", 
			end: "结束日期", 
			delta: "", 
			desc: "描述", 
			id: "编号", 
		},
		uiactions: {
		},
	},
	maindata_form: {
		details: {
			druipart2: "", 
			grouppanel4: "分组面板", 
			formpage2: "需求", 
			druipart3: "", 
			grouppanel3: "分组面板", 
			formpage3: "Bug", 
			druipart4: "", 
			grouppanel5: "子计划", 
			grouppanel1: "分组面板", 
			druipart1: "", 
			grouppanel2: "历史记录", 
			group1: "基本信息", 
			formpage1: "计划详情", 
			srforikey: "", 
			srfkey: "编号", 
			srfmajortext: "名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			product: "产品", 
			title: "名称", 
			begin: "开始日期", 
			beginstr: "开始日期", 
			end: "结束日期", 
			endstr: "结束日期", 
			desc: "描述", 
			statuss: "状态", 
			id: "编号", 
		},
		uiactions: {
		},
	},
	info_form: {
		details: {
			grouppanel1: "分组面板", 
			druipart1: "", 
			grouppanel2: "历史记录", 
			group1: "基本信息", 
			formpage1: "基本信息", 
			srforikey: "", 
			srfkey: "编号", 
			srfmajortext: "名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			product: "产品", 
			title: "名称", 
			begin: "开始日期", 
			end: "结束日期", 
			desc: "描述", 
			id: "编号", 
		},
		uiactions: {
		},
	},
	mainproject_grid: {
		columns: {
			title: "名称",
		},
		uiactions: {
		},
	},
	main_grid: {
		columns: {
			id: "编号",
			title: "名称",
			beginstr: "开始日期",
			endstr: "结束日期",
			storycnt: "需求数",
			bugcnt: "bug数",
			actions: "操作",
		},
		uiactions: {
        productplan_addproject: "添加项目",
        productplan_relationstory: "关联需求",
        productplan_relationbug: "关联Bug",
        productplan_mainedit: "编辑",
        productplan_newsubplan: "子计划",
        productplan_delete: "删除",
		},
	},
	editviewtoolbar_toolbar: {
		deuiaction1: {
			caption: "Save And Close",
			tip: "Save And Close Window",
		},
	},
	maindataeditviewtoolbar_toolbar: {
		deuiaction1_newsubplan: {
			caption: "子计划",
			tip: "子计划",
		},
		deuiaction1_mainedit: {
			caption: "编辑",
			tip: "编辑",
		},
		deuiaction1_deletecz: {
			caption: "删除",
			tip: "删除",
		},
	},
	gridviewtoolbar_toolbar: {
		deuiaction3_create: {
			caption: "创建计划",
			tip: "创建计划",
		},
		deuiaction2: {
			caption: "刷新",
			tip: "刷新",
		},
		deuiaction1: {
			caption: "Export",
			tip: "Export {0} Data To Excel",
		},
	},
};