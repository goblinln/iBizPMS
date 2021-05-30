import TestCase_en_US_Base from './test-case_en_US_base';

function getLocaleResource(){
    const TestCase_en_US_OwnData = {};
    const targetData = Object.assign(TestCase_en_US_Base(), TestCase_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;