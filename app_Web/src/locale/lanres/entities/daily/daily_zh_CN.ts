import Daily_zh_CN_Base from './daily_zh_CN_base';

function getLocaleResource(){
    const Daily_zh_CN_OwnData = {};
    const targetData = Object.assign(Daily_zh_CN_Base(), Daily_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;