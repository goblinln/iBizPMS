import IbizproProductWeekly_zh_CN_Base from './ibizpro-product-weekly_zh_CN_base';

function getLocaleResource(){
    const IbizproProductWeekly_zh_CN_OwnData = {};
    const targetData = Object.assign(IbizproProductWeekly_zh_CN_Base(), IbizproProductWeekly_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;