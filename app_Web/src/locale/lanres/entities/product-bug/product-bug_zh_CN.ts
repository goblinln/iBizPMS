import ProductBug_zh_CN_Base from './product-bug_zh_CN_base';

function getLocaleResource(){
    const ProductBug_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductBug_zh_CN_Base(), ProductBug_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;