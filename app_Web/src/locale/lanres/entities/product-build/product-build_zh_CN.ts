import ProductBuild_zh_CN_Base from './product-build_zh_CN_base';

function getLocaleResource(){
    const ProductBuild_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductBuild_zh_CN_Base(), ProductBuild_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;