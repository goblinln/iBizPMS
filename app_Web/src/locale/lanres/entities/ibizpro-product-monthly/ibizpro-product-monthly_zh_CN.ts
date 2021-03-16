import IbizproProductMonthly_zh_CN_Base from './ibizpro-product-monthly_zh_CN_base';

function getLocaleResource(){
    const IbizproProductMonthly_zh_CN_OwnData = {};
    const targetData = Object.assign(IbizproProductMonthly_zh_CN_Base(), IbizproProductMonthly_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;