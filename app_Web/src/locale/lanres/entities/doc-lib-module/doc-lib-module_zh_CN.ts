import DocLibModule_zh_CN_Base from './doc-lib-module_zh_CN_base';

function getLocaleResource(){
    const DocLibModule_zh_CN_OwnData = {};
    const targetData = Object.assign(DocLibModule_zh_CN_Base(), DocLibModule_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;