import UserContact_zh_CN_Base from './user-contact_zh_CN_base';

function getLocaleResource(){
    const UserContact_zh_CN_OwnData = {};
    const targetData = Object.assign(UserContact_zh_CN_Base(), UserContact_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;