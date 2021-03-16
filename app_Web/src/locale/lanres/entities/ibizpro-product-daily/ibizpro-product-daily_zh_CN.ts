import IbizproProductDaily_zh_CN_Base from './ibizpro-product-daily_zh_CN_base';

function getLocaleResource(){
    const IbizproProductDaily_zh_CN_OwnData = {};
    const targetData = Object.assign(IbizproProductDaily_zh_CN_Base(), IbizproProductDaily_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;