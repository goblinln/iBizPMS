import TestReport_en_US_Base from './test-report_en_US_base';

function getLocaleResource(){
    const TestReport_en_US_OwnData = {};
    const targetData = Object.assign(TestReport_en_US_Base(), TestReport_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;