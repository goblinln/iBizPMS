import ProductPlanAction_en_US_Base from './product-plan-action_en_US_base';

function getLocaleResource(){
    const ProductPlanAction_en_US_OwnData = {};
    const targetData = Object.assign(ProductPlanAction_en_US_Base(), ProductPlanAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;