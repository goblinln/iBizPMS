import ProductModule_en_US_Base from './product-module_en_US_base';

function getLocaleResource(){
    const ProductModule_en_US_OwnData = {};
    const targetData = Object.assign(ProductModule_en_US_Base(), ProductModule_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;