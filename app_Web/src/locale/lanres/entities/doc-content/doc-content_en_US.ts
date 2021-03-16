import DocContent_en_US_Base from './doc-content_en_US_base';

function getLocaleResource(){
    const DocContent_en_US_OwnData = {};
    const targetData = Object.assign(DocContent_en_US_Base(), DocContent_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;