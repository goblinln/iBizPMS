import Product_zh_CN_Base from './product_zh_CN_base';

function getLocaleResource(){
    const Product_zh_CN_OwnData = {};
    const targetData = Object.assign(Product_zh_CN_Base(), Product_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;