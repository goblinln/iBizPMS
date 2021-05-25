import IbzProReportlyAction_zh_CN_Base from './ibz-pro-reportly-action_zh_CN_base';

function getLocaleResource(){
    const IbzProReportlyAction_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzProReportlyAction_zh_CN_Base(), IbzProReportlyAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;