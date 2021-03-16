import PRODUCTTEAM_zh_CN_Base from './productteam_zh_CN_base';

function getLocaleResource(){
    const PRODUCTTEAM_zh_CN_OwnData = {};
    const targetData = Object.assign(PRODUCTTEAM_zh_CN_Base(), PRODUCTTEAM_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;