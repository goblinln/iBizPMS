import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    orgName:  commonLogic.appcommonhandle("归属组织名",null),
    type:  commonLogic.appcommonhandle("类型（story）",null),
    mdeptName:  commonLogic.appcommonhandle("归属部门名",null),
    mdeptId:  commonLogic.appcommonhandle("部门标识",null),
    path:  commonLogic.appcommonhandle("path",null),
    orgId:  commonLogic.appcommonhandle("组织机构标识",null),
    owner:  commonLogic.appcommonhandle("owner",null),
    updateBy:  commonLogic.appcommonhandle("由谁更新",null),
    createBy:  commonLogic.appcommonhandle("由谁创建",null),
    order:  commonLogic.appcommonhandle("排序值",null),
    deleted:  commonLogic.appcommonhandle("逻辑删除标志",null),
    branch:  commonLogic.appcommonhandle("branch",null),
    collector:  commonLogic.appcommonhandle("collector",null),
    id:  commonLogic.appcommonhandle("id",null),
    isLeaf:  commonLogic.appcommonhandle("叶子模块",null),
    name:  commonLogic.appcommonhandle("名称",null),
    iBizShort:  commonLogic.appcommonhandle("简称",null),
    grade:  commonLogic.appcommonhandle("grade",null),
    parentName:  commonLogic.appcommonhandle("上级模块",null),
    rootName:  commonLogic.appcommonhandle("测试",null),
    root:  commonLogic.appcommonhandle("编号",null),
    parent:  commonLogic.appcommonhandle("id",null),
  },
	views: {
		mobpickupview: {
			caption: commonLogic.appcommonhandle("测试模块",null),
		},
		mobpickupmdview: {
			caption: commonLogic.appcommonhandle("测试模块",null),
		},
	},
};