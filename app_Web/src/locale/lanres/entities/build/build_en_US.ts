export default {
  fields: {
    name: "名称编号",
    builder: "构建者",
    desc: "描述",
    id: "id",
    deleted: "已删除",
    scmpath: "源代码地址",
    filepath: "下载地址",
    stories: "完成的需求",
    bugs: "解决的Bug",
    date: "打包日期",
    product: "产品",
    branch: "平台/分支",
    project: "所属项目",
    productname: "产品名称",
    ids: "Bug版本健值",
    files: "附件",
    rebuild: "重新构建",
    releasetype: "运行模式",
    frontapplication: "系统应用",
    backgroundid: "后台体系",
    sqlid: "运行数据库",
    createbugcnt: "产生的bug",
    builderpk: "构建者（选择）",
    noticeusers: "消息通知用户",
  },
	views: {
		optionview: {
			caption: "版本",
      		title: "版本选项操作视图",
		},
		editformeditview: {
			caption: "版本",
      		title: "版本编辑视图",
		},
		maingridview: {
			caption: "版本",
      		title: "版本表格视图",
		},
		testroundsgridview: {
			caption: "轮次",
      		title: "版本表格视图（轮次）",
		},
		mainview: {
			caption: "版本",
      		title: "版本编辑视图",
		},
		editview: {
			caption: "版本",
      		title: "版本编辑视图",
		},
		maintabexpview: {
			caption: "版本",
      		title: "版本分页导航视图",
		},
	},
	main_form: {
		details: {
			druipart2: "", 
			grouppanel2: "附件", 
			group1: "基本信息", 
			druipart1: "", 
			grouppanel1: "历史记录", 
			formpage1: "基本信息", 
			srforikey: "", 
			srfkey: "id", 
			srfmajortext: "名称编号", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			productname: "产品", 
			name: "名称编号", 
			builder: "构建者", 
			date: "打包日期", 
			scmpath: "源代码地址", 
			filepath: "下载地址", 
			desc: "描述", 
			id: "id", 
			product: "产品", 
		},
		uiactions: {
		},
	},
	editform_form: {
		details: {
			grouppanel2: "分组面板", 
			group1: "基本信息", 
			formpage1: "基本信息", 
			srforikey: "", 
			srfkey: "id", 
			srfmajortext: "名称编号", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			project: "所属项目", 
			productname: "产品", 
			name: "名称编号", 
			builder: "构建者", 
			date: "打包日期", 
			scmpath: "源代码地址", 
			filepath: "下载地址", 
			files: "上传发行包", 
			desc: "描述", 
			id: "id", 
			product: "产品", 
		},
		uiactions: {
		},
	},
	quickcreate_form: {
		details: {
			grouppanel2: "分组面板", 
			group1: "基本信息", 
			formpage1: "基本信息", 
			srforikey: "", 
			srfkey: "id", 
			srfmajortext: "名称编号", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			project: "所属项目", 
			productname: "产品", 
			name: "名称编号", 
			builder: "构建者", 
			date: "打包日期", 
			scmpath: "源代码地址", 
			filepath: "下载地址", 
			files: "上传发行包", 
			desc: "描述", 
			id: "id", 
			product: "产品", 
		},
		uiactions: {
		},
	},
	testbuildrelease_form: {
		details: {
			formpage1: "基本信息", 
			srforikey: "", 
			srfkey: "id", 
			srfmajortext: "名称编号", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			releasetype: "运行模式", 
			backgroundid: "后台体系", 
			sqlid: "运行数据库", 
			frontapplication: "系统应用", 
			rebuild: "重新构建", 
			id: "id", 
		},
		uiactions: {
		},
	},
	main_grid: {
		nodata: "",
		columns: {
			id: "ID",
			productname: "产品名称",
			name: "名称编号",
			scmpath: "源代码地址",
			filepath: "下载地址",
			date: "打包日期",
			builder: "构建者",
			uagridcolumn1: "操作",
		},
    exportColumns: {
			id: "ID",
			productname: "产品名称",
			name: "名称编号",
			scmpath: "源代码地址",
			filepath: "下载地址",
			date: "打包日期",
			builder: "构建者",
			product: "产品",
			project: "所属项目",
    },
		uiactions: {
        build_linkstories: "关联需求",
        build_submittotesting: "提交测试",
        build_viewbugs: "查看Bug",
        build_editbuild: "编辑版本",
        build_delete: "删除",
		},
	},
	testrounds_grid: {
		nodata: "",
		columns: {
			id: "ID",
			productname: "产品名称",
			name: "名称编号",
			scmpath: "源代码地址",
			filepath: "下载地址",
			builder: "构建者",
			date: "打包日期",
		},
		uiactions: {
		},
	},
	editformeditviewtoolbar_toolbar: {
		deuiaction1: {
			caption: "Save And Close",
			tip: "Save And Close Window",
		},
	},
	editviewtoolbar_toolbar: {
		deuiaction1: {
			caption: "Save And Close",
			tip: "Save And Close Window",
		},
	},
	maingridviewtoolbar_toolbar: {
		deuiaction3_create: {
			caption: "创建版本",
			tip: "创建版本",
		},
		deuiaction2: {
			caption: "刷新",
			tip: "刷新",
		},
		deuiaction1: {
			caption: "Export",
			tip: "Export {0} Data To Excel",
		},
		deuiaction4: {
			caption: "Filter",
			tip: "Filter",
		},
	},
	testroundsgridviewtoolbar_toolbar: {
		deuiaction1: {
			caption: "Filter",
			tip: "Filter",
		},
	},
};