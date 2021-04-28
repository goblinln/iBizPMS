import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    type: "文档类型",
    doclibtype: "文件库类型",
    isfavourites: "是否收藏",
    collector: "收藏者",
    updatemanname: "由谁更新",
    mdeptid: "部门标识",
    orgid: "组织标识",
    acl: "权限",
    root: "Root",
    deleted: "已删除",
    updateby: "由谁更新",
    groups: "分组",
    doccnt: "文档数量",
    id: "编号",
    users: "用户",
    main: "是否是主库",
    name: "文档库名称",
    modulecnt: "文件夹数",
    createby: "由谁创建",
    openeddate: "创建时间",
    order: "排序",
    productname: "所属产品",
    projectname: "所属项目",
    project: "项目库",
    product: "产品库",
    deptname: "归属部门名",
    orgname: "归属组织名",
  },
	views: {
		projectdoclibmobtreeview: {
			caption: commonLogic.appcommonhandle("项目",null),
		},
		mobproducttreeview: {
			caption: commonLogic.appcommonhandle("产品",null),
		},
		mobeditview: {
			caption: commonLogic.appcommonhandle("文档库",null),
		},
	},
	mobmain_form: {
		details: {
			group1: commonLogic.appcommonhandle("文档库基本信息",null), 
			group2: commonLogic.appcommonhandle("操作信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("文档库名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			name: commonLogic.appcommonhandle("文档库名称",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
};