import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		type: commonLogic.appcommonhandle("文档类型",null),
		doclibtype: commonLogic.appcommonhandle("文件库类型",null),
		isfavourites: commonLogic.appcommonhandle("是否收藏",null),
		collector: commonLogic.appcommonhandle("收藏者",null),
		mdeptid: commonLogic.appcommonhandle("部门标识",null),
		orgid: commonLogic.appcommonhandle("组织标识",null),
		acl: commonLogic.appcommonhandle("权限",null),
		root: commonLogic.appcommonhandle("Root",null),
		deleted: commonLogic.appcommonhandle("已删除",null),
		groups: commonLogic.appcommonhandle("分组",null),
		doccnt: commonLogic.appcommonhandle("文档数量",null),
		id: commonLogic.appcommonhandle("编号",null),
		users: commonLogic.appcommonhandle("用户",null),
		main: commonLogic.appcommonhandle("是否是主库",null),
		name: commonLogic.appcommonhandle("文档库名称",null),
		modulecnt: commonLogic.appcommonhandle("文件夹数",null),
		openeddate: commonLogic.appcommonhandle("创建时间",null),
		order: commonLogic.appcommonhandle("排序",null),
		productname: commonLogic.appcommonhandle("所属产品",null),
		projectname: commonLogic.appcommonhandle("所属项目",null),
		project: commonLogic.appcommonhandle("项目库",null),
		product: commonLogic.appcommonhandle("产品库",null),
	},
		views: {
			usr2treeview: {
				caption: commonLogic.appcommonhandle("文档库",null),
				title: commonLogic.appcommonhandle("文档库树视图",null),
			},
			producttreeviewcust: {
				caption: commonLogic.appcommonhandle("自定义文档库",null),
				title: commonLogic.appcommonhandle("文档库树视图（自定义文档库）",null),
			},
			dashboardview: {
				caption: commonLogic.appcommonhandle("文档主页",null),
				title: commonLogic.appcommonhandle("文档库数据看板视图",null),
			},
			pickupview: {
				caption: commonLogic.appcommonhandle("文档库",null),
				title: commonLogic.appcommonhandle("doclib数据选择视图",null),
			},
			undoneprojecttreeexpview: {
				caption: commonLogic.appcommonhandle("未完成项目",null),
				title: commonLogic.appcommonhandle("文档库树导航视图",null),
			},
			editview: {
				caption: commonLogic.appcommonhandle("文档库",null),
				title: commonLogic.appcommonhandle("创建文档库",null),
			},
			pickupgridview: {
				caption: commonLogic.appcommonhandle("文档库",null),
				title: commonLogic.appcommonhandle("doclib选择表格视图",null),
			},
			gridview: {
				caption: commonLogic.appcommonhandle("文档库",null),
				title: commonLogic.appcommonhandle("doclib表格视图",null),
			},
			customdoclibroottreeview: {
				caption: commonLogic.appcommonhandle("自定义文档库-根目录",null),
				title: commonLogic.appcommonhandle("文档库树视图（自定义文档库-根目录）",null),
			},
			projecttreeview: {
				caption: commonLogic.appcommonhandle("项目",null),
				title: commonLogic.appcommonhandle("文档库树视图",null),
			},
			byprojecttreeexpview: {
				caption: commonLogic.appcommonhandle("项目下属文档库",null),
				title: commonLogic.appcommonhandle("文档库树导航视图（项目下属）",null),
			},
			libtabexpview: {
				caption: commonLogic.appcommonhandle("文档库主页",null),
				title: commonLogic.appcommonhandle("文档库分页导航视图（文档）",null),
			},
			usr2editview: {
				caption: commonLogic.appcommonhandle("文档库",null),
				title: commonLogic.appcommonhandle("文档库编辑视图",null),
			},
			undoneprojectgridview: {
				caption: commonLogic.appcommonhandle("文档库",null),
				title: commonLogic.appcommonhandle("doclib表格视图",null),
			},
			productgridview: {
				caption: commonLogic.appcommonhandle("文档库",null),
				title: commonLogic.appcommonhandle("doclib表格视图",null),
			},
			treeview: {
				caption: commonLogic.appcommonhandle("文档库",null),
				title: commonLogic.appcommonhandle("文档库树视图",null),
			},
			treeviewproductroot: {
				caption: commonLogic.appcommonhandle("文档库",null),
				title: commonLogic.appcommonhandle("文档库树视图",null),
			},
			productgridexpview: {
				caption: commonLogic.appcommonhandle("产品文档库导航视图",null),
				title: commonLogic.appcommonhandle("文档库表格导航视图（产品文档库导航视图）",null),
			},
			usr3treeview: {
				caption: commonLogic.appcommonhandle("文档库",null),
				title: commonLogic.appcommonhandle("文档库树视图",null),
			},
			producttreeview: {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("文档库树视图（产品）",null),
			},
			customtreeexpview: {
				caption: commonLogic.appcommonhandle("自定义文档库",null),
				title: commonLogic.appcommonhandle("文档库树导航视图（自定义文档库）",null),
			},
			producttreeexpview: {
				caption: commonLogic.appcommonhandle("产品文档库",null),
				title: commonLogic.appcommonhandle("文档库树导航视图（产品文档库）",null),
			},
			customdoclibchildtreeview: {
				caption: commonLogic.appcommonhandle("自定义文档库-子目录",null),
				title: commonLogic.appcommonhandle("文档库树视图（自定义文档库-子目录）",null),
			},
			projecttreeexpview: {
				caption: commonLogic.appcommonhandle("项目文档库",null),
				title: commonLogic.appcommonhandle("文档库树导航视图（项目文档库）",null),
			},
			byproducttreeexpview: {
				caption: commonLogic.appcommonhandle("产品下属文档库",null),
				title: commonLogic.appcommonhandle("文档库树导航视图（产品下属）",null),
			},
		},
		main_form: {
			details: {
				group1: commonLogic.appcommonhandle("doclib基本信息",null), 
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("编号",null), 
				srfmajortext: commonLogic.appcommonhandle("文档库名称",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				type: commonLogic.appcommonhandle("文档类型",null), 
				projectname: commonLogic.appcommonhandle("所属项目",null), 
				productname: commonLogic.appcommonhandle("所属产品",null), 
				name: commonLogic.appcommonhandle("文档库名称",null), 
				acl: commonLogic.appcommonhandle("访问控制",null), 
				collector: commonLogic.appcommonhandle("收藏者",null), 
				project: commonLogic.appcommonhandle("项目库",null), 
				product: commonLogic.appcommonhandle("产品库",null), 
				id: commonLogic.appcommonhandle("编号",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				name: commonLogic.appcommonhandle("文档库名称",null),
				doccnt: commonLogic.appcommonhandle("文档数量",null),
				uagridcolumn1: commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				doclib_lookmainprojectdoclib: commonLogic.appcommonhandle("查看",null),
				doclib_editprojectdoclib: commonLogic.appcommonhandle("编辑",null),
				doclib_weihufenlei: commonLogic.appcommonhandle("维护分类",null),
				doclib_collect: commonLogic.appcommonhandle("收藏",null),
				doclib_uncollect: commonLogic.appcommonhandle("取消收藏",null),
			},
		},
		productmain_grid: {
			columns: {
				name: commonLogic.appcommonhandle("文档库名称",null),
				doccnt: commonLogic.appcommonhandle("文档数量",null),
				uagridcolumn1: commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				doclib_lookmainproductdoclib: commonLogic.appcommonhandle("查看",null),
				doclib_editdoclib: commonLogic.appcommonhandle("编辑",null),
				doclib_weihufenlei: commonLogic.appcommonhandle("维护分类",null),
				doclib_collect: commonLogic.appcommonhandle("收藏",null),
				doclib_uncollect: commonLogic.appcommonhandle("取消收藏",null),
			},
		},
		expproductmain_grid: {
			columns: {
				name: commonLogic.appcommonhandle("文档库名称",null),
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
		usr2editviewtoolbar_toolbar: {
			deuiaction1: {
				caption: commonLogic.appcommonhandle("保存并关闭",null),
				tip: commonLogic.appcommonhandle("保存并关闭",null),
			},
		},
		editviewtoolbar_toolbar: {
			deuiaction1: {
				caption: commonLogic.appcommonhandle("保存并关闭",null),
				tip: commonLogic.appcommonhandle("保存并关闭",null),
			},
		},
		libtabexpviewtoolbar_toolbar: {
			items1: {
				caption: commonLogic.appcommonhandle("快速访问",null),
				tip: commonLogic.appcommonhandle("快速访问",null),
			},
			deuiaction1: {
				caption: commonLogic.appcommonhandle("最近更新",null),
				tip: commonLogic.appcommonhandle("最近更新",null),
			},
			deuiaction2: {
				caption: commonLogic.appcommonhandle("我的文档",null),
				tip: commonLogic.appcommonhandle("我的文档",null),
			},
			deuiaction3: {
				caption: commonLogic.appcommonhandle("我的收藏",null),
				tip: commonLogic.appcommonhandle("我的收藏",null),
			},
			deuiaction4: {
				caption: commonLogic.appcommonhandle("创建文档库",null),
				tip: commonLogic.appcommonhandle("创建文档库",null),
			},
			deuiaction6: {
				caption: commonLogic.appcommonhandle("创建文档",null),
				tip: commonLogic.appcommonhandle("创建文档",null),
			},
		},
		gridviewtoolbar_toolbar: {
			deuiaction1_togglefilter: {
				caption: commonLogic.appcommonhandle("过滤",null),
				tip: commonLogic.appcommonhandle("过滤",null),
			},
		},
		undoneprojectgridviewtoolbar_toolbar: {
			deuiaction1_togglefilter: {
				caption: commonLogic.appcommonhandle("过滤",null),
				tip: commonLogic.appcommonhandle("过滤",null),
			},
		},
		productgridviewtoolbar_toolbar: {
			deuiaction1_togglefilter: {
				caption: commonLogic.appcommonhandle("过滤",null),
				tip: commonLogic.appcommonhandle("过滤",null),
			},
		},
		doclibtreeproject_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				files: commonLogic.appcommonhandle("附件库",null),
				root: commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
				file_ibzdownload: commonLogic.appcommonhandle("下载",null),
				file_delete: commonLogic.appcommonhandle("删除",null),
				doclibmodule_edit: commonLogic.appcommonhandle("编辑",null),
				doclibmodule_weihufenlei: commonLogic.appcommonhandle("维护分类",null),
				doclibmodule_favorite: commonLogic.appcommonhandle("收藏",null),
				doclibmodule_nfavorite: commonLogic.appcommonhandle("取消收藏",null),
				doc_edit: commonLogic.appcommonhandle("编辑",null),
				doc_delete: commonLogic.appcommonhandle("删除",null),
				doc_onlycollectdoc: commonLogic.appcommonhandle("收藏",null),
				doc_onlyuncollectdoc: commonLogic.appcommonhandle("取消收藏",null),
				doc_docinfo: commonLogic.appcommonhandle("查看文档详情",null),
				doclib_editdoclib: commonLogic.appcommonhandle("编辑",null),
				doclib_weihufenlei: commonLogic.appcommonhandle("维护分类",null),
				doclib_uncollect: commonLogic.appcommonhandle("取消收藏",null),
				doclib_collect: commonLogic.appcommonhandle("收藏",null),
			},
		},
		rootdoclibprojecttreeveiw_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				root: commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
				doclibmodule_edit: commonLogic.appcommonhandle("编辑",null),
				doclibmodule_weihufenlei: commonLogic.appcommonhandle("维护分类",null),
				doclibmodule_favorite: commonLogic.appcommonhandle("收藏",null),
				doclibmodule_nfavorite: commonLogic.appcommonhandle("取消收藏",null),
				doc_edit: commonLogic.appcommonhandle("编辑",null),
				doc_delete: commonLogic.appcommonhandle("删除",null),
				doc_onlycollectdoc: commonLogic.appcommonhandle("收藏",null),
				doc_onlyuncollectdoc: commonLogic.appcommonhandle("取消收藏",null),
				doc_docinfo: commonLogic.appcommonhandle("查看文档详情",null),
			},
		},
		childdoclibmoduletree_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				root: commonLogic.appcommonhandle("默认根节点",null),
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
			},
		},
		projectleftdoclibtree_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				files: commonLogic.appcommonhandle("附件库",null),
				all: commonLogic.appcommonhandle("所有文档库",null),
				root: commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
				refreshall: commonLogic.appcommonhandle("刷新",null),
			},
		},
		doclibtreeproduct_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				files: commonLogic.appcommonhandle("附件库",null),
				root: commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
				doclibmodule_edit: commonLogic.appcommonhandle("编辑",null),
				doclibmodule_weihufenlei: commonLogic.appcommonhandle("维护分类",null),
				doclibmodule_favorite: commonLogic.appcommonhandle("收藏",null),
				doclibmodule_nfavorite: commonLogic.appcommonhandle("取消收藏",null),
				doclib_editdoclib: commonLogic.appcommonhandle("编辑",null),
				doclib_weihufenlei: commonLogic.appcommonhandle("维护分类",null),
				doclib_uncollect: commonLogic.appcommonhandle("取消收藏",null),
				doclib_collect: commonLogic.appcommonhandle("收藏",null),
				file_ibzdownload: commonLogic.appcommonhandle("下载",null),
				file_delete: commonLogic.appcommonhandle("删除",null),
				doc_edit: commonLogic.appcommonhandle("编辑",null),
				doc_delete: commonLogic.appcommonhandle("删除",null),
				doc_onlycollectdoc: commonLogic.appcommonhandle("收藏",null),
				doc_onlyuncollectdoc: commonLogic.appcommonhandle("取消收藏",null),
				doc_docinfo: commonLogic.appcommonhandle("查看文档详情",null),
			},
		},
		docchildproducttreeview_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				root: commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
				doclibmodule_edit: commonLogic.appcommonhandle("编辑",null),
				doclibmodule_weihufenlei: commonLogic.appcommonhandle("维护分类",null),
				doclibmodule_favorite: commonLogic.appcommonhandle("收藏",null),
				doclibmodule_nfavorite: commonLogic.appcommonhandle("取消收藏",null),
				doc_edit: commonLogic.appcommonhandle("编辑",null),
				doc_delete: commonLogic.appcommonhandle("删除",null),
				doc_onlycollectdoc: commonLogic.appcommonhandle("收藏",null),
				doc_onlyuncollectdoc: commonLogic.appcommonhandle("取消收藏",null),
				doc_docinfo: commonLogic.appcommonhandle("查看文档详情",null),
			},
		},
		doclibchildtreeview_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				root: commonLogic.appcommonhandle("默认根节点",null),
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
			},
		},
		productleftdoclibtree_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				root: commonLogic.appcommonhandle("默认根节点",null),
				all: commonLogic.appcommonhandle("所有文档库",null),
				files: commonLogic.appcommonhandle("附件库",null),
			},
			uiactions: {
				refreshall: commonLogic.appcommonhandle("刷新",null),
			},
		},
		undoneprojectdoclibtree_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				allundoneproject: commonLogic.appcommonhandle("所有未关闭项目",null),
				files: commonLogic.appcommonhandle("附件库",null),
				root: commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
			},
		},
		looproductdoclibtree_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				root: commonLogic.appcommonhandle("默认根节点",null),
				files: commonLogic.appcommonhandle("附件库",null),
				allproduct: commonLogic.appcommonhandle("所有产品",null),
			},
			uiactions: {
			},
		},
		projectdoclibtree_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				root: commonLogic.appcommonhandle("默认根节点",null),
				allproject: commonLogic.appcommonhandle("所有项目",null),
				files: commonLogic.appcommonhandle("附件库",null),
			},
			uiactions: {
			},
		},
		doclibtreecustchild_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				root: commonLogic.appcommonhandle("默认根节点",null),
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
			},
		},
		doclibtreecustroot_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				root: commonLogic.appcommonhandle("默认根节点",null),
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
			},
		},
		doclibtreecust_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				root: commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
				doclib_editdoclib: commonLogic.appcommonhandle("编辑",null),
				doclib_weihufenlei: commonLogic.appcommonhandle("维护分类",null),
				doclib_uncollect: commonLogic.appcommonhandle("取消收藏",null),
				doclib_collect: commonLogic.appcommonhandle("收藏",null),
				doclibmodule_edit: commonLogic.appcommonhandle("编辑",null),
				doclibmodule_weihufenlei: commonLogic.appcommonhandle("维护分类",null),
				doclibmodule_favorite: commonLogic.appcommonhandle("收藏",null),
				doclibmodule_nfavorite: commonLogic.appcommonhandle("取消收藏",null),
				doc_edit: commonLogic.appcommonhandle("编辑",null),
				doc_delete: commonLogic.appcommonhandle("删除",null),
				doc_onlycollectdoc: commonLogic.appcommonhandle("收藏",null),
				doc_onlyuncollectdoc: commonLogic.appcommonhandle("取消收藏",null),
				doc_docinfo: commonLogic.appcommonhandle("查看文档详情",null),
			},
		},
		customdoclibtree_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				all: commonLogic.appcommonhandle("所有文档库",null),
				root: commonLogic.appcommonhandle("默认根节点",null),
			},
			uiactions: {
				doclib_editcustomdoclib: commonLogic.appcommonhandle("编辑",null),
				refreshall: commonLogic.appcommonhandle("刷新",null),
				doclib_weihufenlei: commonLogic.appcommonhandle("维护分类",null),
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;