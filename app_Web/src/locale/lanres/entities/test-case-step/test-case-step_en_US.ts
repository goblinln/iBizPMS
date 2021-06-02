import TestCaseStep_en_US_Base from './test-case-step_en_US_base';

function getLocaleResource(){
    const TestCaseStep_en_US_OwnData = {};
    const targetData = Object.assign(TestCaseStep_en_US_Base(), TestCaseStep_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;