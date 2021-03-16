import IbizproProductWeekly_en_US_Base from './ibizpro-product-weekly_en_US_base';

function getLocaleResource(){
    const IbizproProductWeekly_en_US_OwnData = {};
    const targetData = Object.assign(IbizproProductWeekly_en_US_Base(), IbizproProductWeekly_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;