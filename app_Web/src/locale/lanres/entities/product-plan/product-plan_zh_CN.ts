import ProductPlan_zh_CN_Base from './product-plan_zh_CN_base';

function getLocaleResource(){
    const ProductPlan_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductPlan_zh_CN_Base(), ProductPlan_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;