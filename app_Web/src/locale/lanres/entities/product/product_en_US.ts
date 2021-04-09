import Product_en_US_Base from './product_en_US_base';

function getLocaleResource(){
    const Product_en_US_OwnData = {};
    const targetData = Object.assign(Product_en_US_Base(), Product_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;