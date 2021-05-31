import ProductProject_en_US_Base from './product-project_en_US_base';

function getLocaleResource(){
    const ProductProject_en_US_OwnData = {};
    const targetData = Object.assign(ProductProject_en_US_Base(), ProductProject_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;