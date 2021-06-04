import ProductMonthly_zh_CN_Base from './product-monthly_zh_CN_base';

function getLocaleResource(){
    const ProductMonthly_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductMonthly_zh_CN_Base(), ProductMonthly_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;