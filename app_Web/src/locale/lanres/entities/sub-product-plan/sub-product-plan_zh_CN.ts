import SubProductPlan_zh_CN_Base from './sub-product-plan_zh_CN_base';

function getLocaleResource(){
    const SubProductPlan_zh_CN_OwnData = {};
    const targetData = Object.assign(SubProductPlan_zh_CN_Base(), SubProductPlan_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;