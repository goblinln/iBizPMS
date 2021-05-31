import Doc_en_US_Base from './doc_en_US_base';

function getLocaleResource(){
    const Doc_en_US_OwnData = {};
    const targetData = Object.assign(Doc_en_US_Base(), Doc_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;