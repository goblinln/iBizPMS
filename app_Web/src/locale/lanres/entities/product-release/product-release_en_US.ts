import ProductRelease_en_US_Base from './product-release_en_US_base';

function getLocaleResource(){
    const ProductRelease_en_US_OwnData = {};
    const targetData = Object.assign(ProductRelease_en_US_Base(), ProductRelease_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;