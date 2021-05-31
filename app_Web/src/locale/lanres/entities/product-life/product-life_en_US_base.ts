import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'branch': commonLogic.appcommonhandle("平台/分支",null),
		'createman': commonLogic.appcommonhandle("建立人",null),
		'updatedate': commonLogic.appcommonhandle("更新时间",null),
		'productlifename': commonLogic.appcommonhandle("产品生命周期名称",null),
		'createdate': commonLogic.appcommonhandle("建立时间",null),
		'parent': commonLogic.appcommonhandle("父对象",null),
		'year': commonLogic.appcommonhandle("年",null),
		'updateman': commonLogic.appcommonhandle("更新人",null),
		'type': commonLogic.appcommonhandle("属性",null),
		'marker': commonLogic.appcommonhandle("里程碑",null),
		'begin': commonLogic.appcommonhandle("开始日期",null),
		'productlifeid': commonLogic.appcommonhandle("产品生命周期标识",null),
		'end': commonLogic.appcommonhandle("结束日期",null),
		'product': commonLogic.appcommonhandle("编号",null),
	},
		views: {
			'roadmaplistview': {
				caption: commonLogic.appcommonhandle("产品生命周期",null),
				title: commonLogic.appcommonhandle("路线图",null),
			},
			'roadmaplistview9': {
				caption: commonLogic.appcommonhandle("产品生命周期",null),
				title: commonLogic.appcommonhandle("路线图",null),
			},
			'roadmapslistview9': {
				caption: commonLogic.appcommonhandle("产品生命周期",null),
				title: commonLogic.appcommonhandle("路线图",null),
			},
		},
		getroadmaps_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		getroadmapyears_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		getroadmap_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		getroadmapyear_list: {
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;