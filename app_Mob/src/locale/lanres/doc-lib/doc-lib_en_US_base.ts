import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    type:  commonLogic.appcommonhandle("文档类型",null),
    doclibtype:  commonLogic.appcommonhandle("文件库类型",null),
    isfavourites:  commonLogic.appcommonhandle("是否收藏",null),
    collector:  commonLogic.appcommonhandle("收藏者",null),
    orgName:  commonLogic.appcommonhandle("归属组织名",null),
    updateManName:  commonLogic.appcommonhandle("由谁更新",null),
    mdeptId:  commonLogic.appcommonhandle("部门标识",null),
    orgId:  commonLogic.appcommonhandle("组织标识",null),
    acl:  commonLogic.appcommonhandle("权限",null),
    root:  commonLogic.appcommonhandle("Root",null),
    deleted:  commonLogic.appcommonhandle("已删除",null),
    updateBy:  commonLogic.appcommonhandle("由谁更新",null),
    groups:  commonLogic.appcommonhandle("分组",null),
    doccnt:  commonLogic.appcommonhandle("文档数量",null),
    id:  commonLogic.appcommonhandle("编号",null),
    deptName:  commonLogic.appcommonhandle("归属部门名",null),
    users:  commonLogic.appcommonhandle("用户",null),
    main:  commonLogic.appcommonhandle("是否是主库",null),
    name:  commonLogic.appcommonhandle("文档库名称",null),
    modulecnt:  commonLogic.appcommonhandle("文件夹数",null),
    createBy:  commonLogic.appcommonhandle("由谁创建",null),
    openedDate:  commonLogic.appcommonhandle("创建时间",null),
    order:  commonLogic.appcommonhandle("排序",null),
    productName:  commonLogic.appcommonhandle("所属产品",null),
    projectName:  commonLogic.appcommonhandle("所属项目",null),
    project:  commonLogic.appcommonhandle("项目库",null),
    product:  commonLogic.appcommonhandle("产品库",null),
    doclibsn:  commonLogic.appcommonhandle("文档库编号",null),
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
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'tbitem1',
		},
	},
};