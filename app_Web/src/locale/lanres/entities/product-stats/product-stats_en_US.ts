import ProductStats_en_US_Base from './product-stats_en_US_base';

function getLocaleResource(){
    const ProductStats_en_US_OwnData = {};
    const targetData = Object.assign(ProductStats_en_US_Base(), ProductStats_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;