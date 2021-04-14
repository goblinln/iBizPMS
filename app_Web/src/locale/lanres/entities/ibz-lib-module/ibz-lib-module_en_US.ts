import IbzLibModule_en_US_Base from './ibz-lib-module_en_US_base';

function getLocaleResource(){
    const IbzLibModule_en_US_OwnData = {};
    const targetData = Object.assign(IbzLibModule_en_US_Base(), IbzLibModule_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;