import IbzPlanTemplet_en_US_Base from './ibz-plan-templet_en_US_base';

function getLocaleResource(){
    const IbzPlanTemplet_en_US_OwnData = {};
    const targetData = Object.assign(IbzPlanTemplet_en_US_Base(), IbzPlanTemplet_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;