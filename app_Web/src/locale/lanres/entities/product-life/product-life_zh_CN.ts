import ProductLife_zh_CN_Base from './product-life_zh_CN_base';

function getLocaleResource(){
    const ProductLife_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductLife_zh_CN_Base(), ProductLife_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;