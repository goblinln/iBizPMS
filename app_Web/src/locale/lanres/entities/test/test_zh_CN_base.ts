import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'orgid': commonLogic.appcommonhandle("组织标识",null),
		'updatedate': commonLogic.appcommonhandle("更新时间",null),
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
		'updateman': commonLogic.appcommonhandle("更新人",null),
		'supproreport': commonLogic.appcommonhandle("支持产品汇报",null),
		'order': commonLogic.appcommonhandle("排序",null),
		'type': commonLogic.appcommonhandle("产品类型",null),
		'mdeptname': commonLogic.appcommonhandle("归属部门名",null),
		'po': commonLogic.appcommonhandle("产品负责人",null),
		'qdpk': commonLogic.appcommonhandle("测试负责人（选择）",null),
		'desc': commonLogic.appcommonhandle("产品描述	",null),
		'status': commonLogic.appcommonhandle("状态",null),
		'changedstorycnt': commonLogic.appcommonhandle("已变更需求",null),
		'activebugcnt': commonLogic.appcommonhandle("未解决Bug数",null),
		'createdby': commonLogic.appcommonhandle("由谁创建",null),
		'rdpk': commonLogic.appcommonhandle("发布负责人（选择）",null),
		'createman': commonLogic.appcommonhandle("建立人",null),
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
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'createddate': commonLogic.appcommonhandle("创建日期",null),
		'noticeusers': commonLogic.appcommonhandle("消息通知用户",null),
		'activestorycnt': commonLogic.appcommonhandle("激活需求数",null),
		'productsn': commonLogic.appcommonhandle("产品编号",null),
		'line': commonLogic.appcommonhandle("id",null),
		'linename': commonLogic.appcommonhandle("产品线",null),
	},
		views: {
			'testtabexpview': {
				caption: commonLogic.appcommonhandle("测试",null),
				title: commonLogic.appcommonhandle("测试",null),
			},
			'testleftsidebarlistview': {
				caption: commonLogic.appcommonhandle("测试",null),
				title: commonLogic.appcommonhandle("所有测试",null),
			},
			'casetreeexpview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("产品需求导航视图",null),
			},
			'bugtreeexpview': {
				caption: commonLogic.appcommonhandle("产品",null),
				title: commonLogic.appcommonhandle("产品需求导航视图",null),
			},
		},
		testsidebar_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				test_producttop: commonLogic.appcommonhandle("置顶",null),
				test_cancelproducttop: commonLogic.appcommonhandle("取消置顶",null),
			},
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
	};
	return data;
}
export default getLocaleResourceBase;