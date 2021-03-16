import TestReport_zh_CN_Base from './test-report_zh_CN_base';

function getLocaleResource(){
    const TestReport_zh_CN_OwnData = {};
    const targetData = Object.assign(TestReport_zh_CN_Base(), TestReport_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;