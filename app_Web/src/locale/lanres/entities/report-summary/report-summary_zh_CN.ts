import ReportSummary_zh_CN_Base from './report-summary_zh_CN_base';

function getLocaleResource(){
    const ReportSummary_zh_CN_OwnData = {};
    const targetData = Object.assign(ReportSummary_zh_CN_Base(), ReportSummary_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;