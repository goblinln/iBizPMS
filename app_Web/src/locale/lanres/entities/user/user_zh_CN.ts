import User_zh_CN_Base from './user_zh_CN_base';

function getLocaleResource(){
    const User_zh_CN_OwnData = {};
    const targetData = Object.assign(User_zh_CN_Base(), User_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;