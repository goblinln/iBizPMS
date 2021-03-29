import ProductPlan_en_US_Base from './product-plan_en_US_base';

function getLocaleResource(){
    const ProductPlan_en_US_OwnData = {};
    const targetData = Object.assign(ProductPlan_en_US_Base(), ProductPlan_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;