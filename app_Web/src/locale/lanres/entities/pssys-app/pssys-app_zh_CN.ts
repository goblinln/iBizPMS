import PSSysApp_zh_CN_Base from './pssys-app_zh_CN_base';

function getLocaleResource(){
    const PSSysApp_zh_CN_OwnData = {};
    const targetData = Object.assign(PSSysApp_zh_CN_Base(), PSSysApp_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;