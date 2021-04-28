import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    sysUpdateFeaturesName:  commonLogic.appcommonhandle("系统更新功能名称",null),
    updateDate:  commonLogic.appcommonhandle("更新时间",null),
    createMan:  commonLogic.appcommonhandle("建立人",null),
    updateMan:  commonLogic.appcommonhandle("更新人",null),
    type:  commonLogic.appcommonhandle("更新类型",null),
    createDate:  commonLogic.appcommonhandle("建立时间",null),
    upfeatures:  commonLogic.appcommonhandle("更新功能",null),
    sysUpdateFeaturesId:  commonLogic.appcommonhandle("系统更新功能标识",null),
    displayorder:  commonLogic.appcommonhandle("展示顺序",null),
    featuresdesc:  commonLogic.appcommonhandle("功能描述",null),
    sysUpdateLogName:  commonLogic.appcommonhandle("所属更新",null),
    sysUpdateLogId:  commonLogic.appcommonhandle("系统更新日志标识",null),
  },
	views: {
		mobeditview: {
			caption: commonLogic.appcommonhandle("系统更新功能",null),
		},
		ymobmdview9: {
			caption: commonLogic.appcommonhandle("优化",null),
		},
		mobmdview: {
			caption: commonLogic.appcommonhandle("功能增强",null),
		},
		mobmdview9: {
			caption: commonLogic.appcommonhandle("功能增强",null),
		},
	},
	mobmain_form: {
		details: {
			group1: commonLogic.appcommonhandle("系统更新功能基本信息",null), 
			group2: commonLogic.appcommonhandle("操作信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("系统更新功能标识",null), 
			srfmajortext: commonLogic.appcommonhandle("系统更新功能名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			sys_update_featuresname: commonLogic.appcommonhandle("系统更新功能名称",null), 
			createman: commonLogic.appcommonhandle("建立人",null), 
			createdate: commonLogic.appcommonhandle("建立时间",null), 
			updateman: commonLogic.appcommonhandle("更新人",null), 
			updatedate: commonLogic.appcommonhandle("更新时间",null), 
			sys_update_featuresid: commonLogic.appcommonhandle("系统更新功能标识",null), 
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