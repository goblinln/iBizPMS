import DocContent_zh_CN_Base from './doc-content_zh_CN_base';

function getLocaleResource(){
    const DocContent_zh_CN_OwnData = {};
    const targetData = Object.assign(DocContent_zh_CN_Base(), DocContent_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;