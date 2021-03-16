import TestResult_en_US_Base from './test-result_en_US_base';

function getLocaleResource(){
    const TestResult_en_US_OwnData = {};
    const targetData = Object.assign(TestResult_en_US_Base(), TestResult_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;