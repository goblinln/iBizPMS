import IbzproConfig_en_US_Base from './ibzpro-config_en_US_base';

function getLocaleResource(){
    const IbzproConfig_en_US_OwnData = {};
    const targetData = Object.assign(IbzproConfig_en_US_Base(), IbzproConfig_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;