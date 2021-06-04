import ProductMonthly_en_US_Base from './product-monthly_en_US_base';

function getLocaleResource(){
    const ProductMonthly_en_US_OwnData = {};
    const targetData = Object.assign(ProductMonthly_en_US_Base(), ProductMonthly_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;