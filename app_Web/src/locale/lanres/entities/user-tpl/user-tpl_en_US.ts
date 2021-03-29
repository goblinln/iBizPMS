import UserTpl_en_US_Base from './user-tpl_en_US_base';

function getLocaleResource(){
    const UserTpl_en_US_OwnData = {};
    const targetData = Object.assign(UserTpl_en_US_Base(), UserTpl_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;