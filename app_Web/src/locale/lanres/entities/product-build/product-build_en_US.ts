import ProductBuild_en_US_Base from './product-build_en_US_base';

function getLocaleResource(){
    const ProductBuild_en_US_OwnData = {};
    const targetData = Object.assign(ProductBuild_en_US_Base(), ProductBuild_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;