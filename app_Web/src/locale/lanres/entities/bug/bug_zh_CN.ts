import Bug_zh_CN_Base from './bug_zh_CN_base';

function getLocaleResource(){
    const Bug_zh_CN_OwnData = {};
    const targetData = Object.assign(Bug_zh_CN_Base(), Bug_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;