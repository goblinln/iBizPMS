import Monthly_zh_CN_Base from './monthly_zh_CN_base';

function getLocaleResource(){
    const Monthly_zh_CN_OwnData = {};
    const targetData = Object.assign(Monthly_zh_CN_Base(), Monthly_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;