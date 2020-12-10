import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    productlinename: "产品线名称",
    productlineid: "产品线标识",
    updateman: "更新人",
    updatedate: "更新时间",
    createdate: "建立时间",
    createman: "建立人",
  },
	views: {
		mobpickupmdview: {
			caption: commonLogic.appcommonhandle("产品线",null),
		},
		mobpickupview: {
			caption: commonLogic.appcommonhandle("产品线",null),
		},
	},
};