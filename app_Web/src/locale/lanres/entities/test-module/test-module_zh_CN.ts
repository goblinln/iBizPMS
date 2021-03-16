import TestModule_zh_CN_Base from './test-module_zh_CN_base';

function getLocaleResource(){
    const TestModule_zh_CN_OwnData = {};
    const targetData = Object.assign(TestModule_zh_CN_Base(), TestModule_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;