import TestResult_zh_CN_Base from './test-result_zh_CN_base';

function getLocaleResource(){
    const TestResult_zh_CN_OwnData = {};
    const targetData = Object.assign(TestResult_zh_CN_Base(), TestResult_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;