import Module_zh_CN_Base from './module_zh_CN_base';

function getLocaleResource(){
    const Module_zh_CN_OwnData = {};
    const targetData = Object.assign(Module_zh_CN_Base(), Module_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;