import DocLib_zh_CN_Base from './doc-lib_zh_CN_base';

function getLocaleResource(){
    const DocLib_zh_CN_OwnData = {};
    const targetData = Object.assign(DocLib_zh_CN_Base(), DocLib_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;