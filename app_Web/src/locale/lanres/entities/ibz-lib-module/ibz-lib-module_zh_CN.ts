import IbzLibModule_zh_CN_Base from './ibz-lib-module_zh_CN_base';

function getLocaleResource(){
    const IbzLibModule_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzLibModule_zh_CN_Base(), IbzLibModule_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;