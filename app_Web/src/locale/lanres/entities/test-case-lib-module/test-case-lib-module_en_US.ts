import TestCaseLibModule_en_US_Base from './test-case-lib-module_en_US_base';

function getLocaleResource(){
    const TestCaseLibModule_en_US_OwnData = {};
    const targetData = Object.assign(TestCaseLibModule_en_US_Base(), TestCaseLibModule_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;