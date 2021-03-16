import IbzPlanTempletDetail_en_US_Base from './ibz-plan-templet-detail_en_US_base';

function getLocaleResource(){
    const IbzPlanTempletDetail_en_US_OwnData = {};
    const targetData = Object.assign(IbzPlanTempletDetail_en_US_Base(), IbzPlanTempletDetail_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;