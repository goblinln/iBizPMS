import Module_en_US_Base from './module_en_US_base';

function getLocaleResource(){
    const Module_en_US_OwnData = {};
    const targetData = Object.assign(Module_en_US_Base(), Module_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;