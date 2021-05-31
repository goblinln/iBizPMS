import ProductRelease_zh_CN_Base from './product-release_zh_CN_base';

function getLocaleResource(){
    const ProductRelease_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductRelease_zh_CN_Base(), ProductRelease_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;