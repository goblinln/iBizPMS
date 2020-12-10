import commonLogic from '@/locale/logic/common/common-logic';
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
    noticeusers: "消息通知用户",
  },
	views: {
		mobtabexpview: {
			caption: commonLogic.appcommonhandle("发布",null),
		},
		mobpickupview: {
			caption: commonLogic.appcommonhandle("发布",null),
		},
		editmobeditview: {
			caption: commonLogic.appcommonhandle("发布",null),
		},
		mobeditview: {
			caption: commonLogic.appcommonhandle("发布",null),
		},
		mobmdview: {
			caption: commonLogic.appcommonhandle("发布",null),
		},
		newmobeditview: {
			caption: commonLogic.appcommonhandle("发布",null),
		},
		mobpickupmdview: {
			caption: commonLogic.appcommonhandle("发布",null),
		},
	},
	mobmain_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("附件",null), 
			druipart2: commonLogic.appcommonhandle("",null), 
			grouppanel2: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("发布基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("ID",null), 
			srfmajortext: commonLogic.appcommonhandle("发布名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			name: commonLogic.appcommonhandle("发布名称",null), 
			date: commonLogic.appcommonhandle("发布日期",null), 
			buildname: commonLogic.appcommonhandle("版本",null), 
			marker: commonLogic.appcommonhandle("里程碑",null), 
			status: commonLogic.appcommonhandle("状态",null), 
			id: commonLogic.appcommonhandle("ID",null), 
		},
		uiactions: {
		},
	},
	mobeditform_form: {
		details: {
			grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
			group1: commonLogic.appcommonhandle("release基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("ID",null), 
			srfmajortext: commonLogic.appcommonhandle("发布名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			product: commonLogic.appcommonhandle("产品",null), 
			productname: commonLogic.appcommonhandle("产品名称",null), 
			name: commonLogic.appcommonhandle("发布名称",null), 
			marker: commonLogic.appcommonhandle("里程碑",null), 
			buildname: commonLogic.appcommonhandle("版本",null), 
			date: commonLogic.appcommonhandle("发布日期",null), 
			desc: commonLogic.appcommonhandle("描述",null), 
			files: commonLogic.appcommonhandle("附件",null), 
			id: commonLogic.appcommonhandle("ID",null), 
		},
		uiactions: {
		},
	},
	mobnewform_form: {
		details: {
			grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
			group1: commonLogic.appcommonhandle("release基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("ID",null), 
			srfmajortext: commonLogic.appcommonhandle("发布名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			product: commonLogic.appcommonhandle("产品",null), 
			productname: commonLogic.appcommonhandle("产品名称",null), 
			name: commonLogic.appcommonhandle("发布名称",null), 
			marker: commonLogic.appcommonhandle("里程碑",null), 
			buildname: commonLogic.appcommonhandle("版本",null), 
			date: commonLogic.appcommonhandle("发布日期",null), 
			desc: commonLogic.appcommonhandle("描述",null), 
			files: commonLogic.appcommonhandle("附件",null), 
			id: commonLogic.appcommonhandle("ID",null), 
		},
		uiactions: {
		},
	},
	mobdef_searchform: {
		details: {
			formpage1: commonLogic.appcommonhandle("常规条件",null), 
			n_name_like: commonLogic.appcommonhandle("发布名称",null), 
			n_status_eq: commonLogic.appcommonhandle("状态",null), 
			n_date_ltandeq: commonLogic.appcommonhandle("发布日期",null), 
		},
		uiactions: {
		},
	},
	editmobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
	mobeditviewrighttoolbar_toolbar: {
		deuiaction1_mobactive: {
			caption: commonLogic.appcommonhandle("激活",null),
			tip: '激活',
		},
		deuiaction1_mobterminal: {
			caption: commonLogic.appcommonhandle("停止维护",null),
			tip: '停止维护',
		},
		deuiaction1_mobedit: {
			caption: commonLogic.appcommonhandle("编辑",null),
			tip: '编辑',
		},
		deuiaction1_mobremove: {
			caption: commonLogic.appcommonhandle("删除",null),
			tip: '删除',
		},
	},
	newmobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
	mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("创建发布",null),
			tip: '创建发布',
		},
	},
};