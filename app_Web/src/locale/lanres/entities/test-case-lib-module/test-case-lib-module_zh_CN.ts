import TestCaseLibModule_zh_CN_Base from './test-case-lib-module_zh_CN_base';

function getLocaleResource(){
    const TestCaseLibModule_zh_CN_OwnData = {};
    const targetData = Object.assign(TestCaseLibModule_zh_CN_Base(), TestCaseLibModule_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;