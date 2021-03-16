import IbzCase_en_US_Base from './ibz-case_en_US_base';

function getLocaleResource(){
    const IbzCase_en_US_OwnData = {};
    const targetData = Object.assign(IbzCase_en_US_Base(), IbzCase_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;