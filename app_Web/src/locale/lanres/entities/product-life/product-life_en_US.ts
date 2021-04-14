import ProductLife_en_US_Base from './product-life_en_US_base';

function getLocaleResource(){
    const ProductLife_en_US_OwnData = {};
    const targetData = Object.assign(ProductLife_en_US_Base(), ProductLife_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;