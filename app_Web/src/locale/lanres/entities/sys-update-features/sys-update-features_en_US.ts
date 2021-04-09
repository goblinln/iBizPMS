import SysUpdateFeatures_en_US_Base from './sys-update-features_en_US_base';

function getLocaleResource(){
    const SysUpdateFeatures_en_US_OwnData = {};
    const targetData = Object.assign(SysUpdateFeatures_en_US_Base(), SysUpdateFeatures_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;