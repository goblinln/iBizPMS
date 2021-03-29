import Group_zh_CN_Base from './group_zh_CN_base';

function getLocaleResource(){
    const Group_zh_CN_OwnData = {};
    const targetData = Object.assign(Group_zh_CN_Base(), Group_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;