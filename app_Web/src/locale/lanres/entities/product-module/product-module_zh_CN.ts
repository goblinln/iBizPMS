import ProductModule_zh_CN_Base from './product-module_zh_CN_base';

function getLocaleResource(){
    const ProductModule_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductModule_zh_CN_Base(), ProductModule_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;