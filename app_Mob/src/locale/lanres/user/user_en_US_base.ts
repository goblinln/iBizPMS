import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    password:  commonLogic.appcommonhandle("密码",null),
    address:  commonLogic.appcommonhandle("通讯地址",null),
    weixin:  commonLogic.appcommonhandle("微信",null),
    dingding:  commonLogic.appcommonhandle("钉钉",null),
    fails:  commonLogic.appcommonhandle("fails",null),
    slack:  commonLogic.appcommonhandle("slack",null),
    ranzhi:  commonLogic.appcommonhandle("ranzhi",null),
    account:  commonLogic.appcommonhandle("账户",null),
    locked:  commonLogic.appcommonhandle("locked",null),
    avatar:  commonLogic.appcommonhandle("avatar",null),
    scoreLevel:  commonLogic.appcommonhandle("scoreLevel",null),
    realname:  commonLogic.appcommonhandle("真实姓名",null),
    zipcode:  commonLogic.appcommonhandle("zipcode",null),
    dept:  commonLogic.appcommonhandle("所属部门",null),
    commiter:  commonLogic.appcommonhandle("源代码账户",null),
    role:  commonLogic.appcommonhandle("职位",null),
    deleted:  commonLogic.appcommonhandle("逻辑删除标志",null),
    last:  commonLogic.appcommonhandle("最后登录",null),
    clientStatus:  commonLogic.appcommonhandle("clientStatus",null),
    skype:  commonLogic.appcommonhandle("skype",null),
    whatsapp:  commonLogic.appcommonhandle("whatsapp",null),
    score:  commonLogic.appcommonhandle("score",null),
    gender:  commonLogic.appcommonhandle("性别",null),
    mobile:  commonLogic.appcommonhandle("手机",null),
    clientLang:  commonLogic.appcommonhandle("clientLang",null),
    visits:  commonLogic.appcommonhandle("访问次数",null),
    join:  commonLogic.appcommonhandle("入职日期",null),
    email:  commonLogic.appcommonhandle("邮箱",null),
    ip:  commonLogic.appcommonhandle("ip",null),
    birthday:  commonLogic.appcommonhandle("birthday",null),
    nickname:  commonLogic.appcommonhandle("nickname",null),
    phone:  commonLogic.appcommonhandle("电话",null),
    id:  commonLogic.appcommonhandle("ID",null),
    qq:  commonLogic.appcommonhandle("QQ",null),
    usersn:  commonLogic.appcommonhandle("用户编号",null),
  },
	views: {
		mobmpickupview: {
			caption: commonLogic.appcommonhandle("用户",null),
		},
		usercentermobeditview: {
			caption: commonLogic.appcommonhandle("个人中心",null),
		},
		mobpickupmdview: {
			caption: commonLogic.appcommonhandle("用户",null),
		},
		mobpickupview: {
			caption: commonLogic.appcommonhandle("用户",null),
		},
	},
	usercenter_form: {
		details: {
			group1: commonLogic.appcommonhandle("用户基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("ID",null), 
			srfmajortext: commonLogic.appcommonhandle("真实姓名",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			realname: commonLogic.appcommonhandle("真实姓名",null), 
			gender: commonLogic.appcommonhandle("性别",null), 
			account: commonLogic.appcommonhandle("账户",null), 
			address: commonLogic.appcommonhandle("通讯地址",null), 
			dingding: commonLogic.appcommonhandle("钉钉",null), 
			phone: commonLogic.appcommonhandle("电话",null), 
			mobile: commonLogic.appcommonhandle("手机",null), 
			role: commonLogic.appcommonhandle("职位",null), 
			qq: commonLogic.appcommonhandle("QQ",null), 
			weixin: commonLogic.appcommonhandle("微信",null), 
			id: commonLogic.appcommonhandle("ID",null), 
		},
		uiactions: {
		},
	},
};