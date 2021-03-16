import IbzPlanTemplet_zh_CN_Base from './ibz-plan-templet_zh_CN_base';

function getLocaleResource(){
    const IbzPlanTemplet_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzPlanTemplet_zh_CN_Base(), IbzPlanTemplet_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;