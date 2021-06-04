import ProductWeekly_en_US_Base from './product-weekly_en_US_base';

function getLocaleResource(){
    const ProductWeekly_en_US_OwnData = {};
    const targetData = Object.assign(ProductWeekly_en_US_Base(), ProductWeekly_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;