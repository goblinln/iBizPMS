import Doc_zh_CN_Base from './doc_zh_CN_base';

function getLocaleResource(){
    const Doc_zh_CN_OwnData = {};
    const targetData = Object.assign(Doc_zh_CN_Base(), Doc_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;