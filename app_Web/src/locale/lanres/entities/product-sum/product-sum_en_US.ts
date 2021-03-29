import ProductSum_en_US_Base from './product-sum_en_US_base';

function getLocaleResource(){
    const ProductSum_en_US_OwnData = {};
    const targetData = Object.assign(ProductSum_en_US_Base(), ProductSum_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;