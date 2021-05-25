import IbzProReportlyAction_en_US_Base from './ibz-pro-reportly-action_en_US_base';

function getLocaleResource(){
    const IbzProReportlyAction_en_US_OwnData = {};
    const targetData = Object.assign(IbzProReportlyAction_en_US_Base(), IbzProReportlyAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;