import PlanTempletDetail_en_US_Base from './plan-templet-detail_en_US_base';

function getLocaleResource(){
    const PlanTempletDetail_en_US_OwnData = {};
    const targetData = Object.assign(PlanTempletDetail_en_US_Base(), PlanTempletDetail_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;