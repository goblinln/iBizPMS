import ProductLine_zh_CN_Base from './product-line_zh_CN_base';

function getLocaleResource(){
    const ProductLine_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductLine_zh_CN_Base(), ProductLine_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;