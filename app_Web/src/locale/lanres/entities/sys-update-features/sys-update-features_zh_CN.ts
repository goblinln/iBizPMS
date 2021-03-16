import SysUpdateFeatures_zh_CN_Base from './sys-update-features_zh_CN_base';

function getLocaleResource(){
    const SysUpdateFeatures_zh_CN_OwnData = {};
    const targetData = Object.assign(SysUpdateFeatures_zh_CN_Base(), SysUpdateFeatures_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;