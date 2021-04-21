import SubProductPlan_en_US_Base from './sub-product-plan_en_US_base';

function getLocaleResource(){
    const SubProductPlan_en_US_OwnData = {};
    const targetData = Object.assign(SubProductPlan_en_US_Base(), SubProductPlan_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;