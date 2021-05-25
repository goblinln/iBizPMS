import ProductPlanAction_zh_CN_Base from './product-plan-action_zh_CN_base';

function getLocaleResource(){
    const ProductPlanAction_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductPlanAction_zh_CN_Base(), ProductPlanAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;