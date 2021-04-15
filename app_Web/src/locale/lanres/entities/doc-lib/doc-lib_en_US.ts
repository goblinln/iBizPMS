import DocLib_en_US_Base from './doc-lib_en_US_base';

function getLocaleResource(){
    const DocLib_en_US_OwnData = {};
    const targetData = Object.assign(DocLib_en_US_Base(), DocLib_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;