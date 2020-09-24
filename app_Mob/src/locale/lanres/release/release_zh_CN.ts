export default {
  fields: {
    stories: "完成的需求",
    marker: "里程碑",
    id: "ID",
    leftbugs: "遗留的Bug",
    bugs: "解决的Bug",
    deleted: "已删除",
    name: "发布名称",
    date: "发布日期",
    status: "状态",
    substatus: "子状态",
    desc: "描述",
    buildname: "版本",
    product: "产品",
    build: "版本",
    branch: "平台/分支",
    productname: "产品名称",
    files: "附件",
    backgroundid: "后台体系",
    sqlid: "运行数据库",
    frontapplication: "系统应用",
    rebuild: "重新构建",
    releasetype: "运行模式",
    builder: "构建者",
    builddate: "打包日期",
  },
	views: {
		mobeditview: {
			caption: '发布',
		},
		mobmdview: {
			caption: '发布',
		},
		newmobeditview: {
			caption: '发布',
		},
	},
	mobnewform_form: {
		details: {
			grouppanel1: '分组面板', 
			group1: 'release基本信息', 
			formpage1: '基本信息', 
			srforikey: '', 
			srfkey: 'ID', 
			srfmajortext: '发布名称', 
			srftempmode: '', 
			srfuf: '', 
			srfdeid: '', 
			srfsourcekey: '', 
			product: '产品', 
			productname: '产品名称', 
			name: '发布名称', 
			marker: '里程碑', 
			buildname: '版本', 
			date: '发布日期', 
			desc: '描述', 
			id: 'ID', 
		},
		uiactions: {
		},
	},
	mobmain_form: {
		details: {
			group1: '发布基本信息', 
			group2: '操作信息', 
			formpage1: '基本信息', 
			srforikey: '', 
			srfkey: 'ID', 
			srfmajortext: '发布名称', 
			srftempmode: '', 
			srfuf: '', 
			srfdeid: '', 
			srfsourcekey: '', 
			name: '发布名称', 
			date: '发布日期', 
			buildname: '版本', 
			marker: '里程碑', 
			status: '状态', 
			id: 'ID', 
		},
		uiactions: {
		},
	},
	mobdef_searchform: {
		details: {
			formpage1: '常规条件', 
			n_name_like: '发布名称', 
			n_status_eq: '状态(等于', 
			n_releasetype_eq: '运行模式', 
		},
		uiactions: {
		},
	},
	newmobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: '保存',
			tip: '保存',
		},
	},
	mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: '创建发布',
			tip: '创建发布',
		},
	},
	mobeditviewrighttoolbar_toolbar: {
	},
};