import ProductStats_zh_CN_Base from './product-stats_zh_CN_base';

function getLocaleResource(){
    const ProductStats_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductStats_zh_CN_Base(), ProductStats_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;