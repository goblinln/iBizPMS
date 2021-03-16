import Burn_en_US_Base from './burn_en_US_base';

function getLocaleResource(){
    const Burn_en_US_OwnData = {};
    const targetData = Object.assign(Burn_en_US_Base(), Burn_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;