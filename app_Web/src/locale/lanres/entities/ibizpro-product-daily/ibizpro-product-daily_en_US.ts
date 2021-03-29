import IbizproProductDaily_en_US_Base from './ibizpro-product-daily_en_US_base';

function getLocaleResource(){
    const IbizproProductDaily_en_US_OwnData = {};
    const targetData = Object.assign(IbizproProductDaily_en_US_Base(), IbizproProductDaily_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;