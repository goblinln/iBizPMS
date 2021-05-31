import TestCase_zh_CN_Base from './test-case_zh_CN_base';

function getLocaleResource(){
    const TestCase_zh_CN_OwnData = {};
    const targetData = Object.assign(TestCase_zh_CN_Base(), TestCase_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;