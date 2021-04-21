import IbizproProductMonthly_en_US_Base from './ibizpro-product-monthly_en_US_base';

function getLocaleResource(){
    const IbizproProductMonthly_en_US_OwnData = {};
    const targetData = Object.assign(IbizproProductMonthly_en_US_Base(), IbizproProductMonthly_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;