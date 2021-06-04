import ReportSummary_en_US_Base from './report-summary_en_US_base';

function getLocaleResource(){
    const ReportSummary_en_US_OwnData = {};
    const targetData = Object.assign(ReportSummary_en_US_Base(), ReportSummary_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;