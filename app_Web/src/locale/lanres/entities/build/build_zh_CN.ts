import Build_zh_CN_Base from './build_zh_CN_base';

function getLocaleResource(){
    const Build_zh_CN_OwnData = {};
    const targetData = Object.assign(Build_zh_CN_Base(), Build_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;