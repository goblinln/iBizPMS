import commonLogic from '@/locale/logic/common/common-logic';
function getLocaleResourceBase(){
	const data:any = {
		appdename: commonLogic.appcommonhandle("单位", null),
		fields: {
			orgid: commonLogic.appcommonhandle("单位标识",null),
			orgcode: commonLogic.appcommonhandle("单位代码",null),
			orgname: commonLogic.appcommonhandle("名称",null),
			parentorgid: commonLogic.appcommonhandle("上级单位",null),
			shortname: commonLogic.appcommonhandle("单位简称",null),
			orglevel: commonLogic.appcommonhandle("单位级别",null),
			showorder: commonLogic.appcommonhandle("访问审计",null),
			parentorgname: commonLogic.appcommonhandle("上级单位",null),
			domains: commonLogic.appcommonhandle("区属",null),
			enable: commonLogic.appcommonhandle("逻辑有效",null),
			createdate: commonLogic.appcommonhandle("创建时间",null),
			updatedate: commonLogic.appcommonhandle("最后修改时间",null),
		},
			views: {
				usr2gridview: {
					caption: commonLogic.appcommonhandle("单位",null),
					title: commonLogic.appcommonhandle("单位单位实体表格视图(通讯录所有组织)",null),
				},
				editview: {
					caption: commonLogic.appcommonhandle("单位",null),
					title: commonLogic.appcommonhandle("单位编辑视图",null),
				},
			},
			main_form: {
				details: {
					group1: commonLogic.appcommonhandle("单位基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srfupdatedate: commonLogic.appcommonhandle("最后修改时间",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("单位标识",null), 
					srfmajortext: commonLogic.appcommonhandle("名称",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					orgid: commonLogic.appcommonhandle("单位标识",null), 
				},
				uiactions: {
				},
			},
			main_grid: {
				columns: {
					orgid: commonLogic.appcommonhandle("单位标识",null),
					shortname: commonLogic.appcommonhandle("单位简称",null),
					domains: commonLogic.appcommonhandle("区属",null),
					orgname: commonLogic.appcommonhandle("名称",null),
					orglevel: commonLogic.appcommonhandle("单位级别",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				},
			},
			default_searchform: {
				details: {
					formpage1: commonLogic.appcommonhandle("常规条件",null), 
				},
				uiactions: {
				},
			},
			usr2gridviewtoolbar_toolbar: {
			},
			editviewtoolbar_toolbar: {
				tbitem3: {
					caption: commonLogic.appcommonhandle("保存",null),
					tip: commonLogic.appcommonhandle("保存",null),
				},
				tbitem4: {
					caption: commonLogic.appcommonhandle("保存并新建",null),
					tip: commonLogic.appcommonhandle("保存并新建",null),
				},
				tbitem5: {
					caption: commonLogic.appcommonhandle("保存并关闭",null),
					tip: commonLogic.appcommonhandle("保存并关闭",null),
				},
				tbitem7: {
					caption: commonLogic.appcommonhandle("删除",null),
					tip: commonLogic.appcommonhandle("删除",null),
				},
			},
		};
		return data;
}
export default getLocaleResourceBase;