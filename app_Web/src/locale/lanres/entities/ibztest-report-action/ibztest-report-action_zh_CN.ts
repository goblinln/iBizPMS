import IBZTestReportAction_zh_CN_Base from './ibztest-report-action_zh_CN_base';

function getLocaleResource(){
    const IBZTestReportAction_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZTestReportAction_zh_CN_Base(), IBZTestReportAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;