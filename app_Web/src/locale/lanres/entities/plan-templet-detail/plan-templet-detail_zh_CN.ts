import PlanTempletDetail_zh_CN_Base from './plan-templet-detail_zh_CN_base';

function getLocaleResource(){
    const PlanTempletDetail_zh_CN_OwnData = {};
    const targetData = Object.assign(PlanTempletDetail_zh_CN_Base(), PlanTempletDetail_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;