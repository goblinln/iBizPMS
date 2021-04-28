import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    updateDate:  commonLogic.appcommonhandle("更新时间",null),
    updateMan:  commonLogic.appcommonhandle("更新人",null),
    createMan:  commonLogic.appcommonhandle("建立人",null),
    createDate:  commonLogic.appcommonhandle("建立时间",null),
    sysUpdateLogId:  commonLogic.appcommonhandle("系统更新日志标识",null),
    updatebranch:  commonLogic.appcommonhandle("更新平台",null),
    updatefeatures:  commonLogic.appcommonhandle("更新功能列表详情",null),
    updesc:  commonLogic.appcommonhandle("更新说明",null),
    sysUpdateLogName:  commonLogic.appcommonhandle("更新名称",null),
    latestupdate:  commonLogic.appcommonhandle("最新更新",null),
    update:  commonLogic.appcommonhandle("更新日期",null),
  },
	views: {
		mobeditview: {
			caption: commonLogic.appcommonhandle("更新日志",null),
		},
		mobmdview: {
			caption: commonLogic.appcommonhandle("更新日志",null),
		},
	},
	mobmain_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("功能增强",null), 
			druipart2: commonLogic.appcommonhandle("",null), 
			grouppanel2: commonLogic.appcommonhandle("优化",null), 
			group1: commonLogic.appcommonhandle("系统更新日志基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("系统更新日志标识",null), 
			srfmajortext: commonLogic.appcommonhandle("更新名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			sys_update_logname: commonLogic.appcommonhandle("",null), 
			update: commonLogic.appcommonhandle("",null), 
			updesc: commonLogic.appcommonhandle("",null), 
			sys_update_logid: commonLogic.appcommonhandle("系统更新日志标识",null), 
		},
		uiactions: {
		},
	},
};