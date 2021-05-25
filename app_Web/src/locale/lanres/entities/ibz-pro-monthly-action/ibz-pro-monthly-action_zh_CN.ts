import IbzProMonthlyAction_zh_CN_Base from './ibz-pro-monthly-action_zh_CN_base';

function getLocaleResource(){
    const IbzProMonthlyAction_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzProMonthlyAction_zh_CN_Base(), IbzProMonthlyAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;