import ProductBug_en_US_Base from './product-bug_en_US_base';

function getLocaleResource(){
    const ProductBug_en_US_OwnData = {};
    const targetData = Object.assign(ProductBug_en_US_Base(), ProductBug_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;