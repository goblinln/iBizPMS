import TestCaseStep_zh_CN_Base from './test-case-step_zh_CN_base';

function getLocaleResource(){
    const TestCaseStep_zh_CN_OwnData = {};
    const targetData = Object.assign(TestCaseStep_zh_CN_Base(), TestCaseStep_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;