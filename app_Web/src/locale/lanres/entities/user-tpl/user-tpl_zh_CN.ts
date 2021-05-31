import UserTpl_zh_CN_Base from './user-tpl_zh_CN_base';

function getLocaleResource(){
    const UserTpl_zh_CN_OwnData = {};
    const targetData = Object.assign(UserTpl_zh_CN_Base(), UserTpl_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;