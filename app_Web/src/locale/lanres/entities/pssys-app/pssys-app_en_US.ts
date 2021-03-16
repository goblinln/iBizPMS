import PSSysApp_en_US_Base from './pssys-app_en_US_base';

function getLocaleResource(){
    const PSSysApp_en_US_OwnData = {};
    const targetData = Object.assign(PSSysApp_en_US_Base(), PSSysApp_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;