import ProductWeekly_zh_CN_Base from './product-weekly_zh_CN_base';

function getLocaleResource(){
    const ProductWeekly_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductWeekly_zh_CN_Base(), ProductWeekly_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;