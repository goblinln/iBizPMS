import TestCaseLib_zh_CN_Base from './test-case-lib_zh_CN_base';

function getLocaleResource(){
    const TestCaseLib_zh_CN_OwnData = {};
    const targetData = Object.assign(TestCaseLib_zh_CN_Base(), TestCaseLib_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;