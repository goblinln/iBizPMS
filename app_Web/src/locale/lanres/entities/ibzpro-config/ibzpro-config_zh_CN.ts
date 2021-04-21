import IbzproConfig_zh_CN_Base from './ibzpro-config_zh_CN_base';

function getLocaleResource(){
    const IbzproConfig_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzproConfig_zh_CN_Base(), IbzproConfig_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;