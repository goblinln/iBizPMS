import ProductDaily_en_US_Base from './product-daily_en_US_base';

function getLocaleResource(){
    const ProductDaily_en_US_OwnData = {};
    const targetData = Object.assign(ProductDaily_en_US_Base(), ProductDaily_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;