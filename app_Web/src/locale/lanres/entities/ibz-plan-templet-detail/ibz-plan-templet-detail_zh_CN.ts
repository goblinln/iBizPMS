import IbzPlanTempletDetail_zh_CN_Base from './ibz-plan-templet-detail_zh_CN_base';

function getLocaleResource(){
    const IbzPlanTempletDetail_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzPlanTempletDetail_zh_CN_Base(), IbzPlanTempletDetail_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;