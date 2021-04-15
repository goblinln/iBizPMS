import TestModule_en_US_Base from './test-module_en_US_base';

function getLocaleResource(){
    const TestModule_en_US_OwnData = {};
    const targetData = Object.assign(TestModule_en_US_Base(), TestModule_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;