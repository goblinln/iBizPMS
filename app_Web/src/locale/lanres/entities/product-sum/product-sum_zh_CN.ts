import ProductSum_zh_CN_Base from './product-sum_zh_CN_base';

function getLocaleResource(){
    const ProductSum_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductSum_zh_CN_Base(), ProductSum_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;