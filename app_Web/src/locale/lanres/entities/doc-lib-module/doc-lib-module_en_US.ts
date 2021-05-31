import DocLibModule_en_US_Base from './doc-lib-module_en_US_base';

function getLocaleResource(){
    const DocLibModule_en_US_OwnData = {};
    const targetData = Object.assign(DocLibModule_en_US_Base(), DocLibModule_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;