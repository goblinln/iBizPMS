import Burn_zh_CN_Base from './burn_zh_CN_base';

function getLocaleResource(){
    const Burn_zh_CN_OwnData = {};
    const targetData = Object.assign(Burn_zh_CN_Base(), Burn_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;