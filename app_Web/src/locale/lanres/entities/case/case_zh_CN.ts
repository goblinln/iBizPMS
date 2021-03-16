import Case_zh_CN_Base from './case_zh_CN_base';

function getLocaleResource(){
    const Case_zh_CN_OwnData = {};
    const targetData = Object.assign(Case_zh_CN_Base(), Case_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;