import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    productLineName:  commonLogic.appcommonhandle("产品线名称",null),
    productLineId:  commonLogic.appcommonhandle("产品线标识",null),
    updateMan:  commonLogic.appcommonhandle("更新人",null),
    updateDate:  commonLogic.appcommonhandle("更新时间",null),
    createDate:  commonLogic.appcommonhandle("建立时间",null),
    createMan:  commonLogic.appcommonhandle("建立人",null),
    order:  commonLogic.appcommonhandle("排序",null),
  },
	views: {
		mobpickupview: {
			caption: commonLogic.appcommonhandle("产品线（废弃）",null),
		},
		mobpickupmdview: {
			caption: commonLogic.appcommonhandle("产品线（废弃）",null),
		},
	},
};