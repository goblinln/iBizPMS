import IbzProMonthlyAction_en_US_Base from './ibz-pro-monthly-action_en_US_base';

function getLocaleResource(){
    const IbzProMonthlyAction_en_US_OwnData = {};
    const targetData = Object.assign(IbzProMonthlyAction_en_US_Base(), IbzProMonthlyAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;