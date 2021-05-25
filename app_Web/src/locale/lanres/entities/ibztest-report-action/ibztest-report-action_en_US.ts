import IBZTestReportAction_en_US_Base from './ibztest-report-action_en_US_base';

function getLocaleResource(){
    const IBZTestReportAction_en_US_OwnData = {};
    const targetData = Object.assign(IBZTestReportAction_en_US_Base(), IBZTestReportAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;