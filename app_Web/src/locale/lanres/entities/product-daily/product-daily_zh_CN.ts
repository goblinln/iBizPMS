import ProductDaily_zh_CN_Base from './product-daily_zh_CN_base';

function getLocaleResource(){
    const ProductDaily_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductDaily_zh_CN_Base(), ProductDaily_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;