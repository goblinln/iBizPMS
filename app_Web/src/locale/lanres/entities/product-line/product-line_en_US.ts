import ProductLine_en_US_Base from './product-line_en_US_base';

function getLocaleResource(){
    const ProductLine_en_US_OwnData = {};
    const targetData = Object.assign(ProductLine_en_US_Base(), ProductLine_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;